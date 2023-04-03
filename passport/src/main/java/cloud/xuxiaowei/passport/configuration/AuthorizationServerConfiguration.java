package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.core.properties.CloudClientProperties;
import cloud.xuxiaowei.core.properties.CloudJwkKeyProperties;
import cloud.xuxiaowei.passport.handler.AccessTokenAuthenticationFailureHandlerImpl;
import cloud.xuxiaowei.passport.service.impl.ClientPasswordEncoder;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.*;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.web.OAuth2AuthorizationEndpointFilter;
import org.springframework.security.oauth2.server.authorization.web.OAuth2TokenEndpointFilter;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Arrays;

/**
 * 授权服务器配置
 *
 * @author xuxiaowei
 * @see <a href="http://127.0.0.1:1301/.well-known/oauth-authorization-server">OAuth 2.0
 * 授权服务器元数据请求的默认端点URI</a>
 * @see <a href=
 * "http://127.0.0.1:1301/oauth2/authorize?client_id=xuxiaowei_client_id&redirect_uri=http://127.0.0.1:1401/code&response_type=code&scope=snsapi_base&state=beff3dfc-bad8-40db-b25f-e5459e3d6ad7">/oauth2/authorize</a>
 * @see <a href=
 * "https://docs.spring.io/spring-security/reference/6.0.0-M3/servlet/oauth2/resource-server/jwt.html#oauth2resourceserver-jwt-authorization-extraction">手动提取权限-6.0.0-M3</a>
 * @see <a href=
 * "https://github.com/spring-projects/spring-authorization-server/issues/181#issuecomment-756913042">将用户权限作为声明传播`Jwt`是一种自定义行为</a>
 * @see <a href=
 * "https://github.com/spring-projects/spring-authorization-server/blob/a30a1692b28915947a001f1e2a6d1e41a550eaa7/oauth2-authorization-server/src/test/java/org/springframework/security/config/annotation/web/configurers/oauth2/server/authorization/OidcTests.java#L264">自定义
 * Jwt 声明和标头官方示例代码</a>
 * @see <a href=
 * "https://github.com/spring-projects/spring-authorization-server/issues/199">自定义 Jwt
 * 声明和标头需要更灵活 议题</a>
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfiguration {

	private CloudJwkKeyProperties cloudJwkKeyProperties;

	private CloudClientProperties cloudClientProperties;

	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public void setCloudJwkKeyProperties(CloudJwkKeyProperties cloudJwkKeyProperties) {
		this.cloudJwkKeyProperties = cloudJwkKeyProperties;
	}

	@Autowired
	public void setCloudClientProperties(CloudClientProperties cloudClientProperties) {
		this.cloudClientProperties = cloudClientProperties;
	}

	@Autowired
	@Qualifier("revocationAuthenticationSuccessHandler")
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}

	/**
	 * @see <a href=
	 * "https://docs.spring.io/spring-authorization-server/docs/current/reference/html/protocol-endpoints.html">协议端点的</a>
	 * Spring Security 过滤器链。
	 * @see OAuth2AuthorizationServerConfiguration#applyDefaultSecurity(HttpSecurity) 默认
	 * OAuth 2.1 授权配置
	 * @see OAuth2AuthorizationEndpointFilter 默认 OAuth 2.1 授权页面
	 *
	 * @see OAuth2TokenEndpointFilter#setAuthenticationConverter(AuthenticationConverter)
	 * @see OAuth2TokenEndpointConfigurer#accessTokenRequestConverter(AuthenticationConverter)
	 * @see OAuth2TokenEndpointConfigurer#authenticationProvider(AuthenticationProvider)
	 * @see AnonymousAuthenticationProvider
	 * @see JwtClientAssertionAuthenticationProvider
	 * @see ClientSecretAuthenticationProvider
	 * @see PublicClientAuthenticationProvider
	 * @see OAuth2AuthorizationCodeRequestAuthenticationProvider
	 * @see OAuth2AuthorizationCodeAuthenticationProvider
	 * @see OAuth2RefreshTokenAuthenticationProvider
	 * @see OAuth2ClientCredentialsAuthenticationProvider
	 * @see OAuth2TokenIntrospectionAuthenticationProvider
	 * @see OAuth2TokenRevocationAuthenticationProvider
	 * @see OidcUserInfoAuthenticationProvider
	 * @see HttpSecurity#authenticationProvider(AuthenticationProvider)
	 */
	@Bean
	@Order(-1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
			RegisteredClientRepository registeredClientRepository, OAuth2AuthorizationService authorizationService)
			throws Exception {

		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = http
			.getConfigurer(OAuth2AuthorizationServerConfigurer.class);

		authorizationServerConfigurer
			.authorizationEndpoint(authorizationEndpointCustomizer -> authorizationEndpointCustomizer
				.consentPage(cloudClientProperties.getConsentPage()));

		// 自定义客户授权
		// @formatter:off
		authorizationServerConfigurer.tokenEndpoint(tokenEndpointCustomizer -> tokenEndpointCustomizer
			.accessTokenRequestConverter(new DelegatingAuthenticationConverter(Arrays.asList(
					// 新增：支付宝小程序 登录 OAuth2 用于验证授权授予的 {@link OAuth2AlipayMiniProgramAuthenticationToken}
					new OAuth2AlipayMiniProgramAuthenticationConverter(),
					// 新增：支付宝 登录 OAuth2 用于验证授权授予的 {@link OAuth2AlipayOplatformWebsiteAuthenticationToken}
					new OAuth2AlipayOplatformWebsiteAuthenticationConverter(),
					// 新增：钉钉 dingtalk 登录 OAuth2 用于验证授权授予的 {@link OAuth2DingtalkAuthenticationToken}
					new OAuth2DingtalkAuthenticationConverter(),
					// 新增：飞书网页应用 登录 OAuth2 用于验证授权授予的 {@link OAuth2FeiShuWebPageAuthenticationToken}
					new OAuth2FeiShuWebPageAuthenticationConverter(),
					// 新增：码云 Gitee 网站应用 OAuth2 用于验证授权授予的 {@link OAuth2GiteeAuthenticationToken}
					new OAuth2GiteeAuthenticationConverter(),
					// 新增：GitHub 登录 OAuth2 用于验证授权授予的 {@link OAuth2GitHubAuthenticationToken}
					new OAuth2GitHubAuthenticationConverter(),
					// 新增：GitLab 网站应用 OAuth2 用于验证授权授予的 {@link OAuth2GitLabAuthenticationToken}
					new OAuth2GitLabAuthenticationConverter(),
					// 新增：QQ小程序 登录 OAuth2 用于验证授权授予的 {@link OAuth2QQMiniProgramAuthenticationToken}
					new OAuth2QQMiniProgramAuthenticationConverter(),
					// 新增：QQ 网站应用 OAuth2 用于验证授权授予的 {@link OAuth2QQWebsiteAuthenticationToken}
					new OAuth2QQWebsiteAuthenticationConverter(),
					// 新增：微信小程序 OAuth2 用于验证授权授予的 {@link OAuth2WeChatMiniProgramAuthenticationToken}
					new OAuth2WeChatMiniProgramAuthenticationConverter(),
					// 新增：微信公众号 OAuth2 用于验证授权授予的 {@link OAuth2WeChatOffiaccountAuthenticationToken}
					new OAuth2WeChatOffiaccountAuthenticationConverter(),
					// 新增：微信开放平台 网站应用 OAuth2 用于验证授权授予的 {@link OAuth2WeChatOplatformWebsiteAuthenticationToken}
					new OAuth2WeChatOplatformWebsiteAuthenticationConverter(),
					// 新增：企业微信扫码登录 OAuth2 用于验证授权授予的 {@link OAuth2WeChatWorkWebsiteAuthenticationToken}
					new OAuth2WeChatWorkWebsiteAuthenticationConverter(),
					// 新增：微博 网站应用 OAuth2 用于验证授权授予的 {@link OAuth2WeiBoWebsiteAuthenticationToken}
					new OAuth2WeiBoWebsiteAuthenticationConverter()

					// 默认值：OAuth2 授权码认证转换器，已存在
					// new OAuth2AuthorizationCodeAuthenticationConverter()
					// 默认值：OAuth2 刷新令牌认证转换器，已存在
					// new OAuth2RefreshTokenAuthenticationConverter()
					// 默认值：OAuth2 客户端凭据身份验证转换器，已存在
					// new OAuth2ClientCredentialsAuthenticationConverter()
			)))
			// 用于处理失败的身份验证尝试的策略。
			.errorResponseHandler(new AccessTokenAuthenticationFailureHandlerImpl()));
		// @formatter:on

		// 支付宝 小程序 登录 OAuth2 身份验证提供程序
		new OAuth2AlipayMiniProgramAuthenticationProvider(http);
		// 支付宝 网站应用 登录 OAuth2 身份验证提供程序
		new OAuth2AlipayOplatformWebsiteAuthenticationProvider(http);
		// 钉钉 dingtalk 登录 OAuth2 身份验证提供程序
		new OAuth2DingtalkAuthenticationProvider(http);
		// 飞书网页应用 登录 OAuth2 身份验证提供程序
		new OAuth2FeiShuWebPageAuthenticationProvider(http);
		// 码云 Gitee 网站应用 OAuth2 身份验证提供程序
		new OAuth2GiteeAuthenticationProvider(http);
		// GitHub 登录 OAuth2 身份验证提供程序
		new OAuth2GitHubAuthenticationProvider(http);
		// GitLab 网站应用 OAuth2 身份验证提供程序
		new OAuth2GitLabAuthenticationProvider(http);
		// QQ 小程序 登录 OAuth2 身份验证提供程序
		new OAuth2QQMiniProgramAuthenticationProvider(http);
		// QQ 网站应用 OAuth2 身份验证提供程序
		new OAuth2QQWebsiteAuthenticationProvider(http);
		// 微信小程序 OAuth2 身份验证提供程序
		new OAuth2WeChatMiniProgramAuthenticationProvider(http);
		// 微信公众号 OAuth2 身份验证提供程序
		new OAuth2WeChatOffiaccountAuthenticationProvider(http);
		// 微信开放平台 网站应用 OAuth2 身份验证提供程序
		new OAuth2WeChatOplatformWebsiteAuthenticationProvider(http);
		// 企业微信扫码登录 OAuth2 身份验证提供程序
		new OAuth2WeChatWorkWebsiteAuthenticationProvider(http);
		// 微博 网站应用 OAuth2 身份验证提供程序
		new OAuth2WeiBoWebsiteAuthenticationProvider(http);

		authorizationServerConfigurer.tokenRevocationEndpoint(tokenRevocationEndpointCustomizer -> {
			// 自定义撤销授权成功后的处理
			tokenRevocationEndpointCustomizer.revocationResponseHandler(authenticationSuccessHandler);
		});

		authorizationServerConfigurer.clientAuthentication(clientAuthenticationCustomizer -> {
			ClientSecretAuthenticationProvider clientSecretAuthenticationProvider = new ClientSecretAuthenticationProvider(
					registeredClientRepository, authorizationService);
			ClientPasswordEncoder passwordEncoder = new ClientPasswordEncoder();
			// 自定义客户密码编辑器
			clientSecretAuthenticationProvider.setPasswordEncoder(passwordEncoder);
			clientAuthenticationCustomizer.authenticationProvider(clientSecretAuthenticationProvider);
		});

		return http.build();
	}

	/**
	 * @see JWKSource 用于签署访问令牌的实例。
	 */
	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		RSAKey rsaKey = new RSAKey.Builder(cloudJwkKeyProperties.rsaPublicKey())
			.privateKey(cloudJwkKeyProperties.privateKey())
			.build();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

	/**
	 * {@link AuthorizationServerSettings} 配置 Spring Authorization Server 的实例。
	 * @see <a href=
	 * "https://github.com/spring-projects/spring-authorization-server/commit/c60ae4532f1d745bff6eb793113731aba0493b70">Rename
	 * ProviderSettings</a>
	 */
	@Bean
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}

}
