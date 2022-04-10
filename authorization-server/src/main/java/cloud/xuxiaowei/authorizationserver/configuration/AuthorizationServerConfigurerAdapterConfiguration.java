package cloud.xuxiaowei.authorizationserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerTokenServicesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.endpoint.*;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.security.Principal;
import java.util.Map;

/**
 * 授权服务 配置
 *
 * @author xuxiaowei
 * @see DefaultLoginPageGeneratingFilter 默认登录页面
 * @see OAuth2ClientAuthenticationProcessingFilter 一个 OAuth2 客户端过滤器，可用于从授权服务器获取 OAuth2 访问令牌，并将身份验证对象加载到 SecurityContext
 * @see AuthorizationServerEndpointsConfiguration#authorizationEndpoint() 自定义页面
 * @see AuthorizationServerSecurityConfiguration Token 相关路径权限配置
 * @see ClientCredentialsTokenEndpointFilter OAuth2 令牌端点的过滤器和身份验证端点。
 * @see WhitelabelApprovalEndpoint 用于显示授权服务器的批准页面的控制器。
 * @see WhitelabelErrorEndpoint 用于显示授权服务器错误页面的控制器。
 * @see AuthorizationEndpoint#authorize(Map, Map, SessionStatus, Principal) 授权码
 * @see AuthorizationEndpoint#approveOrDeny(Map, Map, SessionStatus, Principal) 授权码
 * @see TokenEndpoint#getAccessToken(Principal, Map) 获取 Token
 * @see TokenEndpoint#postAccessToken(Principal, Map) 获取 Token
 * @see TokenKeyEndpoint#getKey(Principal) 获取 令牌签名的验证密钥
 * @see CheckTokenEndpoint#checkToken(String) 检查 Token
 * @see WhitelabelErrorEndpoint#handleError(HttpServletRequest) 异常
 * @see JwtAccessTokenConverter#enhance(OAuth2AccessToken, OAuth2Authentication) 增强Token
 * @see JwtTokenStore#readAccessToken(String) 解密Token
 * @see AuthorizationServerTokenServicesConfiguration
 * @see ExceptionHandlerExceptionResolver
 * @see <a href="http://authorization-server.example.xuxiaowei.cloud:1301/oauth/authorize?client_id=xuxiaowei_client_id&redirect_uri=http://passport.example.xuxiaowei.cloud:1411/code&response_type=code&scope=snsapi_base&state=beff3dfc-bad8-40db-b25f-e5459e3d6ad7">获取 code</a>
 * @see <a href="http://authorization-server.example.xuxiaowei.cloud:1301/oauth/authorize?client_id=xuxiaowei_client_id&redirect_uri=http://passport.example.xuxiaowei.cloud:1411/code&response_type=code&scope=snsapi_userinfo&state=beff3dfc-bad8-40db-b25f-e5459e3d6ad7">获取 code</a>
 * @see <a href="http://authorization-server.example.xuxiaowei.cloud:1301/oauth/authorize?client_id=xuxiaowei_client_id&redirect_uri=http://passport.example.xuxiaowei.cloud:1411/code&response_type=token&scope=snsapi_base&state=beff3dfc-bad8-40db-b25f-e5459e3d6ad7">获取 Token（implicit，简化模式）</a>
 * @see <a href="http://authorization-server.example.xuxiaowei.cloud:1301/oauth/token?code=&client_id=xuxiaowei_client_id&client_secret=xuxiaowei_client_secret&redirect_uri=http://passport.example.xuxiaowei.cloud:1411/code&grant_type=authorization_code">获取 Token</a>
 * @see <a href="http://authorization-server.example.xuxiaowei.cloud:1301/oauth/token?client_id=xuxiaowei_client_id&client_secret=xuxiaowei_client_secret&grant_type=refresh_token&refresh_token=">刷新 Token</a>
 * @see <a href="http://authorization-server.example.xuxiaowei.cloud:1301/oauth/token?grant_type=client_credentials&client_id=xuxiaowei_client_id&client_secret=xuxiaowei_client_secret&scope=snsapi_base%20snsapi_userinfo">凭证式 client_credentials</a>
 * @see <a href="http://authorization-server.example.xuxiaowei.cloud:1301/oauth/check_token?token=">检查 Token（需要使用 POST）</a>
 * @since 0.0.1
 */
@SuppressWarnings({"deprecation"})
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurerAdapterConfiguration extends AuthorizationServerConfigurerAdapter {

    private DataSource dataSource;

    private TokenStore tokenStore;

    private TokenEnhancer tokenEnhancer;

    private AuthorizationCodeServices authorizationCodeServices;

    private UserDetailsService userDetailsService;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Autowired
    public void setTokenEnhancer(TokenEnhancer tokenEnhancer) {
        this.tokenEnhancer = tokenEnhancer;
    }

    @Autowired
    public void setAuthorizationCodeServices(AuthorizationCodeServices authorizationCodeServices) {
        this.authorizationCodeServices = authorizationCodeServices;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        // 获取 Token：允许所有
        security.tokenKeyAccess("permitAll()");
        // 检查 Token：允许所有
        security.checkTokenAccess("permitAll()");
        // 允许 Client 进行表单验证（URL），否则将出现弹窗输入 ClientId、ClientSecret
        security.allowFormAuthenticationForClients();

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 查询客户端
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 获取 Token 可使用 GET、POST
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        // 刷新 Token 时查询用户
        endpoints.userDetailsService(userDetailsService);

        // code 持久化
        endpoints.authorizationCodeServices(authorizationCodeServices);

        // Token 缓存方式
        endpoints.tokenStore(tokenStore);

        // Token 增强
        endpoints.tokenEnhancer(tokenEnhancer);

        // 自定义显示授权服务器的批准页面。
        endpoints.pathMapping("/oauth/confirm_access", "/oauth/customize_confirm_access");

        // 自定义 用于显示授权服务器的错误页面（响应）。
        endpoints.pathMapping("/oauth/error", "/oauth/customize_error");

    }

}
