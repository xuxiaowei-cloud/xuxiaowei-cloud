package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.UsersGithub;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersGithubService;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.GitHubAuthenticationToken;
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
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.client.GitHubService;
import org.springframework.security.oauth2.server.authorization.client.GitHubTokenResponse;
import org.springframework.security.oauth2.server.authorization.client.InMemoryGitHubService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.exception.RedirectGitHubException;
import org.springframework.security.oauth2.server.authorization.properties.GitHubProperties;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2GitHubEndpointUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * GitHub 账户服务接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class GitHubServiceImpl implements GitHubService {

	private final static String GITHUB_STATE_PREFIX = "github_state_prefix";

	private final static String GITHUB_BINDING_PREFIX = "github_binding_prefix";

	private final static String GITHUB_USERS_PREFIX = "github_users_prefix";

	private GitHubProperties githubProperties;

	private CloudSecurityProperties cloudSecurityProperties;

	private IUsersGithubService usersGithubService;

	private SessionService sessionService;

	@Autowired
	public void setGithubProperties(GitHubProperties githubProperties) {
		this.githubProperties = githubProperties;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Autowired
	public void setUsersGithubService(IUsersGithubService usersGithubService) {
		this.usersGithubService = usersGithubService;
	}

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	/**
	 * 根据 appid 获取重定向的地址
	 * @param appid 开放平台 ID
	 * @return 返回重定向的地址
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public String getRedirectUriByAppid(String appid) throws OAuth2AuthenticationException {
		return new InMemoryGitHubService(githubProperties).getRedirectUriByAppid(appid);
	}

	/**
	 * 生成状态码
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 ID
	 * @return 返回生成的授权码
	 */
	@Override
	public String stateGenerate(HttpServletRequest request, HttpServletResponse response, String appid) {
		String state = UUID.randomUUID().toString();
		sessionService.set(GITHUB_STATE_PREFIX + ":" + appid + ":" + state, state, 30, TimeUnit.MINUTES);
		return state;
	}

	/**
	 * 储存绑定参数
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 ID
	 * @param state 状态码
	 * @param binding 绑定参数
	 */
	@Override
	public void storeBinding(HttpServletRequest request, HttpServletResponse response, String appid, String state,
			String binding) {
		if (binding != null) {
			sessionService.set(GITHUB_BINDING_PREFIX + ":" + appid + ":" + state, binding, 30, TimeUnit.MINUTES);
		}
	}

	/**
	 * 储存操作用户
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 ID
	 * @param state 状态码
	 * @param binding 绑定参数
	 */
	@Override
	public void storeUsers(HttpServletRequest request, HttpServletResponse response, String appid, String state,
			String binding) {
		Long usersId = SecurityUtils.getUsersId();
		sessionService.set(GITHUB_USERS_PREFIX + ":" + appid + ":" + state, usersId + "", 30, TimeUnit.MINUTES);
	}

	/**
	 * 状态码验证（返回 {@link Boolean#FALSE} 时，将终止后面需要执行的代码）
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 ID
	 * @param code 授权码
	 * @param state 状态码
	 * @return 返回 状态码验证结果
	 */
	@SneakyThrows
	@Override
	public boolean stateValid(HttpServletRequest request, HttpServletResponse response, String appid, String code,
			String state) {
		String string = sessionService.get(GITHUB_STATE_PREFIX + ":" + appid + ":" + state);
		if (!StringUtils.hasText(string)) {
			Response<?> error = Response.error("非法状态码");
			ResponseUtils.response(response, error);
			return false;
		}
		else if (string.equals(state)) {
			sessionService.remove(GITHUB_STATE_PREFIX + ":" + appid + ":" + state);
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
	 * @param appid 开放平台 ID
	 * @param code 授权码
	 * @param state 状态码
	 * @return 返回 绑定参数
	 */
	@Override
	public String getBinding(HttpServletRequest request, HttpServletResponse response, String appid, String code,
			String state) {
		String binding = sessionService.get(GITHUB_BINDING_PREFIX + ":" + appid + ":" + state);
		sessionService.remove(GITHUB_BINDING_PREFIX + ":" + appid + ":" + state);
		return binding;
	}

	/**
	 * 根据 appid 获取 GitHub 属性配置
	 * @param appid 公众号ID
	 * @return 返回 GitHub 属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public GitHubProperties.GitHub getGitHubByAppid(String appid) throws OAuth2AuthenticationException {
		return new InMemoryGitHubService(githubProperties).getGitHubByAppid(appid);
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
			HttpServletResponse response, String clientId, String clientSecret, String tokenUrlPrefix, String tokenUrl,
			Map<String, String> uriVariables) throws OAuth2AuthenticationException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(8);
		multiValueMap.put(OAuth2ParameterNames.CLIENT_ID, Collections.singletonList(clientId));
		multiValueMap.put(OAuth2ParameterNames.CLIENT_SECRET, Collections.singletonList(clientSecret));

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
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
	 * @return 返回 GitHub授权结果
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public GitHubTokenResponse getAccessTokenResponse(String appid, String code, String state, String binding,
			String accessTokenUrl, String userinfoUrl, String remoteAddress, String sessionId)
			throws OAuth2AuthenticationException {
		Map<String, String> uriVariables = new HashMap<>(8);
		uriVariables.put(OAuth2ParameterNames.CLIENT_ID, appid);

		GitHubProperties.GitHub gitHub = getGitHubByAppid(appid);
		String secret = gitHub.getSecret();
		String redirectUri = gitHub.getRedirectUriPrefix() + "/" + appid;

		uriVariables.put(OAuth2ParameterNames.CLIENT_SECRET, secret);
		uriVariables.put(OAuth2ParameterNames.CODE, code);
		uriVariables.put(OAuth2ParameterNames.REDIRECT_URI, redirectUri);

		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		// 设置代理
		// @formatter:off
		// SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		// simpleClientHttpRequestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 12333)));
		// restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
		// @formatter:on

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		String forObject = restTemplate.postForObject(accessTokenUrl, httpEntity, String.class, uriVariables);

		GitHubTokenResponse accessTokenResponse;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			accessTokenResponse = objectMapper.readValue(forObject, GitHubTokenResponse.class);
		}
		catch (JsonProcessingException e) {
			OAuth2Error error = new OAuth2Error(OAuth2GitHubEndpointUtils.ERROR_CODE,
					"使用 GitHub  授权code：" + code + " 获取Token异常", OAuth2GitHubEndpointUtils.AUTH_CODE2SESSION_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		String accessToken = accessTokenResponse.getAccessToken();
		if (accessToken == null) {
			OAuth2Error error = new OAuth2Error(accessTokenResponse.getError(),
					accessTokenResponse.getErrorDescription(), OAuth2GitHubEndpointUtils.AUTH_CODE2SESSION_URI);
			throw new OAuth2AuthenticationException(error);
		}

		httpHeaders.setBearerAuth(accessToken);

		GitHubTokenResponse.UserInfo userInfo;
		try {
			// HTTP GET 支持设置 Header
			RequestCallback requestCallback = restTemplate.httpEntityCallback(httpEntity,
					GitHubTokenResponse.UserInfo.class);
			HttpMessageConverterExtractor<GitHubTokenResponse.UserInfo> responseExtractor = new HttpMessageConverterExtractor<>(
					GitHubTokenResponse.UserInfo.class, restTemplate.getMessageConverters());
			userInfo = restTemplate.execute(userinfoUrl, HttpMethod.GET, requestCallback, responseExtractor);

			assert userInfo != null;

			accessTokenResponse.setUserInfo(userInfo);
		}
		catch (Exception e) {
			OAuth2Error error = new OAuth2Error(OAuth2GitHubEndpointUtils.ERROR_CODE, "使用 GitHub  获取用户个人信息异常：",
					OAuth2GitHubEndpointUtils.AUTH_CODE2SESSION_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		int id = userInfo.getId();

		String refreshToken = accessTokenResponse.getRefreshToken();
		Integer expiresIn = accessTokenResponse.getExpiresIn();
		LocalDateTime expires = LocalDateTime.now().plusSeconds(expiresIn);
		UsersGithub usersGithub = usersGithubService.getByAppidAndId(appid, id);

		if (usersGithub == null) {

			UsersGithub users = new UsersGithub();

			BeanUtils.copyProperties(userInfo, users);

			users.setAppid(appid);
			users.setAccessToken(accessToken);
			users.setRefreshToken(refreshToken);
			users.setExpires(expires);
			users.setCreateIp(remoteAddress);
			users.setId(id);

			usersGithubService.save(users);
		}
		else {

			BeanUtils.copyProperties(userInfo, usersGithub);

			usersGithub.setAccessToken(accessToken);
			usersGithub.setRefreshToken(refreshToken);
			usersGithub.setExpires(expires);
			usersGithub.setUpdateIp(remoteAddress);
			usersGithubService.updateById(usersGithub);
		}

		// 绑定用户
		if (Boolean.TRUE.toString().equals(binding)) {

			String usersIdStr = sessionService.get(GITHUB_USERS_PREFIX + ":" + appid + ":" + state);
			long usersId = Long.parseLong(usersIdStr);

			usersGithubService.binding(usersId, appid, id);
		}

		return accessTokenResponse;
	}

	/**
	 * 构建 GitHub 认证信息
	 * @param clientPrincipal 经过身份验证的客户端主体
	 * @param additionalParameters 附加参数
	 * @param details 登录信息
	 * @param appid AppID
	 * @param code 授权码
	 * @param id 用户唯一标识
	 * @param credentials 证书
	 * @param login GitHub登录用户名
	 * @param accessToken 授权凭证
	 * @param refreshToken 刷新凭证
	 * @param expiresIn 过期时间
	 * @param scope 授权范围
	 * @return 返回 认证信息
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AbstractAuthenticationToken authenticationToken(Authentication clientPrincipal,
			Map<String, Object> additionalParameters, Object details, String appid, String code, int id,
			Object credentials, String login, String accessToken, String refreshToken, Integer expiresIn, String scope)
			throws OAuth2AuthenticationException {
		UsersGithub usersGithub = usersGithubService.getByAppidAndId(appid, id);

		if (usersGithub == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未查询到GitHub用户或已被删除", null);
			throw new LoginAuthenticationException(error);
		}

		Users users = usersGithub.getUsers();
		String username;
		if (users == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未找到GitHub绑定的用户", null);
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
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(githubProperties.getDefaultRole());
		authorities.add(authority);
		User user = new User(username, accessToken, authorities);

		UsernamePasswordAuthenticationToken principal = UsernamePasswordAuthenticationToken.authenticated(user, null,
				user.getAuthorities());

		GitHubAuthenticationToken authenticationToken = new GitHubAuthenticationToken(authorities, clientPrincipal,
				principal, user, additionalParameters, details, appid, code, id);

		authenticationToken.setCredentials(credentials);
		authenticationToken.setLogin(login);

		return authenticationToken;
	}

	/**
	 * 授权成功重定向方法
	 * @param request 请求
	 * @param response 响应
	 * @param uriVariables 参数
	 * @param oauth2AccessTokenResponse OAuth2.1 授权 Token
	 * @param gitHub GitHub 配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, Map<String, String> uriVariables,
			OAuth2AccessTokenResponse oauth2AccessTokenResponse, GitHubProperties.GitHub gitHub)
			throws OAuth2AuthenticationException {
		OAuth2AccessToken oauth2AccessToken = oauth2AccessTokenResponse.getAccessToken();
		OAuth2RefreshToken oauth2RefreshToken = oauth2AccessTokenResponse.getRefreshToken();

		String successUrl = gitHub.getSuccessUrl();
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
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "GitHub 网站应用重定向异常", null);
			throw new RedirectGitHubException(error);
		}
	}

}
