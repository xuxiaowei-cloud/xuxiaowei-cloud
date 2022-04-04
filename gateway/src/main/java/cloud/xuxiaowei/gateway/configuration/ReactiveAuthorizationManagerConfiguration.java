package cloud.xuxiaowei.gateway.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.List;

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
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class ReactiveAuthorizationManagerConfiguration implements ReactiveAuthorizationManager<AuthorizationContext> {

    private KeyPair keyPair;

    private ServerAuthenticationEntryPoint serverAuthenticationEntryPoint;

    @Autowired
    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Autowired
    public void setServerAuthenticationEntryPoint(ServerAuthenticationEntryPoint serverAuthenticationEntryPoint) {
        this.serverAuthenticationEntryPoint = serverAuthenticationEntryPoint;
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

        // 自定义动态跨域 CORS 配置 过滤器 <code>http.addFilterBefore(过滤器, SecurityWebFiltersOrder.CORS);</code>

        // 身份验证入口点
        http.exceptionHandling().authenticationEntryPoint(serverAuthenticationEntryPoint);

        return http.build();
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {

        ServerWebExchange exchange = authorizationContext.getExchange();
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String path = uri.getPath();

        // 待转换为配置文件
        List<String> pathList = Arrays.asList("/passport/login", "/passport/code", "/authorization-server/oauth/authorize");
        if (pathList.contains(path)) {
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

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return ReactiveAuthorizationManager.super.verify(authentication, object);
    }

}
