package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.entity.AlipayOplatformWebsiteUsers;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IAlipayOplatformWebsiteUsersService;
import cloud.xuxiaowei.system.service.SessionService;
import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.ResponseUtils;
import cloud.xuxiaowei.utils.SecurityUtils;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.utils.exception.login.LoginException;
import cloud.xuxiaowei.utils.exception.oauth2.LoginAuthenticationException;
import cloud.xuxiaowei.utils.map.ResponseMap;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AlipayOplatformWebsiteAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
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
import org.springframework.security.oauth2.server.authorization.client.*;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.exception.RedirectUriAlipayOplatformException;
import org.springframework.security.oauth2.server.authorization.properties.AlipayOplatformWebsiteProperties;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 支付宝 网站应用 账户服务接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 * @see RegisteredClientRepository
 * @see InMemoryRegisteredClientRepository
 * @see JdbcRegisteredClientRepository
 */
@Slf4j
@Service
public class AlipayOplatformWebsiteServiceImpl implements AlipayOplatformWebsiteService {

	private final static String ALIPAY_OPLATFORM_WEBSITE_STATE_PREFIX = "alipay_oplatform_website_state_prefix";

	private final static String ALIPAY_OPLATFORM_WEBSITE_BINDING_PREFIX = "alipay_oplatform_website_binding_prefix";

	private final static String ALIPAY_OPLATFORM_WEBSITE_USERS_PREFIX = "alipay_oplatform_website_users_prefix";

	private AlipayOplatformWebsiteProperties alipayOplatformWebsiteProperties;

	private CloudSecurityProperties cloudSecurityProperties;

	private IAlipayOplatformWebsiteUsersService alipayOplatformWebsiteUsersService;

	private SessionService sessionService;

