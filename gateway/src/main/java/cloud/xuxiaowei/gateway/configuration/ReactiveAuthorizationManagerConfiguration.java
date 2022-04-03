package cloud.xuxiaowei.gateway.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

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
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class ReactiveAuthorizationManagerConfiguration implements ReactiveAuthorizationManager<AuthorizationContext> {

    private KeyPair keyPair;

    @Autowired
    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        // 反应式授权管理器
        http.authorizeExchange().anyExchange().access(this);

        PublicKey publicKey = keyPair.getPublic();

        // 启用 OAuth2 JWT 资源服务器支持
        http.oauth2ResourceServer().jwt().publicKey((RSAPublicKey) publicKey);

        // 自定义动态跨域 CORS 配置 过滤器 <code>http.addFilterBefore(过滤器, SecurityWebFiltersOrder.CORS);</code>

        return http.build();
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {

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
