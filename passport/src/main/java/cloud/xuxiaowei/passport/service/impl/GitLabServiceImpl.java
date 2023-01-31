package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.GitlabUsers;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IGitlabUsersService;
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
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.GitLabAuthenticationToken;
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
import org.springframework.security.oauth2.server.authorization.client.GitLabService;
import org.springframework.security.oauth2.server.authorization.client.GitLabTokenResponse;
import org.springframework.security.oauth2.server.authorization.client.InMemoryGitLabService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.exception.RedirectGitLabException;
import org.springframework.security.oauth2.server.authorization.properties.GitLabProperties;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * GitLab 网站应用 账户服务接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class GitLabServiceImpl implements GitLabService {

	private final static String GITLAB_STATE_PREFIX = "gitlab_state_prefix";

	private final static String GITLAB_BINDING_PREFIX = "gitlab_binding_prefix";

	private final static String GITLAB_USERS_PREFIX = "gitlab_users_prefix";

	private GitLabProperties gitLabProperties;

	private CloudSecurityProperties cloudSecurityProperties;

	private IGitlabUsersService gitlabUsersService;

	private SessionService sessionService;

	@Autowired
	public void setGitLabProperties(GitLabProperties gitLabProperties) {
		this.gitLabProperties = gitLabProperties;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Autowired
	public void setGitlabUsersService(IGitlabUsersService gitlabUsersService) {
		this.gitlabUsersService = gitlabUsersService;
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
		InMemoryGitLabService gitLabService = new InMemoryGitLabService(gitLabProperties);
		return gitLabService.getRedirectUriByAppid(appid);
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
		sessionService.set(GITLAB_STATE_PREFIX + ":" + appid + ":" + state, state, 30, TimeUnit.MINUTES);
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
			sessionService.set(GITLAB_BINDING_PREFIX + ":" + appid + ":" + state, binding, 30, TimeUnit.MINUTES);
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
		sessionService.set(GITLAB_USERS_PREFIX + ":" + appid + ":" + state, usersId + "", 30, TimeUnit.MINUTES);
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
		String string = sessionService.get(GITLAB_STATE_PREFIX + ":" + appid + ":" + state);
		if (!StringUtils.hasText(string)) {
			Response<?> error = Response.error("非法状态码");
			ResponseUtils.response(response, error);
			return false;
		}
		else if (string.equals(state)) {
			sessionService.remove(GITLAB_STATE_PREFIX + ":" + appid + ":" + state);
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
		String binding = sessionService.get(GITLAB_BINDING_PREFIX + ":" + appid + ":" + state);
		sessionService.remove(GITLAB_BINDING_PREFIX + ":" + appid + ":" + state);
		return binding;
	}

	/**
	 * 根据 appid 获取 GitLab 属性配置
	 * @param appid 公众号ID
	 * @return 返回 GitLab 属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public GitLabProperties.GitLab getGitLabByAppid(String appid) throws OAuth2AuthenticationException {
		InMemoryGitLabService gitLabService = new InMemoryGitLabService(gitLabProperties);
		return gitLabService.getGitLabByAppid(appid);
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
	@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
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
	 * @param domain 域名
	 * @param appid AppID
	 * @param code 授权码
	 * @param state 状态码
	 * @param binding 是否绑定，需要使用者自己去拓展
	 * @param accessTokenUrl 通过 code 换取网页授权 access_token 的 URL
	 * @param userinfoUrl 通过 access_token 获取用户个人信息
	 * @param remoteAddress 用户IP
	 * @param sessionId SessionID
	 * @return 返回 GitLab授权结果
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public GitLabTokenResponse getAccessTokenResponse(String domain, String appid, String code, String state,
			String binding, String accessTokenUrl, String userinfoUrl, String remoteAddress, String sessionId)
			throws OAuth2AuthenticationException {
		InMemoryGitLabService gitLabService = new InMemoryGitLabService(gitLabProperties);
		GitLabTokenResponse accessTokenResponse = gitLabService.getAccessTokenResponse(domain, appid, code, state,
				binding, accessTokenUrl, userinfoUrl, remoteAddress, sessionId);

		GitLabTokenResponse.UserInfo userInfo = accessTokenResponse.getUserInfo();
		long id = userInfo.getId();

		String accessToken = accessTokenResponse.getAccessToken();
		String refreshToken = accessTokenResponse.getRefreshToken();
		Integer expiresIn = accessTokenResponse.getExpiresIn();
		LocalDateTime expires = LocalDateTime.now().plusSeconds(expiresIn);
		GitlabUsers gitlabUsers = gitlabUsersService.getByAppidAndId(appid, id);
		if (gitlabUsers == null) {

			GitlabUsers users = new GitlabUsers();

			BeanUtils.copyProperties(userInfo, users);

			users.setAppid(appid);
			users.setAccessToken(accessToken);
			users.setRefreshToken(refreshToken);
			users.setExpires(expires);
			users.setCreateIp(remoteAddress);
			users.setDomain(domain);
			users.setId(id);

			gitlabUsersService.save(users);
		}
		else {

			BeanUtils.copyProperties(userInfo, gitlabUsers);

			gitlabUsers.setAccessToken(accessToken);
			gitlabUsers.setRefreshToken(refreshToken);
			gitlabUsers.setExpires(expires);
			gitlabUsers.setUpdateIp(remoteAddress);
			gitlabUsersService.updateById(gitlabUsers);
		}

		// 绑定用户
		if (Boolean.TRUE.toString().equals(binding)) {

			String usersIdStr = sessionService.get(GITLAB_USERS_PREFIX + ":" + appid + ":" + state);
			long usersId = Long.parseLong(usersIdStr);

			gitlabUsersService.binding(usersId, appid, id);
		}

		return accessTokenResponse;
	}

	/**
	 * 构建 GitLab 认证信息
	 * @param clientPrincipal 经过身份验证的客户端主体
	 * @param additionalParameters 附加参数
	 * @param details 登录信息
	 * @param appid AppID
	 * @param code 授权码
	 * @param id 用户唯一标识
	 * @param credentials 证书
	 * @param username GitLab登录用户名
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
			Object credentials, String username, String accessToken, String refreshToken, Integer expiresIn,
			String scope) throws OAuth2AuthenticationException {
		GitlabUsers gitlabUsers = gitlabUsersService.getByAppidAndId(appid, id);

		if (gitlabUsers == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未查询到GitLab用户或已被删除", null);
			throw new LoginAuthenticationException(error);
		}

		Users users = gitlabUsers.getUsers();
		if (users == null) {
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "未找到GitLab绑定的用户", null);
			throw new LoginAuthenticationException(error);
		}

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

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(gitLabProperties.getDefaultRole());
		authorities.add(authority);
		User user = new User(users.getUsername(), accessToken, authorities);

		UsernamePasswordAuthenticationToken principal = UsernamePasswordAuthenticationToken.authenticated(user, null,
				user.getAuthorities());

		GitLabAuthenticationToken authenticationToken = new GitLabAuthenticationToken(authorities, clientPrincipal,
				principal, user, additionalParameters, details, appid, code, id);

		authenticationToken.setCredentials(credentials);

		return authenticationToken;
	}

	/**
	 * 授权成功重定向方法
	 * @param request 请求
	 * @param response 响应
	 * @param uriVariables 参数
	 * @param oauth2AccessTokenResponse OAuth2.1 授权 Token
	 * @param gitLab GitLab 配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, Map<String, String> uriVariables,
			OAuth2AccessTokenResponse oauth2AccessTokenResponse, GitLabProperties.GitLab gitLab)
			throws OAuth2AuthenticationException {
		OAuth2AccessToken oauth2AccessToken = oauth2AccessTokenResponse.getAccessToken();
		OAuth2RefreshToken oauth2RefreshToken = oauth2AccessTokenResponse.getRefreshToken();

		String successUrl = gitLab.getSuccessUrl();
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
			OAuth2Error error = new OAuth2Error(CodeEnums.ERROR.code, "GitLab 网站应用重定向异常", null);
			throw new RedirectGitLabException(error);
		}

	}

}
