package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.entity.UsersWxWorkWebsite;
import cloud.xuxiaowei.system.service.IUsersWxWorkWebsiteService;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.WeChatWorkWebsiteAuthenticationToken;
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
import org.springframework.security.oauth2.core.endpoint.OAuth2WeChatWorkWebsiteParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.client.InMemoryWeChatWorkWebsiteService;
import org.springframework.security.oauth2.server.authorization.client.WeChatWorkWebsiteService;
import org.springframework.security.oauth2.server.authorization.client.WeChatWorkWebsiteTokenResponse;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.exception.RedirectWeChatWorkWebsiteException;
import org.springframework.security.oauth2.server.authorization.properties.WeChatWorkWebsiteProperties;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2WeChatWorkWebsiteEndpointUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 企业微信 扫码授权登录 账户服务接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class WeChatWorkWebsiteServiceImpl implements WeChatWorkWebsiteService {

	private final static String WECHAT_WORK_WEBSITE_STATE_PREFIX = "wechat_work_website_state_prefix";

	private final static String WECHAT_WORK_WEBSITE_BINDING_PREFIX = "wechat_work_website_binding_prefix";

	private final static String WECHAT_WORK_WEBSITE_USERS_PREFIX = "wechat_work_website_users_prefix";

	private WeChatWorkWebsiteProperties weChatWorkWebsiteProperties;

	private CloudSecurityProperties cloudSecurityProperties;

	private IUsersWxWorkWebsiteService usersWxWorkWebsiteService;

	private SessionService sessionService;

	@Autowired
	public void setWeChatWorkWebsiteProperties(WeChatWorkWebsiteProperties weChatWorkWebsiteProperties) {
		this.weChatWorkWebsiteProperties = weChatWorkWebsiteProperties;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Autowired
	public void setUsersWxWorkWebsiteService(IUsersWxWorkWebsiteService usersWxWorkWebsiteService) {
		this.usersWxWorkWebsiteService = usersWxWorkWebsiteService;
	}

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	/**
	 * 根据 appid 获取重定向的地址
	 * @param appid 开放平台 网站应用 ID
	 * @param agentid 访问
	 * <a href="https://work.weixin.qq.com/wework_admin/frame#apps">应用管理/应用</a>
	 * @return 返回重定向的地址
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public String getRedirectUriByAppidAndAgentid(String appid, String agentid) throws OAuth2AuthenticationException {
		InMemoryWeChatWorkWebsiteService weChatWorkWebsiteService = new InMemoryWeChatWorkWebsiteService(
				weChatWorkWebsiteProperties);
		return weChatWorkWebsiteService.getRedirectUriByAppidAndAgentid(appid, agentid);
	}

	/**
	 * 生成状态码
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param agentid 访问
	 * <a href="https://work.weixin.qq.com/wework_admin/frame#apps">应用管理/应用</a>
	 * 中的《自建》应用即可查看到
	 * @return 返回生成的授权码
	 */
	@Override
	public String stateGenerate(HttpServletRequest request, HttpServletResponse response, String appid,
			String agentid) {
		String state = UUID.randomUUID().toString();
		sessionService.set(WECHAT_WORK_WEBSITE_STATE_PREFIX + ":" + appid + ":" + agentid + ":" + state, state, 30,
				TimeUnit.MINUTES);
		return state;
	}

	/**
	 * 储存绑定参数
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param agentid 访问
	 * <a href="https://work.weixin.qq.com/wework_admin/frame#apps">应用管理/应用</a>
	 * @param state 状态码
	 * @param binding 绑定参数
	 */
	@Override
	public void storeBinding(HttpServletRequest request, HttpServletResponse response, String appid, String agentid,
			String state, String binding) {
		if (binding != null) {
			sessionService.set(WECHAT_WORK_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + agentid + ":" + state, binding,
					30, TimeUnit.MINUTES);
		}
	}

	/**
	 * 储存操作用户
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param agentid 访问
	 * <a href="https://work.weixin.qq.com/wework_admin/frame#apps">应用管理/应用</a>
	 * @param state 状态码
	 * @param binding 绑定参数
	 */
	@Override
	public void storeUsers(HttpServletRequest request, HttpServletResponse response, String appid, String agentid,
			String state, String binding) {
		Long usersId = SecurityUtils.getUsersId();
		sessionService.set(WECHAT_WORK_WEBSITE_USERS_PREFIX + ":" + appid + ":" + agentid + ":" + state, usersId + "",
				30, TimeUnit.MINUTES);
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
	public boolean stateValid(HttpServletRequest request, HttpServletResponse response, String appid, String agentid,
			String code, String state) {
		String string = sessionService
			.get(WECHAT_WORK_WEBSITE_STATE_PREFIX + ":" + appid + ":" + agentid + ":" + state);
		if (!StringUtils.hasText(string)) {
			Response<?> error = Response.error("非法状态码");
			ResponseUtils.response(response, error);
			return false;
		}
		else if (string.equals(state)) {
			sessionService.remove(WECHAT_WORK_WEBSITE_STATE_PREFIX + ":" + appid + ":" + agentid + ":" + state);
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
	public String getBinding(HttpServletRequest request, HttpServletResponse response, String appid, String agentid,
			String code, String state) {
		String binding = sessionService
			.get(WECHAT_WORK_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + agentid + ":" + state);
		sessionService.remove(WECHAT_WORK_WEBSITE_BINDING_PREFIX + ":" + appid + ":" + agentid + ":" + state);
		return binding;
	}

	/**
	 * 根据 appid 获取 企业微信 扫码授权登录属性配置
	 * @param appid 公众号ID
	 * @param agentid 访问
	 * <a href="https://work.weixin.qq.com/wework_admin/frame#apps">应用管理/应用</a>
	 * @return 返回 企业微信 扫码授权登录属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public WeChatWorkWebsiteProperties.WeChatWorkWebsite getWeChatWorkWebsiteByAppidAndAgentid(String appid,
			String agentid) throws OAuth2AuthenticationException {
		InMemoryWeChatWorkWebsiteService weChatWorkWebsiteService = new InMemoryWeChatWorkWebsiteService(
				weChatWorkWebsiteProperties);
		return weChatWorkWebsiteService.getWeChatWorkWebsiteByAppidAndAgentid(appid, agentid);
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
	 * @param agentid 访问
	 * <a href="https://work.weixin.qq.com/wework_admin/frame#apps">应用管理/应用</a>
	 * @param code 授权码
	 * @param state 状态码
	 * @param binding 是否绑定，需要使用者自己去拓展
	 * @param userinfoUrl 通过 access_token 获取用户个人信息
	 * @param getUserUrl 读取成员
	 * @param convertToOpenidUrl 使用 userid 换取 openid
	 * @param remoteAddress 用户IP
	 * @param sessionId SessionID
	 * @return 返回 微信授权结果
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public WeChatWorkWebsiteTokenResponse getAccessTokenResponse(String appid, String agentid, String code,
			String state, String binding, String accessTokenUrl, String userinfoUrl, String getUserUrl,
			String convertToOpenidUrl, String remoteAddress, String sessionId) throws OAuth2AuthenticationException {
		Map<String, String> uriVariables = new HashMap<>(8);
		uriVariables.put(OAuth2WeChatWorkWebsiteParameterNames.APPID, appid);

		String secret = getSecretByAppid(appid, agentid);

		uriVariables.put(OAuth2WeChatWorkWebsiteParameterNames.SECRET, secret);

		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		String forObject = restTemplate.getForObject(accessTokenUrl, String.class, uriVariables);

		WeChatWorkWebsiteTokenResponse accessTokenResponse;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			accessTokenResponse = objectMapper.readValue(forObject, WeChatWorkWebsiteTokenResponse.class);
		}
		catch (JsonProcessingException e) {
			OAuth2Error error = new OAuth2Error(OAuth2WeChatWorkWebsiteEndpointUtils.ERROR_CODE,
					"使用 企业微信 扫码授权登录 授权code：" + code + " 获取Token异常",
					OAuth2WeChatWorkWebsiteEndpointUtils.AUTH_WEBSITE_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		Integer errcode = accessTokenResponse.getErrcode();
		String accessToken = accessTokenResponse.getAccessToken();
		if (errcode != 0) {
			OAuth2Error error = new OAuth2Error(accessTokenResponse.getErrcode() + "", accessTokenResponse.getErrmsg(),
					OAuth2WeChatWorkWebsiteEndpointUtils.AUTH_WEBSITE_URI);
			throw new OAuth2AuthenticationException(error);
		}

		Integer expiresIn = accessTokenResponse.getExpiresIn();
		LocalDateTime expires = LocalDateTime.now().plusSeconds(expiresIn);

		Map<String, String> map = new HashMap<>(4);
		map.put(OAuth2ParameterNames.ACCESS_TOKEN, accessToken);
		map.put(OAuth2ParameterNames.CODE, code);

		String string = restTemplate.getForObject(userinfoUrl, String.class, map);
		WeChatWorkWebsiteTokenResponse.User useridResponse;
		try {
			useridResponse = objectMapper.readValue(string, WeChatWorkWebsiteTokenResponse.User.class);
		}
		catch (JsonProcessingException e) {
			OAuth2Error error = new OAuth2Error(OAuth2WeChatWorkWebsiteEndpointUtils.ERROR_CODE,
					"使用 企业微信 扫码授权登录 获取用户个人信息异常：", OAuth2WeChatWorkWebsiteEndpointUtils.AUTH_WEBSITE_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		Integer useridErrcode = useridResponse.getErrcode();
		if (useridErrcode != 0) {
			OAuth2Error error = new OAuth2Error(useridResponse.getErrcode() + "", useridResponse.getErrmsg(),
					OAuth2WeChatWorkWebsiteEndpointUtils.AUTH_WEBSITE_URI);
			throw new OAuth2AuthenticationException(error);
		}

		accessTokenResponse.setUserid(useridResponse.getUserid());

		String userid = accessTokenResponse.getUserid();
		String openid;
		if (userid != null) {

			Map<String, String> uriVariablesMap = new HashMap<>(4);
			uriVariablesMap.put(OAuth2ParameterNames.ACCESS_TOKEN, accessToken);
			uriVariablesMap.put(OAuth2WeChatWorkWebsiteParameterNames.USERID, userid);

			String getForObject = restTemplate.getForObject(getUserUrl, String.class, uriVariablesMap);
			try {
				WeChatWorkWebsiteTokenResponse.User response = objectMapper.readValue(getForObject,
						WeChatWorkWebsiteTokenResponse.User.class);
				accessTokenResponse.setUser(response);
			}
			catch (JsonProcessingException e) {
				OAuth2Error error = new OAuth2Error(OAuth2WeChatWorkWebsiteEndpointUtils.ERROR_CODE,
						"使用 企业微信 扫码授权登录 读取成员异常：", OAuth2WeChatWorkWebsiteEndpointUtils.AUTH_WEBSITE_URI);
				throw new OAuth2AuthenticationException(error, e);
			}

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			Map<String, String> body = new HashMap<>(4);
			body.put(OAuth2WeChatWorkWebsiteParameterNames.USERID, userid);
			HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(body, httpHeaders);

			String post = restTemplate.postForObject(convertToOpenidUrl, httpEntity, String.class, uriVariablesMap);
			try {
				WeChatWorkWebsiteTokenResponse.User response = objectMapper.readValue(post,
						WeChatWorkWebsiteTokenResponse.User.class);
				WeChatWorkWebsiteTokenResponse.User user = accessTokenResponse.getUser();
				openid = response.getOpenid();
				if (user == null) {
					accessTokenResponse.setUser(response);
				}
				else {
					user.setOpenid(openid);
				}
				accessTokenResponse.setOpenid(openid);
			}
			catch (JsonProcessingException e) {
				OAuth2Error error = new OAuth2Error(OAuth2WeChatWorkWebsiteEndpointUtils.ERROR_CODE,
						"使用 企业微信 扫码授权登录 使用userid获取openid异常：", OAuth2WeChatWorkWebsiteEndpointUtils.AUTH_WEBSITE_URI);
				throw new OAuth2AuthenticationException(error, e);
			}
		}
		else {
			openid = null;
		}

		UsersWxWorkWebsite usersWxWorkWebsite = usersWxWorkWebsiteService.getByAppidAndAgentidAndOpenid(appid, agentid,
				openid);
		if (usersWxWorkWebsite == null) {

			UsersWxWorkWebsite workWebsiteUsers = new UsersWxWorkWebsite();

			BeanUtils.copyProperties(accessTokenResponse, workWebsiteUsers);
			BeanUtils.copyProperties(accessTokenResponse.getUser(), workWebsiteUsers);

			workWebsiteUsers.setExpires(expires);
			workWebsiteUsers.setAppid(appid);
			workWebsiteUsers.setAgentid(agentid);
			workWebsiteUsers.setCreateIp(remoteAddress);

			usersWxWorkWebsiteService.save(workWebsiteUsers);
		}
		else {

			usersWxWorkWebsite.setExpires(expires);
			usersWxWorkWebsite.setUpdateIp(remoteAddress);
			usersWxWorkWebsiteService.updateById(usersWxWorkWebsite);
		}

		// 绑定用户
		if (Boolean.TRUE.toString().equals(binding)) {

			String usersIdStr = sessionService
				.get(WECHAT_WORK_WEBSITE_USERS_PREFIX + ":" + appid + ":" + agentid + ":" + state);
			long usersId = Long.parseLong(usersIdStr);

			usersWxWorkWebsiteService.binding(usersId, appid, agentid, openid);
		}

		return accessTokenResponse;
	}

	/**
	 * 构建 企业微信 扫码授权登录 认证信息
	 * @param clientPrincipal 经过身份验证的客户端主体
	 * @param additionalParameters 附加参数
	 * @param details 登录信息
	 * @param appid AppID
	 * @param agentid 应用ID
	 * @param code 授权码
	 * @param userid 用户ID
	 * @param openid 用户唯一标识
	 * @param credentials 证书
	 * @param unionid 多账户用户唯一标识
	 * @param accessToken 授权凭证
	 * @param expiresIn 过期时间
	 * @return 返回 认证信息
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AbstractAuthenticationToken authenticationToken(Authentication clientPrincipal,
			Map<String, Object> additionalParameters, Object details, String appid, String agentid, String code,
			String userid, String openid, Object credentials, String unionid, String accessToken, Integer expiresIn)
			throws OAuth2AuthenticationException {
		UsersWxWorkWebsite wxOpenWebsiteUsers = usersWxWorkWebsiteService.getByAppidAndAgentidAndOpenid(appid, agentid,
				openid);

		if (wxOpenWebsiteUsers == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未查询到微信用户或已被删除", null);
			throw new LoginAuthenticationException(error);
		}
		Users users = wxOpenWebsiteUsers.getUsers();
		if (users == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未找到微信绑定的用户", null);
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

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(weChatWorkWebsiteProperties.getDefaultRole());
		authorities.add(authority);
		User user = new User(username, accessToken, authorities);

		UsernamePasswordAuthenticationToken principal = UsernamePasswordAuthenticationToken.authenticated(user, null,
				user.getAuthorities());

		WeChatWorkWebsiteAuthenticationToken authenticationToken = new WeChatWorkWebsiteAuthenticationToken(authorities,
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
	 * @param weChatWorkWebsite 企业微信 扫码授权登录 配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, Map<String, String> uriVariables,
			OAuth2AccessTokenResponse oauth2AccessTokenResponse,
			WeChatWorkWebsiteProperties.WeChatWorkWebsite weChatWorkWebsite) throws OAuth2AuthenticationException {
		OAuth2AccessToken oauth2AccessToken = oauth2AccessTokenResponse.getAccessToken();
		OAuth2RefreshToken oauth2RefreshToken = oauth2AccessTokenResponse.getRefreshToken();

		String successUrl = weChatWorkWebsite.getSuccessUrl();
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
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "微信开放平台 网站应用重定向异常", null);
			throw new RedirectWeChatWorkWebsiteException(error);
		}

	}

	public String getSecretByAppid(String appid, String agentid) {
		Assert.notNull(appid, "appid 不能为 null");
		WeChatWorkWebsiteProperties.WeChatWorkWebsite weChatWorkWebsite = getWeChatWorkWebsiteByAppidAndAgentid(appid,
				agentid);
		return weChatWorkWebsite.getSecret();
	}

}
