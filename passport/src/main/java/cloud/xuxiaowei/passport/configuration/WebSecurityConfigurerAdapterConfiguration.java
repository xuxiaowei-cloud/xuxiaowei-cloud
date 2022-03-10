package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private UserDetailsService userDetailsService;

    private CloudSecurityProperties cloudSecurityProperties;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
        this.cloudSecurityProperties = cloudSecurityProperties;
    }

    /**
     * @see super.configure(AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户密码编辑器
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // 查询登录用户
        auth.userDetailsService(userDetailsService).passwordEncoder(delegatingPasswordEncoder);
    }

    /**
     * @see super#configure(HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                // 登录页面地址
                .loginPage(cloudSecurityProperties.getLoginPageUrl())
                // 登录请求地址
                .loginProcessingUrl(cloudSecurityProperties.getLoginProcessingUrl())
                // 登录失败地址
                .failureUrl(cloudSecurityProperties.getFailureUrl())
                // 登录成功地址
                .successForwardUrl(cloudSecurityProperties.getSuccessForwardUrl())
                // 已上地址，允许任何人访问
                .permitAll();

        // 所有地址，均需要认证后才能访问
        http.authorizeRequests().anyRequest().authenticated();

        // 禁用 CSRF
        http.csrf().disable();

    }

    /**
     * 资源（静态）不拦截
     *
     * @see super#configure(WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico");
        web.ignoring().antMatchers("/**/**.js");
        web.ignoring().antMatchers("/**/**.css");
    }

}
