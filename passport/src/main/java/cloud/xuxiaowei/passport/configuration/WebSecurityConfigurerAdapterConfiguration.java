package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static cloud.xuxiaowei.passport.service.impl.DefaultCsrfRequestMatcherImpl.CSRF_REQUEST_MATCHER_BEAN_NAME;

/**
 * Spring Security 配置
 *
 * @author xuxiaowei
 * @see UsernamePasswordAuthenticationFilter 用户名密码认证过滤器
 * @see DefaultLoginPageGeneratingFilter 默认登录页面
 * @see HttpSecurity#formLogin() 登录配置
 * @see CsrfFilter
 * @see CsrfTokenRepository
 * @see LazyCsrfTokenRepository
 * @see HttpSessionCsrfTokenRepository
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    private CloudSecurityProperties cloudSecurityProperties;

    private RequestMatcher csrfRequestMatcher;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
        this.cloudSecurityProperties = cloudSecurityProperties;
    }

    @Autowired
    @Qualifier(CSRF_REQUEST_MATCHER_BEAN_NAME)
    public void setCsrfRequestMatcher(RequestMatcher csrfRequestMatcher) {
        this.csrfRequestMatcher = csrfRequestMatcher;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 查询登录用户
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                // 登录页面地址
                .loginPage(cloudSecurityProperties.getLoginPageUrl())
                // 登录请求地址
                .loginProcessingUrl(cloudSecurityProperties.getLoginProcessingUrl())
                // 登录失败地址
                .failureUrl(cloudSecurityProperties.getFailureAllUrl())
                // 登录成功地址
                .successForwardUrl(cloudSecurityProperties.getSuccessForwardUrl())
                // 已上地址，允许任何人访问
                .permitAll();

        // 登录服务（单服务）地址：匿名访问
        http.authorizeRequests().antMatchers(cloudSecurityProperties.getFailureUrl()).permitAll();

        // 端点放行
        http.authorizeRequests().antMatchers("/" + Constant.ACTUATOR + "/**").permitAll();

        // 所有地址，均需要认证后才能访问
        http.authorizeRequests().anyRequest().authenticated();

        // CSRF 配置
        http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher);

    }

    /**
     * 资源（静态）不拦截
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico");
        web.ignoring().antMatchers("/**/**.js");
        web.ignoring().antMatchers("/**/**.css");
    }

}
