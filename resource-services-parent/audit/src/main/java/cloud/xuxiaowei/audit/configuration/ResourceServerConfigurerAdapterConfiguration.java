package cloud.xuxiaowei.audit.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务配置
 *
 * @author xuxiaowei
 * @see <a href="http://127.0.0.1:1501/sns/userinfo?access_token=">访问资源</a>
 * @since 0.0.1
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigurerAdapterConfiguration extends ResourceServerConfigurerAdapter {

    private TokenStore tokenStore;

    @Autowired
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources.tokenStore(tokenStore);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置资源路径 /sns/** 需要的权限 scope
        http.antMatcher("/sns/**").authorizeRequests()
                .antMatchers("/sns/userinfo").access("#oauth2.hasAnyScope('snsapi_base','snsapi_userinfo')");

        // OAuth2 授权后可访问
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/**").access("#oauth2.isOAuth()");

    }

}
