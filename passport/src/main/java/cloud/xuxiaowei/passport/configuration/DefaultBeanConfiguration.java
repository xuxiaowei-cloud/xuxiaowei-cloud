package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.passport.service.LoginService;
import cloud.xuxiaowei.passport.service.impl.DefaultCsrfRequestMatcherImpl;
import cloud.xuxiaowei.passport.service.impl.DefaultLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.sql.DataSource;

import static cloud.xuxiaowei.passport.service.impl.DefaultCsrfRequestMatcherImpl.CSRF_REQUEST_MATCHER_BEAN_NAME;

/**
 * 默认 {@link Bean} 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class DefaultBeanConfiguration {

    private DataSource dataSource;

    private ServerProperties serverProperties;

    private CloudSecurityProperties cloudSecurityProperties;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setServerProperties(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Autowired
    public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
        this.cloudSecurityProperties = cloudSecurityProperties;
    }

    /**
     * 默认 登录 服务 {@link LoginService} {@link Bean}
     * <p>
     * 在 {@link LoginService} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 返回 默认 登录 服务 {@link Bean}
     */
    @Bean
    @ConditionalOnMissingBean
    public LoginService loginService() {
        return new DefaultLoginServiceImpl();
    }

    /**
     * 默认 CSRF 请求匹配匹配 {@link Bean} 配置
     *
     * @return 返回 默认 CSRF 请求匹配匹配 {@link Bean}
     */
    @Bean(CSRF_REQUEST_MATCHER_BEAN_NAME)
    @ConditionalOnMissingBean(name = CSRF_REQUEST_MATCHER_BEAN_NAME)
    public RequestMatcher defaultCsrfRequestMatcher() {
        ServerProperties.Servlet servlet = serverProperties.getServlet();
        String contextPath = servlet.getContextPath();
        return new DefaultCsrfRequestMatcherImpl(contextPath, cloudSecurityProperties);
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
