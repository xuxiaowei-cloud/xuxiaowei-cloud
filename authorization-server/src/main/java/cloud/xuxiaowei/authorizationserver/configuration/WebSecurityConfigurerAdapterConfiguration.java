package cloud.xuxiaowei.authorizationserver.configuration;

import cloud.xuxiaowei.authorizationserver.filter.RedirectLoginPageFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

/**
 * Spring Security 配置
 *
 * @author xuxiaowei
 * @see UsernamePasswordAuthenticationFilter 用户名密码认证过滤器
 * @see DefaultLoginPageGeneratingFilter 默认登录页面
 * @see HttpSecurity#formLogin() 登录配置
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    private RedirectLoginPageFilter redirectLoginPageFilter;

    @Autowired
    public void setRedirectLoginPageFilter(RedirectLoginPageFilter redirectLoginPageFilter) {
        this.redirectLoginPageFilter = redirectLoginPageFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 任何路径均需要授权后才能访问
        http.authorizeRequests().anyRequest().authenticated();

        // 自定义：默认登录页面URL（在未配置登录页面URL时，即未设置：HttpSecurity#formLogin()）
        http.addFilterAt(redirectLoginPageFilter, DefaultLoginPageGeneratingFilter.class);

    }

}
