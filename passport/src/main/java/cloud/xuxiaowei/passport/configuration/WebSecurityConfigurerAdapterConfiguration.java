package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.core.properties.CloudJwkKeyProperties;
import cloud.xuxiaowei.core.properties.CloudRememberMeProperties;
import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import org.springdoc.core.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.web.OAuth2TokenRevocationEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.security.interfaces.RSAPublicKey;

import static cloud.xuxiaowei.oauth2.matcher.CsrfRequestMatcher.CSRF_REQUEST_MATCHER_BEAN_NAME;

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
public class WebSecurityConfigurerAdapterConfiguration {

	private CloudJwkKeyProperties cloudJwkKeyProperties;

	private AccessDeniedHandler accessDeniedHandler;

	private AuthenticationEntryPoint authenticationEntryPoint;

	private UserDetailsService userDetailsService;

	private CloudSecurityProperties cloudSecurityProperties;

	private CloudRememberMeProperties cloudRememberMeProperties;

	private RequestMatcher csrfRequestMatcher;

	private AuthenticationSuccessHandler authenticationSuccessHandler;

	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	public void setCloudJwkKeyProperties(CloudJwkKeyProperties cloudJwkKeyProperties) {
		this.cloudJwkKeyProperties = cloudJwkKeyProperties;
	}

