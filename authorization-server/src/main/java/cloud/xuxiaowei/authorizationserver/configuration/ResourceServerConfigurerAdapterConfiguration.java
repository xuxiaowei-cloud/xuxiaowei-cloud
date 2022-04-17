package cloud.xuxiaowei.authorizationserver.configuration;

import cloud.xuxiaowei.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 资源服务配置
 *
 * @author xuxiaowei
 * @see <a href="http://127.0.0.1:1301/sns/userinfo?access_token=">访问资源</a>
 * @since 0.0.1
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigurerAdapterConfiguration extends ResourceServerConfigurerAdapter {

    private TokenStore tokenStore;

    private AuthenticationEntryPoint authenticationEntryPoint;

    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Autowired
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources.tokenStore(tokenStore);

        // 身份验证入口点
        resources.authenticationEntryPoint(authenticationEntryPoint);

        // 访问拒绝处理程序
        resources.accessDeniedHandler(accessDeniedHandler);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置资源路径 /sns/** 需要的权限 scope
        // http.antMatcher("/sns/**").authorizeRequests()
        //        .antMatchers("/sns/userinfo").access("#oauth2.hasAnyScope('snsapi_base','snsapi_userinfo')");

        // 端点放行
        http.authorizeRequests().antMatchers("/" + Constant.ACTUATOR + "/**").permitAll();

    }

}
