package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.system.entity.UsersAlipayMiniprogram;
import cloud.xuxiaowei.system.service.IUsersAlipayMiniprogramService;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.authorization.client.*;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.properties.AlipayMiniProgramProperties;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 支付宝小程序 账户服务接口 实现类
 *
 * @author xuxiaowei
 * @see RegisteredClientRepository
 * @see InMemoryRegisteredClientRepository
 * @see JdbcRegisteredClientRepository
 * @since 0.0.1
 */
@Slf4j
@Service
public class AlipayMiniProgramServiceImpl implements AlipayMiniProgramService {

	private AlipayMiniProgramProperties alipayMiniProgramProperties;

	private IUsersAlipayMiniprogramService usersAlipayMiniprogramService;

	@Autowired
	public void setAlipayMiniProgramProperties(AlipayMiniProgramProperties alipayMiniProgramProperties) {
		this.alipayMiniProgramProperties = alipayMiniProgramProperties;
	}

	@Autowired
	public void setUsersAlipayMiniprogramService(IUsersAlipayMiniprogramService usersAlipayMiniprogramService) {
		this.usersAlipayMiniprogramService = usersAlipayMiniprogramService;
	}

	/**
	 * 认证信息
	 * @param appid AppID(小程序ID)
	 * @param userId 用户唯一标识
	 * @param details 登录信息
	 * @return 返回 认证信息
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AbstractAuthenticationToken authenticationToken(Authentication clientPrincipal,
			Map<String, Object> additionalParameters, Object details, String appid, String code, String userId,
			Object credentials) throws OAuth2AuthenticationException {
		InMemoryAlipayMiniProgramService inMemoryAlipayMiniProgramService = new InMemoryAlipayMiniProgramService(
				alipayMiniProgramProperties);
		return inMemoryAlipayMiniProgramService.authenticationToken(clientPrincipal, additionalParameters, details,
				appid, code, userId, credentials);
	}

	/**
	 * 根据 AppID(小程序ID)、code、jsCode2SessionUrl 获取Token
	 * @param appid AppID(小程序ID)
	 * @param code @see <a href=
	 * "https://opendocs.alipay.com/mini/api/openapi-authorize">my.getAuthCode获取用户信息授权，取得授权码（authCode）</a>
	 * @return 返回 Token及用户信息
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AlipayMiniProgramTokenResponse getAccessTokenResponse(String appid, String code)
			throws OAuth2AuthenticationException {
		InMemoryAlipayMiniProgramService inMemoryAlipayMiniProgramService = new InMemoryAlipayMiniProgramService(
				alipayMiniProgramProperties);
		AlipayMiniProgramTokenResponse alipayMiniProgramTokenResponse = inMemoryAlipayMiniProgramService
			.getAccessTokenResponse(appid, code);

		AlipaySystemOauthTokenResponse systemOauthTokenResponse = alipayMiniProgramTokenResponse
			.getSystemOauthTokenResponse();
		AlipayUserInfoShareResponse userInfoShareResponse = alipayMiniProgramTokenResponse.getUserInfoShareResponse();

		String userId = userInfoShareResponse.getUserId();
		String openId = userInfoShareResponse.getOpenId();
		String unionId = systemOauthTokenResponse.getUnionId();

		UsersAlipayMiniprogram usersAlipayMiniprogram = usersAlipayMiniprogramService.getByAppidAndUserId(appid,
				userId);
		// @formatter:off
		// UsersAlipayMiniprogram usersAlipayMiniprogram = usersAlipayMiniprogramService.getByAppidAndOpenId(appid,
		// 		openId);
		// UsersAlipayMiniprogram usersAlipayMiniprogram = usersAlipayMiniprogramService.getByAppidAndUnionId(appid,
		// 		unionId);
		// @formatter:on

		if (usersAlipayMiniprogram == null) {
			UsersAlipayMiniprogram users = new UsersAlipayMiniprogram();
			users.setAppid(appid);

			BeanUtils.copyProperties(systemOauthTokenResponse, users);
			BeanUtils.copyProperties(userInfoShareResponse, users);

			usersAlipayMiniprogramService.save(users);
		}
		else {

			BeanUtils.copyProperties(systemOauthTokenResponse, usersAlipayMiniprogram);
			BeanUtils.copyProperties(userInfoShareResponse, usersAlipayMiniprogram);

			usersAlipayMiniprogramService.updateById(usersAlipayMiniprogram);
		}

		return alipayMiniProgramTokenResponse;
	}

	/**
	 * 根据 appid 获取 支付宝小程序属性配置
	 * @param appid 小程序ID
	 * @return 返回 支付宝小程序属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AlipayMiniProgramProperties.AlipayMiniProgram getAlipayMiniProgramByAppid(String appid)
			throws OAuth2AuthenticationException {
		InMemoryAlipayMiniProgramService inMemoryAlipayMiniProgramService = new InMemoryAlipayMiniProgramService(
				alipayMiniProgramProperties);
		return inMemoryAlipayMiniProgramService.getAlipayMiniProgramByAppid(appid);
	}

}
