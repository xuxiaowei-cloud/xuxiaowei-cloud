package cloud.xuxiaowei.gateway.configuration;

import cloud.xuxiaowei.core.properties.CloudWhiteListProperties;
import cloud.xuxiaowei.gateway.filter.CorsBeforeWebFilter;
import cloud.xuxiaowei.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

/**
 * 反应式授权管理器
 * <p>
 * 当引入了 spring-cloud-starter-gateway 之后，即 {@link ReactiveJwtDecoder} 可正常使用时（存在 {@link Mono}），
 * 由于 org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerJwkConfiguration.JwtConfiguration#jwtDecoder()，
 * 所以设置了 spring.security.oauth2.resourceserver.jwt.jwk-set-uri 之后，不可使用 {@link WebSecurityConfigurerAdapter}
 * 解决办法为使用本类
 *
 * @author xuxiaowei
 * @see EnableWebFluxSecurity
 * @see AuthenticationWebFilter 身份验证 Web 过滤器，等级 <code>http.addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION);</code>
 * @see ReactiveUserDetailsServiceAutoConfiguration
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class ReactiveAuthorizationManagerConfiguration implements ReactiveAuthorizationManager<AuthorizationContext> {

    private KeyPair keyPair;

    private ServerAuthenticationEntryPoint serverAuthenticationEntryPoint;

    private CorsBeforeWebFilter corsBeforeWebFilter;

    private CloudWhiteListProperties cloudWhiteListProperties;

    @Autowired
    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Autowired
    public void setServerAuthenticationEntryPoint(ServerAuthenticationEntryPoint serverAuthenticationEntryPoint) {
        this.serverAuthenticationEntryPoint = serverAuthenticationEntryPoint;
    }

    @Autowired
    public void setCorsBeforeWebFilter(CorsBeforeWebFilter corsBeforeWebFilter) {
        this.corsBeforeWebFilter = corsBeforeWebFilter;
    }

    @Autowired
    public void setCloudWhiteListProperties(CloudWhiteListProperties cloudWhiteListProperties) {
        this.cloudWhiteListProperties = cloudWhiteListProperties;
    }

    /**
     * 禁止控制室台输出默认用户的密码
     */
    @Autowired
    public void setSecurityProperties(SecurityProperties securityProperties) {
        securityProperties.getUser().setPassword(UUID.randomUUID().toString());
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        // 反应式授权管理器
        http.authorizeExchange().anyExchange().access(this);

        // 临时禁用 跨站请求伪造 CSRF
        // 待转化为配置文件
        http.csrf().disable();

        PublicKey publicKey = keyPair.getPublic();

        // 启用 OAuth2 JWT 资源服务器支持
        http.oauth2ResourceServer().jwt().publicKey((RSAPublicKey) publicKey);

        // 资源服务异常切入点（验证Token异常）
        http.oauth2ResourceServer().authenticationEntryPoint(serverAuthenticationEntryPoint);

        // 自定义动态跨域 CORS 配置 过滤器 <code>http.addFilterBefore(过滤器, SecurityWebFiltersOrder.CORS);</code>

        // 在 CORS 之前执行
        http.addFilterBefore(corsBeforeWebFilter, SecurityWebFiltersOrder.CORS);

        // 身份验证入口点
        http.exceptionHandling().authenticationEntryPoint(serverAuthenticationEntryPoint);

        return http.build();
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {

        ServerWebExchange exchange = authorizationContext.getExchange();

        boolean whiteList = whiteList(exchange);
        if (whiteList) {
            return Mono.just(new AuthorizationDecision(true));
        }

        return authentication.map(requestAuthentication -> {
            // 已通过认证授权
            if (requestAuthentication.isAuthenticated()) {
                // 放行
                return new AuthorizationDecision(true);
            } else {
                // 未通过认证授权
                // 拒绝放行
                return new AuthorizationDecision(false);
            }
        })
                // 无认证授权
                // 拒绝放行
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     * 白名单配置
     *
     * @param exchange 服务器网络交换
     * @return 返回匹配结果
     */
    private boolean whiteList(ServerWebExchange exchange) {

        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String path = uri.getPath();

        InetSocketAddress remoteAddress = request.getRemoteAddress();

        assert remoteAddress != null;
        InetAddress address = remoteAddress.getAddress();
        String hostAddress = address.getHostAddress();

        List<String> actuatorIpList = cloudWhiteListProperties.getActuatorIpList();

        // 放行指定IP访问端点
        if (path.contains(Constant.ACTUATOR) && actuatorIpList.contains(hostAddress)) {
            return true;
        }

        String[] pathSplit = path.split("/");
        if (pathSplit.length > 1) {
            String serviceName = pathSplit[1];

            List<CloudWhiteListProperties.Service> services = cloudWhiteListProperties.getServices();

            for (CloudWhiteListProperties.Service service : services) {
                String name = service.getName();
                List<String> pathList = service.getPathList();

                if (serviceName.equals(name)) {
                    String substring = path.substring(serviceName.length() + 1);

                    if (pathList.contains(substring)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return ReactiveAuthorizationManager.super.verify(authentication, object);
    }

}