	@Autowired
	public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
		this.accessDeniedHandler = accessDeniedHandler;
	}

	@Autowired
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Autowired
	public void setCloudRememberMeProperties(CloudRememberMeProperties cloudRememberMeProperties) {
		this.cloudRememberMeProperties = cloudRememberMeProperties;
	}

	@Autowired
	@Qualifier(CSRF_REQUEST_MATCHER_BEAN_NAME)
	public void setCsrfRequestMatcher(RequestMatcher csrfRequestMatcher) {
		this.csrfRequestMatcher = csrfRequestMatcher;
	}

	@Autowired
	@Qualifier("authenticationSuccessHandlerImpl")
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}

	@Autowired
	@Qualifier("authenticationFailureHandlerImpl")
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> {
			web.ignoring().antMatchers("/favicon.ico");
			web.ignoring().antMatchers("/**/**.js");
			web.ignoring().antMatchers("/**/**.css");
			web.ignoring().antMatchers("/**/**.png");
			web.ignoring().antMatchers(Constants.SWAGGER_UI_PREFIX + "/**");
			web.ignoring().antMatchers(Constants.DEFAULT_API_DOCS_URL + "/**");
		};
	}

	/**
	 * @see <a href=
	 * "https://docs.spring.io/spring-security/reference/servlet/authentication/index.html">用于身份验证</a>
	 * 的 Spring Security 过滤器链。
	 */
	@Bean
	@Order(0)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// 资源服务配置秘钥
		http.oauth2ResourceServer().jwt(oauth2ResourceServer -> {
			RSAPublicKey rsaPublicKey = cloudJwkKeyProperties.rsaPublicKey();
			NimbusJwtDecoder.PublicKeyJwtDecoderBuilder publicKeyJwtDecoderBuilder = NimbusJwtDecoder
				.withPublicKey(rsaPublicKey);
			NimbusJwtDecoder nimbusJwtDecoder = publicKeyJwtDecoderBuilder.build();
			oauth2ResourceServer.decoder(nimbusJwtDecoder);
		});

		// 异常处理
		http.exceptionHandling(exceptionHandlingCustomizer -> {
			exceptionHandlingCustomizer
				// 访问被拒绝处理程序
				.accessDeniedHandler(accessDeniedHandler)
				// 身份验证入口点
				.authenticationEntryPoint(authenticationEntryPoint);
		});

		// 用于检索用户进行身份验证的实例
		http.userDetailsService(userDetailsService);

		// 路径权限控制
		// @formatter:off
		http.authorizeHttpRequests((authorize) -> {
			authorize
				// 放行端点
				.antMatchers("/actuator/**").permitAll()
				// # 健康检查路径（此处为冗余配置，用于k8s探针/健康检查、滚动发布）
				.antMatchers("/actuator/health", "/actuator/health/liveness", "/actuator/health/readiness").permitAll()
				// 放行授权路径
				.antMatchers("/oauth2/authorize").permitAll()
				// 放行检查Token
				.antMatchers("/oauth2/check_token").permitAll()
				// 放行Token
				.antMatchers("/oauth2/token").permitAll()
				// OAuth2 撤销端点
				.antMatchers("/oauth2/revoke").permitAll()
				// 授权码
				.antMatchers("/code").permitAll()
				// 注销登录放行
				.antMatchers("/signout").permitAll()
				// 找回密码
				.antMatchers("/forget").permitAll()
				// 检查重置密码凭证
				.antMatchers("/forget/check-reset-password-token").permitAll()
				// 重置密码
				.antMatchers("/forget/reset-password").permitAll()
				// 重置密码（手机验证码）
				.antMatchers("/forget/reset-type-phone-password").permitAll()
				// 配置
				.antMatchers("/configuration").permitAll()
				// 放行错误地址
				.antMatchers("/error").permitAll()

				// 支付宝 网站应用 登录 跳转到 支付宝 网站应用 授权页面
				.antMatchers("/alipay-oplatform/website/authorize/*").permitAll()
				// 支付宝 网站应用 登录 授权码接收服务
				.antMatchers("/alipay-oplatform/website/code/*").permitAll()
				// 钉钉 dingtalk 登录 跳转到 钉钉 dingtalk 授权页面
				.antMatchers("/dingtalk/authorize/*").permitAll()
				// 钉钉 dingtalk 登录 授权码接收服务
				.antMatchers("/dingtalk/code/*").permitAll()
				// 飞书网页应用 跳转到 飞书网页应用 授权页面
				.antMatchers("/feishu-webpage/authorize/*").permitAll()
				// 飞书网页应用 登录 授权码接收服务
				.antMatchers("/feishu-webpage/code/*").permitAll()
				// 码云 Gitee 网站应用 跳转到码云Gitee授权页面
				.antMatchers("/gitee/authorize/*").permitAll()
				// 码云 Gitee 网站应用 授权码接收服务
				.antMatchers("/gitee/code/*").permitAll()
				// Github 登录 跳转到Github授权页面
				.antMatchers("/github/authorize/*").permitAll()
				// Github 登录 授权码接收服务
				.antMatchers("/github/code/*").permitAll()
				// GitLab 网站应用 跳转到GitLab授权页面
				.antMatchers("/gitlab/authorize/*").permitAll()
				// GitLab 网站应用 授权码接收服务
				.antMatchers("/gitlab/code/*").permitAll()
				// QQ 网站应用 跳转到微信授权页面
				.antMatchers("/qq/website/authorize/*").permitAll()
				// QQ 网站应用 授权码接收服务
				.antMatchers("/qq/website/code/*").permitAll()
				// 微信公众号跳转到微信授权页面
				.antMatchers("/wechat-offiaccount/authorize/*").permitAll()
				// 微信公众号授权码接收服务
				.antMatchers("/wechat-offiaccount/code/*").permitAll()
				// 微信开放平台 网站应用 跳转到微信授权页面
				.antMatchers("/wechat-oplatform/website/authorize/*").permitAll()
				// 微信开放平台 网站应用 授权码接收服务
				.antMatchers("/wechat-oplatform/website/code/*").permitAll()
				// 企业微信扫码登录 跳转到企业微信授权页面
				.antMatchers("/wechat-work/website/authorize/*/*").permitAll()
				// 企业微信扫码登录 授权码接收服务
				.antMatchers("/wechat-work/website/code/*/*").permitAll()
				// 微博 网站应用 跳转到微博授权页面
				.antMatchers("/weibo/authorize/*").permitAll()
				// 微博 网站应用 授权码接收服务
				.antMatchers("/weibo/code/*").permitAll()

				// 分页查询租户-登录页面
				.antMatchers("/tenant/page/login").permitAll()

				// 其他路径均需要授权
				.anyRequest().authenticated();
		});
		// @formatter:on

		http.formLogin(formLogin -> formLogin
			// 登录页面地址
			.loginPage(cloudSecurityProperties.getLoginPageUrl())
			// 登录请求地址
			.loginProcessingUrl(cloudSecurityProperties.getLoginProcessingUrl())
			// 身份验证失败处理程序
			.failureHandler(authenticationFailureHandler)
			// 登录成功后的处理，重定向到某个地址
			.successHandler(authenticationSuccessHandler)
			// 已上地址，允许任何人访问
			.permitAll());

		http.rememberMe(rememberMe -> rememberMe
			// 查询用户
			.userDetailsService(userDetailsService)
			// 记住我参数名
			.rememberMeParameter(cloudRememberMeProperties.getRememberMeParameter())
			// 记住我 Cookie 名
			.rememberMeCookieName(cloudRememberMeProperties.getRememberMeCookieName())
			// 记住我域名
			.rememberMeCookieDomain(cloudRememberMeProperties.getRememberMeCookieDomain())
			// 秘钥
			.key(cloudRememberMeProperties.getKey())
			// 记住我 Token 有效时间
			.tokenValiditySeconds(cloudRememberMeProperties.getTokenValiditySeconds()));

		// CSRF 配置
		http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher);

		OAuth2TokenRevocationEndpointFilter sharedObject = http
			.getSharedObject(OAuth2TokenRevocationEndpointFilter.class);

		return http.build();
	}

}
