package cloud.xuxiaowei.oauth2client.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@SuppressWarnings({"deprecation"})
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 此模块仅为 OAuth 2.0 客户端独立程序，不需要 Security 登录
        http.formLogin().disable();

    }

}
