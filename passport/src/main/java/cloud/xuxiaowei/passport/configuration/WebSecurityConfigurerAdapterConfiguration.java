package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.core.properties.CloudRememberMeProperties;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static cloud.xuxiaowei.passport.service.impl.DefaultCsrfRequestMatcherImpl.CSRF_REQUEST_MATCHER_BEAN_NAME;

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
@SuppressWarnings({"deprecation"})
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    private CloudSecurityProperties cloudSecurityProperties;

    private CloudRememberMeProperties cloudRememberMeProperties;

    private RequestMatcher csrfRequestMatcher;

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setCloudRememberMeProperties(CloudRememberMeProperties cloudRememberMeProperties) {
        this.cloudRememberMeProperties = cloudRememberMeProperties;
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

    @Autowired
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
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
                // 身份验证失败处理程序
                .failureHandler(authenticationFailureHandler)
                // 登录成功后的处理，重定向到某个地址
                .successHandler(authenticationSuccessHandler)
                // 已上地址，允许任何人访问
                .permitAll();

        http.rememberMe().rememberMeParameter(cloudRememberMeProperties.getRememberMeParameter()).rememberMeCookieName(cloudRememberMeProperties.getRememberMeCookieName()).rememberMeCookieDomain(cloudRememberMeProperties.getRememberMeCookieDomain()).key(cloudRememberMeProperties.getKey()).tokenValiditySeconds(cloudRememberMeProperties.getTokenValiditySeconds());

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