	@Autowired
	public void setAlipayOplatformWebsiteProperties(AlipayOplatformWebsiteProperties alipayOplatformWebsiteProperties) {
		this.alipayOplatformWebsiteProperties = alipayOplatformWebsiteProperties;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Autowired
	public void setAlipayOplatformWebsiteUsersService(
			IAlipayOplatformWebsiteUsersService alipayOplatformWebsiteUsersService) {
		this.alipayOplatformWebsiteUsersService = alipayOplatformWebsiteUsersService;
	}

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	/**
	 * 根据 appid 获取重定向的地址
	 * @param appid 开放平台 网站应用 ID
	 * @return 返回重定向的地址
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public String getRedirectUriByAppid(String appid) throws OAuth2AuthenticationException {
		InMemoryAlipayOplatformWebsiteService gitLabService = new InMemoryAlipayOplatformWebsiteService(
				alipayOplatformWebsiteProperties);
		return gitLabService.getRedirectUriByAppid(appid);
	}

	/**
	 * 生成状态码
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @return 返回生成的授权码
	 */
	@Override
	public String stateGenerate(HttpServletRequest request, HttpServletResponse response, String appid) {
		String state = UUID.randomUUID().toString();
		sessionService.set(ALIPAY_OPLATFORM_WEBSITE_STATE_PREFIX + ":" + appid + ":" + state, state, 30,
				TimeUnit.MINUTES);
		return state;
	}

	/**
	 * 储存绑定参数
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param state 状态码
	 * @param binding 绑定参数
	 */
	@Override
	public void storeBinding(HttpServletRequest request, HttpServletResponse response, String appid, String state,
			String binding) {
		if (binding != null) {
			sessionService.set(ALIPAY_OPLATFORM_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + state, binding, 30,
					TimeUnit.MINUTES);
		}
	}

	/**
	 * 储存操作用户
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param state 状态码
	 * @param binding 绑定参数
	 */
	@Override
	public void storeUsers(HttpServletRequest request, HttpServletResponse response, String appid, String state,
			String binding) {
		Long usersId = SecurityUtils.getUsersId();
		sessionService.set(ALIPAY_OPLATFORM_WEBSITE_USERS_PREFIX + ":" + appid + ":" + state, usersId + "", 30,
				TimeUnit.MINUTES);
	}

	/**
	 * 状态码验证（返回 {@link Boolean#FALSE} 时，将终止后面需要执行的代码）
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param code 授权码
	 * @param state 状态码
	 * @return 返回 状态码验证结果
	 */
	@SneakyThrows
	@Override
	public boolean stateValid(HttpServletRequest request, HttpServletResponse response, String appid, String code,
			String state) {
		String string = sessionService.get(ALIPAY_OPLATFORM_WEBSITE_STATE_PREFIX + ":" + appid + ":" + state);
		if (!StringUtils.hasText(string)) {
			Response<?> error = Response.error("非法状态码");
			ResponseUtils.response(response, error);
			return false;
		}
		else if (string.equals(state)) {
			sessionService.remove(ALIPAY_OPLATFORM_WEBSITE_STATE_PREFIX + ":" + appid + ":" + state);
			return true;
		}
		else {
			Response<?> error = Response.error("非法状态码");
			ResponseUtils.response(response, error);
			return false;
		}
	}

	/**
	 * 获取 绑定参数
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param code 授权码
	 * @param state 状态码
	 * @return 返回 绑定参数
	 */
	@Override
	public String getBinding(HttpServletRequest request, HttpServletResponse response, String appid, String code,
			String state) {
		String binding = sessionService.get(ALIPAY_OPLATFORM_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + state);
		sessionService.remove(ALIPAY_OPLATFORM_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + state);
		return binding;
	}

	/**
	 * 根据 appid 获取 支付宝 网站应用属性配置
	 * @param appid 公众号ID
	 * @return 返回 支付宝 网站应用属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AlipayOplatformWebsiteProperties.AlipayOplatformWebsite getAlipayOplatformWebsiteByAppid(String appid)
			throws OAuth2AuthenticationException {
		InMemoryAlipayOplatformWebsiteService gitLabService = new InMemoryAlipayOplatformWebsiteService(
				alipayOplatformWebsiteProperties);
		return gitLabService.getAlipayOplatformWebsiteByAppid(appid);
	}

	/**
	 * 获取 OAuth 2.1 授权 Token（如果不想执行此方法后面的内容，可返回 null）
	 * @param request 请求
	 * @param response 响应
	 * @param tokenUrlPrefix 获取 Token URL 前缀
	 * @param tokenUrl Token URL
	 * @param uriVariables 参数
	 * @return 返回 OAuth 2.1 授权 Token
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public OAuth2AccessTokenResponse getOAuth2AccessTokenResponse(HttpServletRequest request,
			HttpServletResponse response, String tokenUrlPrefix, String tokenUrl, Map<String, String> uriVariables)
			throws OAuth2AuthenticationException {
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

	/**
	 * 根据 AppID、code、accessTokenUrl 获取Token
	 * @param appid AppID
	 * @param code 授权码
	 * @param state 状态码
	 * @param binding 是否绑定，需要使用者自己去拓展
	 * @param remoteAddress 用户IP
	 * @param sessionId SessionID
	 * @return 返回 支付宝授权结果
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AlipayOplatformWebsiteTokenResponse getAccessTokenResponse(String appid, String code, String state,
			String binding, String remoteAddress, String sessionId) throws OAuth2AuthenticationException {
		InMemoryAlipayOplatformWebsiteService dingtalkService = new InMemoryAlipayOplatformWebsiteService(
				alipayOplatformWebsiteProperties);
		AlipayOplatformWebsiteTokenResponse accessTokenResponse = dingtalkService.getAccessTokenResponse(appid, code,
				state, binding, remoteAddress, sessionId);

		AlipaySystemOauthTokenResponse systemOauthTokenResponse = accessTokenResponse.getSystemOauthTokenResponse();
		AlipayUserInfoShareResponse userInfoShareResponse = accessTokenResponse.getUserInfoShareResponse();

		String userId = systemOauthTokenResponse.getUserId();
		String accessToken = systemOauthTokenResponse.getAccessToken();
		String refreshToken = systemOauthTokenResponse.getRefreshToken();
		String expiresIn = systemOauthTokenResponse.getExpiresIn();
		// LocalDateTime expires = LocalDateTime.now().plusSeconds(expireIn);
		String openId = userInfoShareResponse.getOpenId();
		String unionId = systemOauthTokenResponse.getUnionId();

		AlipayOplatformWebsiteUsers alipayOplatformWebsiteUsers = alipayOplatformWebsiteUsersService
				.getByAppidAndUserId(appid, userId);
		// @formatter:off
		// AlipayOplatformWebsiteUsers alipayOplatformWebsiteUsers = alipayOplatformWebsiteUsersService.getByAppidAndOpenId(appid,
		// 		openId);
		// AlipayOplatformWebsiteUsers alipayOplatformWebsiteUsers = alipayOplatformWebsiteUsersService.getByAppidAndUnionId(appid,
		// 		unionId);
		// @formatter:on

		if (alipayOplatformWebsiteUsers == null) {

			AlipayOplatformWebsiteUsers users = new AlipayOplatformWebsiteUsers();

			BeanUtils.copyProperties(systemOauthTokenResponse, users);
			BeanUtils.copyProperties(userInfoShareResponse, users);

			users.setAppid(appid);
			users.setAccessToken(accessToken);
			users.setRefreshToken(refreshToken);
			// users.setExpires(expires);
			users.setCreateIp(remoteAddress);

			alipayOplatformWebsiteUsersService.save(users);
		}
		else {

			BeanUtils.copyProperties(systemOauthTokenResponse, alipayOplatformWebsiteUsers);
			BeanUtils.copyProperties(userInfoShareResponse, alipayOplatformWebsiteUsers);

			// alipayOplatformWebsiteUsers.setExpires(expires);
			alipayOplatformWebsiteUsers.setUpdateIp(remoteAddress);
			alipayOplatformWebsiteUsersService.updateById(alipayOplatformWebsiteUsers);
		}

		// 绑定用户
		if (Boolean.TRUE.toString().equals(binding)) {

			String usersIdStr = sessionService.get(ALIPAY_OPLATFORM_WEBSITE_USERS_PREFIX + ":" + appid + ":" + state);
			long usersId = Long.parseLong(usersIdStr);

			alipayOplatformWebsiteUsersService.binding(usersId, appid, userId);
		}

		return accessTokenResponse;
	}

	/**
	 * 构建 支付宝 网站应用 认证信息
	 * @param clientPrincipal 经过身份验证的客户端主体
	 * @param additionalParameters 附加参数
	 * @param details 登录信息
	 * @param appid AppID
	 * @param code 授权码
	 * @param userId
	 * @param openId 用户唯一标识
	 * @param credentials 证书
	 * @param unionid 多账户用户唯一标识
	 * @param accessToken 授权凭证
	 * @param refreshToken 刷新凭证
	 * @param expiresIn 过期时间
	 * @return 返回 认证信息
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AbstractAuthenticationToken authenticationToken(Authentication clientPrincipal,
			Map<String, Object> additionalParameters, Object details, String appid, String code, String userId,
			String openId, Object credentials, String unionid, String accessToken, String refreshToken,
			String expiresIn) throws OAuth2AuthenticationException {
		AlipayOplatformWebsiteUsers alipayOplatformWebsiteUsers = alipayOplatformWebsiteUsersService
				.getByAppidAndUserId(appid, userId);

		if (alipayOplatformWebsiteUsers == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未查询到支付宝网站应用用户或已被删除", null);
			throw new LoginAuthenticationException(error);
		}

		Users users = alipayOplatformWebsiteUsers.getUsers();
		if (users == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未找到支付宝网站应用绑定的用户", null);
			throw new LoginAuthenticationException(error);
		}
		String username = users.getUsername();
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
				alipayOplatformWebsiteProperties.getDefaultRole());
		authorities.add(authority);
		User user = new User(username, accessToken, authorities);

		UsernamePasswordAuthenticationToken principal = UsernamePasswordAuthenticationToken.authenticated(user, null,
				user.getAuthorities());

		AlipayOplatformWebsiteAuthenticationToken authenticationToken = new AlipayOplatformWebsiteAuthenticationToken(
				authorities, clientPrincipal, principal, user, additionalParameters, details, appid, code, userId,
				openId);

		authenticationToken.setCredentials(credentials);
		authenticationToken.setUnionid(unionid);

		return authenticationToken;
	}

	/**
	 * 授权成功重定向方法
	 * @param request 请求
	 * @param response 响应
	 * @param uriVariables 参数
	 * @param oauth2AccessTokenResponse OAuth2.1 授权 Token
	 * @param alipayOplatformWebsite 支付宝 网站应用 配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, Map<String, String> uriVariables,
			OAuth2AccessTokenResponse oauth2AccessTokenResponse,
			AlipayOplatformWebsiteProperties.AlipayOplatformWebsite alipayOplatformWebsite)
			throws OAuth2AuthenticationException {
		OAuth2AccessToken oauth2AccessToken = oauth2AccessTokenResponse.getAccessToken();
		OAuth2RefreshToken oauth2RefreshToken = oauth2AccessTokenResponse.getRefreshToken();

		String successUrl = alipayOplatformWebsite.getSuccessUrl();
		String accessToken = oauth2AccessToken.getTokenValue();
		String refreshToken = "";
		if (oauth2RefreshToken != null) {
			refreshToken = oauth2RefreshToken.getTokenValue();
		}

		try {
			response.sendRedirect(String.format("%s?store=true&accessToken=%s&refreshToken=%s", successUrl, accessToken,
					refreshToken));
		}
		catch (IOException e) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "支付宝 网站应用重定向异常", null);
			throw new RedirectUriAlipayOplatformException(error);
		}
	}

}
