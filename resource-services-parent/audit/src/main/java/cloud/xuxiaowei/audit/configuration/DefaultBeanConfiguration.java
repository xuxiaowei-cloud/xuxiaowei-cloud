package cloud.xuxiaowei.audit.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * 默认 {@link Bean} 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@SuppressWarnings({"deprecation"})
public class DefaultBeanConfiguration {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
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
