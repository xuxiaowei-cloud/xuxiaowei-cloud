package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.ResponseUtils;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.utils.exception.login.LoginException;
import cloud.xuxiaowei.utils.exception.oauth2.LoginAuthenticationException;
import cloud.xuxiaowei.utils.map.ResponseMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.WeChatOplatformWebsiteAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.DefaultMapOAuth2AccessTokenResponseConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.client.InMemoryWeChatOplatformWebsiteService;
import org.springframework.security.oauth2.server.authorization.client.WeChatOplatformWebsiteService;
import org.springframework.security.oauth2.server.authorization.exception.RedirectWeChatOplatformException;
import org.springframework.security.oauth2.server.authorization.properties.WeChatOplatformWebsiteProperties;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 微信开放平台 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class WeChatOplatformConfiguration {

	private IUsersService usersService;

	private WeChatOplatformWebsiteProperties weChatOplatformWebsiteProperties;

	private CloudSecurityProperties cloudSecurityProperties;

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	@Autowired
	public void setWeChatOplatformWebsiteProperties(WeChatOplatformWebsiteProperties weChatOplatformWebsiteProperties) {
		this.weChatOplatformWebsiteProperties = weChatOplatformWebsiteProperties;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Bean
	public WeChatOplatformWebsiteService weChatOplatformWebsiteService() {

		return new InMemoryWeChatOplatformWebsiteService(weChatOplatformWebsiteProperties) {

			@Override
			public AbstractAuthenticationToken authenticationToken(Authentication clientPrincipal,
					Map<String, Object> additionalParameters, Object details, String appid, String code, String openid,
					Object credentials, String unionid, String accessToken, String refreshToken, Integer expiresIn,
					String scope) {

				// 这里需要使用 openid、unionid 查找绑定的用户

				String username;

				Users users = usersService.loadUserByUsername("xuxiaowei");
				if (users == null) {
					OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未找到微信绑定的用户", null);
					throw new LoginAuthenticationException(error);
				}

				username = users.getUsername();

				List<GrantedAuthority> authorities = new ArrayList<>();

				List<Authorities> authoritiesList = users.getAuthoritiesList();

				boolean allowEmptyAuthorities = cloudSecurityProperties.isAllowEmptyAuthorities();
				if (!allowEmptyAuthorities && authoritiesList.size() == 0) {
					throw new LoginException(CodeEnums.A10011.code, CodeEnums.A10011.msg);
				}

				for (Authorities auth : authoritiesList) {
					SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth.getAuthority());
					authorities.add(authority);
				}

				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						weChatOplatformWebsiteProperties.getDefaultRole());
				authorities.add(authority);
				User user = new User(username, accessToken, authorities);

				UsernamePasswordAuthenticationToken principal = UsernamePasswordAuthenticationToken.authenticated(user,
						null, user.getAuthorities());

				WeChatOplatformWebsiteAuthenticationToken authenticationToken = new WeChatOplatformWebsiteAuthenticationToken(
						authorities, clientPrincipal, principal, user, additionalParameters, details, appid, code,
						openid);

				authenticationToken.setCredentials(credentials);
				authenticationToken.setUnionid(unionid);

				return authenticationToken;
			}

			@Override
			public void sendRedirect(HttpServletRequest request, HttpServletResponse response,
					Map<String, String> uriVariables, OAuth2AccessTokenResponse oauth2AccessTokenResponse,
					WeChatOplatformWebsiteProperties.WeChatOplatformWebsite weChatOplatformWebsite) {
				OAuth2AccessToken oauth2AccessToken = oauth2AccessTokenResponse.getAccessToken();
				OAuth2RefreshToken oauth2RefreshToken = oauth2AccessTokenResponse.getRefreshToken();

				String successUrl = weChatOplatformWebsite.getSuccessUrl();
				String accessToken = oauth2AccessToken.getTokenValue();
				String refreshToken = "";
				if (oauth2RefreshToken != null) {
					refreshToken = oauth2RefreshToken.getTokenValue();
				}

				try {
					response.sendRedirect(String.format("%s?store=true&accessToken=%s&refreshToken=%s", successUrl,
							accessToken, refreshToken));
				}
				catch (IOException e) {
					OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "微信开放平台 网站应用重定向异常", null);
					throw new RedirectWeChatOplatformException(error);
				}
			}

			@Override
			public OAuth2AccessTokenResponse getOAuth2AccessTokenResponse(HttpServletRequest request,
					HttpServletResponse response, String tokenUrlPrefix, String tokenUrl,
					Map<String, String> uriVariables) throws OAuth2AuthenticationException {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
				RestTemplate restTemplate = new RestTemplate();
				List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
				messageConverters.add(5, new OAuth2AccessTokenResponseHttpMessageConverter());
				String postForObject = restTemplate.postForObject(tokenUrlPrefix + tokenUrl, httpEntity, String.class,
						uriVariables);

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				ResponseMap responseMap;
				try {
					responseMap = objectMapper.readValue(postForObject, ResponseMap.class);
				}
				catch (JsonProcessingException e) {
					throw new CloudRuntimeException(e);
				}

				String code = responseMap.getCode();
				if (code == null) {
					// 不存在响应代码，代表响应的为 OAuth 2.1 授权 Token

					Map<String, Object> map;
					try {
						map = objectMapper.readValue(postForObject, new TypeReference<Map<String, Object>>() {
						});
					}
					catch (JsonProcessingException e) {
						throw new CloudRuntimeException(e);
					}

					DefaultMapOAuth2AccessTokenResponseConverter converter = new DefaultMapOAuth2AccessTokenResponseConverter();
					return converter.convert(map);

				}
				else {
					// 存在响应代码时，代表程序报错，直接响应错误内容

					try {
						ResponseUtils.response(response, responseMap);
						return null;
					}
					catch (IOException e) {
						throw new CloudRuntimeException(e);
					}
				}
			}
		};
	}

}
