package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.map.ResponseMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2RefreshTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2RefreshTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.context.ProviderContext;
import org.springframework.security.oauth2.server.authorization.settings.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2RefreshTokenAuthenticationConverter;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Token
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/token")
public class TokenController {

	private ProviderSettings providerSettings;

	private RegisteredClientRepository registeredClientRepository;

	private OAuth2AuthorizationService authorizationService;

	private OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

	public void setProviderSettings(ProviderSettings providerSettings) {
		this.providerSettings = providerSettings;
	}

	public void setRegisteredClientRepository(RegisteredClientRepository registeredClientRepository) {
		this.registeredClientRepository = registeredClientRepository;
	}

	public void setAuthorizationService(OAuth2AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	public void setTokenGenerator(OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
	}

	/**
	 * @see OAuth2RefreshTokenAuthenticationConverter
	 * @see OAuth2RefreshTokenAuthenticationProvider
	 * @param clientId
	 * @param clientSecret
	 * @param refreshToken
	 * @return
	 */
	@RequestMapping("/refresh")
	public Response<?> refresh(HttpServletRequest request, String clientId, String clientSecret, String refreshToken) {

		if (!StringUtils.hasText(refreshToken)) {
			return Response.error("Token 不能为空");
		}

		RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
		if (registeredClient == null) {
			return Response.error("未找到客户");
		}

		PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		boolean matches = delegatingPasswordEncoder.matches(clientSecret, registeredClient.getClientSecret());
		if (!matches) {
			return Response.error("密码不匹配");
		}

		OAuth2Authorization authorization = authorizationService.findByToken(refreshToken,
				OAuth2TokenType.REFRESH_TOKEN);

		if (authorization == null) {
			return Response.error("未找到刷新Token");
		}

		if (!registeredClient.getId().equals(authorization.getRegisteredClientId())) {
			return Response.error("Token 与 客户不匹配");
		}

		if (!registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN)) {
			return Response.error("客户无刷新Token的权限");
		}

		Map<String, Object> additionalParameters = new HashMap<>(8);

		Authentication clientPrincipal = new OAuth2ClientAuthenticationToken(clientId,
				ClientAuthenticationMethod.CLIENT_SECRET_POST, clientSecret, additionalParameters);
		Set<String> scopes = registeredClient.getScopes();

		OAuth2RefreshTokenAuthenticationToken refreshTokenAuthentication = new OAuth2RefreshTokenAuthenticationToken(
				refreshToken, clientPrincipal, scopes, additionalParameters);

		ProviderContext providerContext = new ProviderContext(this.providerSettings,
				() -> resolveIssuer(providerSettings, request));

		OAuth2Authorization.Token<OAuth2RefreshToken> oauth2RefreshToken = authorization.getRefreshToken();
		if (!oauth2RefreshToken.isActive()) {
			// As per https://tools.ietf.org/html/rfc6749#section-5.2
			// invalid_grant: The provided authorization grant (e.g., authorization code,
			// resource owner credentials) or refresh token is invalid, expired, revoked
			// [...].
			throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_GRANT);
		}

		// @formatter:off
		DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
				.registeredClient(registeredClient)
				.principal(authorization.getAttribute(Principal.class.getName()))
				.providerContext(providerContext)
				.authorization(authorization)
				.authorizedScopes(scopes)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.authorizationGrant(refreshTokenAuthentication);
		// @formatter:on

		OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.from(authorization);

		OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
		OAuth2Token generatedAccessToken = tokenGenerator.generate(tokenContext);

		OAuth2RefreshToken currentRefreshToken = oauth2RefreshToken.getToken();
		// 刷新Token是否可以重复使用
		if (!registeredClient.getTokenSettings().isReuseRefreshTokens()) {
			tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
			OAuth2Token generatedRefreshToken = tokenGenerator.generate(tokenContext);
			currentRefreshToken = (OAuth2RefreshToken) generatedRefreshToken;
			authorizationBuilder.refreshToken(currentRefreshToken);
		}

		authorization = authorizationBuilder.build();

		authorizationService.save(authorization);

		return ResponseMap.ok().put("accessToken", generatedAccessToken).put("refreshToken", currentRefreshToken);
	}

	private static String resolveIssuer(ProviderSettings providerSettings, HttpServletRequest request) {
		return providerSettings.getIssuer() != null ? providerSettings.getIssuer() : getContextPath(request);
	}

	private static String getContextPath(HttpServletRequest request) {
		// @formatter:off
		return UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
				.replacePath(request.getContextPath())
				.replaceQuery(null)
				.fragment(null)
				.build()
				.toUriString();
		// @formatter:on
	}

}
