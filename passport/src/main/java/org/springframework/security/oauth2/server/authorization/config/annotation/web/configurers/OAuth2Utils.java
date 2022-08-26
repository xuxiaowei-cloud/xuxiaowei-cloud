package org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * OAuth2 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class OAuth2Utils {

	public static RegisteredClientRepository getRegisteredClientRepository(HttpSecurity httpSecurity) {
		return OAuth2ConfigurerUtils.getRegisteredClientRepository(httpSecurity);
	}

	public static OAuth2AuthorizationService getAuthorizationService(HttpSecurity httpSecurity) {
		return OAuth2ConfigurerUtils.getAuthorizationService(httpSecurity);
	}

	public static OAuth2TokenGenerator<? extends OAuth2Token> getTokenGenerator(HttpSecurity httpSecurity) {
		return OAuth2ConfigurerUtils.getTokenGenerator(httpSecurity);
	}

	public static ProviderSettings getProviderSettings(HttpSecurity httpSecurity) {
		return OAuth2ConfigurerUtils.getProviderSettings(httpSecurity);
	}

}
