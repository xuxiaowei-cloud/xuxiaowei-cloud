package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.passport.service.LoginService;
import cloud.xuxiaowei.passport.service.impl.DefaultCsrfRequestMatcherImpl;
import cloud.xuxiaowei.passport.service.impl.DefaultLoginServiceImpl;
import cloud.xuxiaowei.passport.service.impl.DefaultPasswordEncoderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
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
     * 默认用户信息 {@link UserDetailsService} {@link Bean}
     * <p>
     * 在 {@link UserDetailsService} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     * <p>
     * 存在 {@link UserDetailsService} 对应的 {@link Bean} 时，控制台不会输出默认用户名为“user”的密码
     *
     * @return 在 {@link UserDetailsService} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     */
    @Bean
    @ConditionalOnMissingBean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }

    /**
     * 默认密码编辑器 {@link PasswordEncoder} {@link Bean}
     * <p>
     * 在 {@link PasswordEncoder} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 返回 默认密码编辑器 {@link Bean}
     */
    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new DefaultPasswordEncoderImpl();
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

}
