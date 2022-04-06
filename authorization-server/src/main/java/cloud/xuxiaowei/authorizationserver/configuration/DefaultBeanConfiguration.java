package cloud.xuxiaowei.authorizationserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.RedisAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.*;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * 默认 {@link Bean} 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@SuppressWarnings({"deprecation"})
@Configuration
public class DefaultBeanConfiguration {

    private DataSource dataSource;

    /**
     * 来自 spring-cloud-context-*.*.*.jar
     */
    private KeyProperties keyProperties;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setKeyProperties(KeyProperties keyProperties) {
        this.keyProperties = keyProperties;
    }

    /**
     * 授权 Code {@link Bean}
     * <p>
     * 在 {@link AuthorizationCodeServices} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link AuthorizationCodeServices} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     * @see InMemoryAuthorizationCodeServices 基于内存的 code 服务
     * @see RedisAuthorizationCodeServices 基于 Redis 的 code 服务
     * @see JdbcAuthorizationCodeServices 基于 JDBC 的 code 服务
     */
    @Bean
    @ConditionalOnMissingBean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource) {
            private final RandomValueStringGenerator GENERATOR = new RandomValueStringGenerator();

            @Override
            public String createAuthorizationCode(OAuth2Authentication authentication) {
                // 自定义 code 长度
                GENERATOR.setLength(32);
                String code = GENERATOR.generate();
                store(code, authentication);
                return code;
            }
        };
    }

    /**
     * {@link KeyPair} {@link Bean}
     * <p>
     * 在 {@link KeyPair} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link KeyPair} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     */
    @Bean
    @ConditionalOnMissingBean
    public KeyPair keyPair() {
        KeyProperties.KeyStore keyStore = keyProperties.getKeyStore();
        Resource location = keyStore.getLocation();
        String alias = keyStore.getAlias();
        String password = keyStore.getPassword();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(location, password.toCharArray());
        return keyStoreKeyFactory.getKeyPair(alias, password.toCharArray());
    }

    /**
     * 加密 Token {@link Bean}
     * <p>
     * 在 {@link JwtAccessTokenConverter} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link JwtAccessTokenConverter} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     */
    @Bean
    @ConditionalOnMissingBean
    public JwtAccessTokenConverter jwtAccessTokenConverter(KeyPair keyPair) {
        // 加密 Token
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    /**
     * JDBC Token 储存
     * 在 {@link TokenStore} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link TokenStore} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     * @see InMemoryTokenStore 内存 储存
     * @see JwtTokenStore Jwt Token 储存
     * @see JwkTokenStore Jwk Token 储存
     * @see RedisTokenStore Redis Token 储存
     */
    @Bean
    @ConditionalOnMissingBean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

}
