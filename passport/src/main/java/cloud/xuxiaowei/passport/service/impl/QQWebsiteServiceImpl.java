package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.UsersQqWebsite;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersQqWebsiteService;
import cloud.xuxiaowei.system.service.SessionService;
import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.ResponseUtils;
import cloud.xuxiaowei.utils.SecurityUtils;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.utils.exception.login.LoginException;
import cloud.xuxiaowei.utils.exception.oauth2.LoginAuthenticationException;
import cloud.xuxiaowei.utils.map.ResponseMap;
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
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.QQWebsiteAuthenticationToken;
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
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.OAuth2QQWebsiteParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.client.InMemoryQQWebsiteService;
import org.springframework.security.oauth2.server.authorization.client.QQWebsiteService;
import org.springframework.security.oauth2.server.authorization.client.QQWebsiteTokenResponse;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.exception.RedirectQQWebsiteException;
import org.springframework.security.oauth2.server.authorization.properties.QQWebsiteProperties;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2QQWebsiteEndpointUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * QQ开放平台 网站应用 账户服务接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class QQWebsiteServiceImpl implements QQWebsiteService {

	private final static String QQ_WEBSITE_STATE_PREFIX = "qq_website_state_prefix";

	private final static String QQ_WEBSITE_BINDING_PREFIX = "qq_website_binding_prefix";

	private final static String QQ_WEBSITE_USERS_PREFIX = "qq_website_users_prefix";

	private QQWebsiteProperties qqWebsiteProperties;

	private CloudSecurityProperties cloudSecurityProperties;

	private IUsersQqWebsiteService usersQqWebsiteService;

	private SessionService sessionService;

	@Autowired
	public void setQqWebsiteProperties(QQWebsiteProperties qqWebsiteProperties) {
		this.qqWebsiteProperties = qqWebsiteProperties;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Autowired
	public void setUsersQqWebsiteService(IUsersQqWebsiteService usersQqWebsiteService) {
		this.usersQqWebsiteService = usersQqWebsiteService;
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
		InMemoryQQWebsiteService qqWebsiteService = new InMemoryQQWebsiteService(qqWebsiteProperties);
		return qqWebsiteService.getRedirectUriByAppid(appid);
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
		sessionService.set(QQ_WEBSITE_STATE_PREFIX + ":" + appid + ":" + state, state, 30, TimeUnit.MINUTES);
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
			sessionService.set(QQ_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + state, binding, 30, TimeUnit.MINUTES);
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
		sessionService.set(QQ_WEBSITE_USERS_PREFIX + ":" + appid + ":" + state, usersId + "", 30, TimeUnit.MINUTES);
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
		String string = sessionService.get(QQ_WEBSITE_STATE_PREFIX + ":" + appid + ":" + state);
		if (!StringUtils.hasText(string)) {
			Response<?> error = Response.error("非法状态码");
			ResponseUtils.response(response, error);
			return false;
		}
		else if (string.equals(state)) {
			sessionService.remove(QQ_WEBSITE_STATE_PREFIX + ":" + appid + ":" + state);
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
		String binding = sessionService.get(QQ_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + state);
		sessionService.remove(QQ_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + state);
		return binding;
	}

	/**
	 * 根据 appid 获取 QQ开放平台 网站应用属性配置
	 * @param appid 公众号ID
	 * @return 返回 QQ开放平台 网站应用属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public QQWebsiteProperties.QQWebsite getQQWebsiteByAppid(String appid) throws OAuth2AuthenticationException {
		return new InMemoryQQWebsiteService(qqWebsiteProperties).getQQWebsiteByAppid(appid);
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
	 * @param accessTokenUrl 通过 code 换取网页授权 access_token 的 URL
	 * @param userinfoUrl 通过 access_token 获取用户个人信息
	 * @param remoteAddress 用户IP
	 * @param sessionId SessionID
	 * @return 返回 QQ授权结果
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@SneakyThrows
	@Override
	public QQWebsiteTokenResponse getAccessTokenResponse(String appid, String code, String state, String binding,
			String redirectUri, String accessTokenUrl, String openidUrl, String unionidUrl, String userinfoUrl,
			String remoteAddress, String sessionId) throws OAuth2AuthenticationException {
		Map<String, String> uriVariables = new HashMap<>(8);
		uriVariables.put(OAuth2ParameterNames.CLIENT_ID, appid);
		uriVariables.put(OAuth2ParameterNames.REDIRECT_URI, redirectUri);

		QQWebsiteProperties.QQWebsite qqWebsite = getQQWebsiteByAppid(appid);
		String secret = qqWebsite.getSecret();

		uriVariables.put(OAuth2ParameterNames.CLIENT_SECRET, secret);
		uriVariables.put(OAuth2ParameterNames.CODE, code);

		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		String tokenObject = restTemplate.getForObject(accessTokenUrl, String.class, uriVariables);

		QQWebsiteTokenResponse qqWebsiteTokenResponse;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			qqWebsiteTokenResponse = objectMapper.readValue(tokenObject, QQWebsiteTokenResponse.class);
		}
		catch (JsonProcessingException e) {
			OAuth2Error error = new OAuth2Error(OAuth2QQWebsiteEndpointUtils.ERROR_CODE,
					"使用 QQ开放平台 网站应用 授权code：" + code + " 获取Token异常", OAuth2QQWebsiteEndpointUtils.AUTH_CODE2SESSION_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		String accessToken = qqWebsiteTokenResponse.getAccessToken();
		if (accessToken == null) {
			OAuth2Error error = new OAuth2Error(qqWebsiteTokenResponse.getError(),
					qqWebsiteTokenResponse.getErrorDescription(), OAuth2QQWebsiteEndpointUtils.AUTH_CODE2SESSION_URI);
			throw new OAuth2AuthenticationException(error);
		}

		Map<String, String> accessTokenVariables = new HashMap<>(8);
		accessTokenVariables.put(OAuth2ParameterNames.ACCESS_TOKEN, accessToken);
		String openidObject = restTemplate.getForObject(openidUrl, String.class, accessTokenVariables);
		QQWebsiteTokenResponse openidResponse;
		try {
			openidResponse = objectMapper.readValue(openidObject, QQWebsiteTokenResponse.class);
		}
		catch (JsonProcessingException e) {
			OAuth2Error error = new OAuth2Error(OAuth2QQWebsiteEndpointUtils.ERROR_CODE,
					"使用 QQ开放平台 网站应用 授权Token：" + accessToken + " 获取opneid异常",
					OAuth2QQWebsiteEndpointUtils.AUTH_OPENID_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		String openid = openidResponse.getOpenid();
		String clientId = openidResponse.getClientId();
		if (openid == null) {
			OAuth2Error error = new OAuth2Error(openidResponse.getError(), openidResponse.getErrorDescription(),
					OAuth2QQWebsiteEndpointUtils.AUTH_OPENID_URI);
			throw new OAuth2AuthenticationException(error);
		}
		qqWebsiteTokenResponse.setOpenid(openid);
		qqWebsiteTokenResponse.setClientId(clientId);

		try {
			String unionidObject = restTemplate.getForObject(unionidUrl, String.class, accessTokenVariables);
			QQWebsiteTokenResponse unionidResponse = objectMapper.readValue(unionidObject,
					QQWebsiteTokenResponse.class);
			qqWebsiteTokenResponse.setUnionid(unionidResponse.getUnionid());
		}
		catch (Exception e) {
			log.error("获取 unionid 异常", e);
		}

		accessTokenVariables.put(OAuth2ParameterNames.CLIENT_ID, clientId);
		accessTokenVariables.put(OAuth2QQWebsiteParameterNames.OPENID, openid);
		String userinfoObject = restTemplate.getForObject(userinfoUrl, String.class, accessTokenVariables);

		QQWebsiteTokenResponse userinfoResponse;
		try {
			userinfoResponse = objectMapper.readValue(userinfoObject, QQWebsiteTokenResponse.class);
		}
		catch (JsonProcessingException e) {
			OAuth2Error error = new OAuth2Error(OAuth2QQWebsiteEndpointUtils.ERROR_CODE,
					"使用 QQ开放平台 网站应用 授权Token：" + accessToken + " 获取opneid异常",
					OAuth2QQWebsiteEndpointUtils.AUTH_OPENID_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		int ret = userinfoResponse.getRet();
		if (ret != 0) {
			OAuth2Error error = new OAuth2Error(ret + "", openidResponse.getMsg(),
					OAuth2QQWebsiteEndpointUtils.AUTH_OPENID_URI);
			throw new OAuth2AuthenticationException(error);
		}
		qqWebsiteTokenResponse.setRet(userinfoResponse.getRet());
		qqWebsiteTokenResponse.setMsg(userinfoResponse.getMsg());
		qqWebsiteTokenResponse.setIsLost(userinfoResponse.getIsLost());
		qqWebsiteTokenResponse.setGender(userinfoResponse.getGender());
		qqWebsiteTokenResponse.setIsYellowVip(userinfoResponse.getIsYellowVip());
		qqWebsiteTokenResponse.setCity(userinfoResponse.getCity());
		qqWebsiteTokenResponse.setYear(userinfoResponse.getYear());
		qqWebsiteTokenResponse.setLevel(userinfoResponse.getLevel());
		qqWebsiteTokenResponse.setFigureurl2(userinfoResponse.getFigureurl2());
		qqWebsiteTokenResponse.setFigureurl1(userinfoResponse.getFigureurl1());
		qqWebsiteTokenResponse.setGenderType(userinfoResponse.getGenderType());
		qqWebsiteTokenResponse.setIsYellowYearVip(userinfoResponse.getIsYellowYearVip());
		qqWebsiteTokenResponse.setProvince(userinfoResponse.getProvince());
		qqWebsiteTokenResponse.setConstellation(userinfoResponse.getConstellation());
		qqWebsiteTokenResponse.setFigureurl(userinfoResponse.getFigureurl());
		qqWebsiteTokenResponse.setFigureurlType(userinfoResponse.getFigureurlType());
		qqWebsiteTokenResponse.setFigureurlQq(userinfoResponse.getFigureurlQq());
		qqWebsiteTokenResponse.setNickname(userinfoResponse.getNickname());
		qqWebsiteTokenResponse.setYellowVipLevel(userinfoResponse.getYellowVipLevel());
		qqWebsiteTokenResponse.setFigureurlQq1(userinfoResponse.getFigureurlQq1());
		qqWebsiteTokenResponse.setVip(userinfoResponse.getVip());
		qqWebsiteTokenResponse.setFigureurlQq2(userinfoResponse.getFigureurlQq2());

		UsersQqWebsite usersQqWebsite = usersQqWebsiteService.getByAppidAndOpenid(appid, openid);
		if (usersQqWebsite == null) {
			UsersQqWebsite websiteUsers = new UsersQqWebsite();
			BeanUtils.copyProperties(qqWebsiteTokenResponse, websiteUsers);
			// websiteUsers.setExpires(expires);
			websiteUsers.setAppid(qqWebsiteTokenResponse.getClientId());
			websiteUsers.setCreateIp(remoteAddress);
			usersQqWebsiteService.save(websiteUsers);
		}
		else {
			// qqWebsiteUsers.setExpires(expires);
			usersQqWebsite.setUpdateIp(remoteAddress);
			usersQqWebsiteService.updateById(usersQqWebsite);
		}

		// 绑定用户
		if (Boolean.TRUE.toString().equals(binding)) {

			String usersIdStr = sessionService.get(QQ_WEBSITE_USERS_PREFIX + ":" + appid + ":" + state);
			long usersId = Long.parseLong(usersIdStr);

			usersQqWebsiteService.binding(usersId, appid, openid);
		}

		return qqWebsiteTokenResponse;
	}

	/**
	 * 构建 QQ开放平台 网站应用 认证信息
	 * @param clientPrincipal 经过身份验证的客户端主体
	 * @param additionalParameters 附加参数
	 * @param details 登录信息
	 * @param appid AppID
	 * @param code 授权码
	 * @param openid 用户唯一标识
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
			Map<String, Object> additionalParameters, Object details, String appid, String code, String openid,
			Object credentials, String unionid, String accessToken, String refreshToken, Integer expiresIn)
			throws OAuth2AuthenticationException {
		UsersQqWebsite usersQqWebsite = usersQqWebsiteService.getByAppidAndOpenid(appid, openid);

		if (usersQqWebsite == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未查询到QQ用户或已被删除", null);
			throw new LoginAuthenticationException(error);
		}

		Users users = usersQqWebsite.getUsers();
		if (users == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未找到QQ绑定的用户", null);
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

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(qqWebsiteProperties.getDefaultRole());
		authorities.add(authority);
		User user = new User(username, accessToken, authorities);

		UsernamePasswordAuthenticationToken principal = UsernamePasswordAuthenticationToken.authenticated(user, null,
				user.getAuthorities());

		QQWebsiteAuthenticationToken authenticationToken = new QQWebsiteAuthenticationToken(authorities,
				clientPrincipal, principal, user, additionalParameters, details, appid, code, openid);

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
	 * @param qqWebsite QQ开放平台 网站应用 配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, Map<String, String> uriVariables,
			OAuth2AccessTokenResponse oauth2AccessTokenResponse, QQWebsiteProperties.QQWebsite qqWebsite)
			throws OAuth2AuthenticationException {
		OAuth2AccessToken oauth2AccessToken = oauth2AccessTokenResponse.getAccessToken();
		OAuth2RefreshToken oauth2RefreshToken = oauth2AccessTokenResponse.getRefreshToken();

		String successUrl = qqWebsite.getSuccessUrl();
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
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "QQ开放平台 网站应用重定向异常", null);
			throw new RedirectQQWebsiteException(error);
		}
	}

}
