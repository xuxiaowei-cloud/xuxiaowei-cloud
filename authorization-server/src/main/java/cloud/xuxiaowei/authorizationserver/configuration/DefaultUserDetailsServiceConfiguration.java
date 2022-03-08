package cloud.xuxiaowei.authorizationserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

/**
 * 默认 {@link UserDetailsService} 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class DefaultUserDetailsServiceConfiguration {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * {@link UserDetailsService} {@link Bean}
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

}
