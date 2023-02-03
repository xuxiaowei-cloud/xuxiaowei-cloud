package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.system.entity.QqMiniprogramUsers;
import cloud.xuxiaowei.system.service.IQqMiniprogramUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.authorization.client.*;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.properties.QQMiniProgramProperties;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * QQ小程序 账户服务接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 * @see RegisteredClientRepository
 * @see InMemoryRegisteredClientRepository
 * @see JdbcRegisteredClientRepository
 */
@Slf4j
@Service
public class QQMiniProgramServiceImpl implements QQMiniProgramService {

	private QQMiniProgramProperties qqMiniProgramProperties;

	private IQqMiniprogramUsersService qqMiniprogramUsersService;

	@Autowired
	public void setQqMiniProgramProperties(QQMiniProgramProperties qqMiniProgramProperties) {
		this.qqMiniProgramProperties = qqMiniProgramProperties;
	}

	@Autowired
	public void setQqMiniprogramUsersService(IQqMiniprogramUsersService qqMiniprogramUsersService) {
		this.qqMiniprogramUsersService = qqMiniprogramUsersService;
	}

	/**
	 * 认证信息
	 * @param clientPrincipal 经过身份验证的客户端主体
	 * @param additionalParameters 附加参数
	 * @param details 登录信息
	 * @param appid AppID(小程序ID)
	 * @param code @see
	 * <a href= "https://q.qq.com/wiki/develop/game/server/open-port/login.html">登录 -
	 * code2Session</a>
	 * @param openid
	 * 用户唯一标识，<a href= "https://q.qq.com/wiki/develop/game/server/open-port/login.html">登录
	 * - code2Session</a>
	 * @param credentials 证书
	 * @param unionid 如果开发者拥有多个移动应用、网站应用、和小程序，可通过 UnionID
	 * 来区分用户的唯一性，因为只要是同一个QQ互联帐号下的移动应用、网站应用和小程序，用户的 UnionID
	 * 是唯一的。换句话说，同一用户，对同一个QQ互联平台下的不同应用，unionid是相同的。详见 <a href=
	 * "https://q.qq.com/wiki/develop/game/frame/open-ability/union-id.html">UnionID
	 * 机制说明</a>。
	 * @param sessionKey 会话密钥
	 * @return 返回 认证信息
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AbstractAuthenticationToken authenticationToken(Authentication clientPrincipal,
			Map<String, Object> additionalParameters, Object details, String appid, String code, String openid,
			Object credentials, String unionid, String sessionKey) throws OAuth2AuthenticationException {

		QqMiniprogramUsers qqMiniprogramUsers = qqMiniprogramUsersService.getByAppidAndOpenid(appid, openid);
		if (qqMiniprogramUsers == null) {
			QqMiniprogramUsers users = new QqMiniprogramUsers();
			users.setAppid(appid);
			users.setOpenid(openid);
			users.setUnionid(unionid);
			users.setSessionKey(sessionKey);

			qqMiniprogramUsersService.save(users);
		}
		else {
			qqMiniprogramUsers.setSessionKey(sessionKey);

			qqMiniprogramUsersService.updateById(qqMiniprogramUsers);
		}

		InMemoryQQMiniProgramService inMemoryQqMiniProgramService = new InMemoryQQMiniProgramService(
				qqMiniProgramProperties);
		return inMemoryQqMiniProgramService.authenticationToken(clientPrincipal, additionalParameters, details, appid,
				code, openid, credentials, unionid, sessionKey);
	}

	/**
	 * 根据 AppID(小程序ID)、code、jsCode2SessionUrl 获取Token
	 * @param appid AppID(小程序ID)
	 * @param code <a href=
	 * "https://q.qq.com/wiki/develop/game/server/open-port/login.html">登录-code2Session</a>
	 * @param jsCode2SessionUrl
	 * <a href= "https://q.qq.com/wiki/develop/game/server/open-port/login.html">登录 -
	 * code2Session</a>
	 * @return 返回
	 * <a href= "https://q.qq.com/wiki/develop/game/server/open-port/login.html">登录 -
	 * code2Session</a>
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public QQMiniprogramTokenResponse getAccessTokenResponse(String appid, String code, String jsCode2SessionUrl)
			throws OAuth2AuthenticationException {
		InMemoryQQMiniProgramService inMemoryQqMiniProgramService = new InMemoryQQMiniProgramService(
				qqMiniProgramProperties);
		return inMemoryQqMiniProgramService.getAccessTokenResponse(appid, code, jsCode2SessionUrl);
	}

	/**
	 * 根据 appid 获取 QQ小程序属性配置
	 * @param appid 小程序ID
	 * @return 返回 QQ小程序属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public QQMiniProgramProperties.QQMiniProgram getQQMiniProgramByAppid(String appid)
			throws OAuth2AuthenticationException {
		InMemoryQQMiniProgramService inMemoryQqMiniProgramService = new InMemoryQQMiniProgramService(
				qqMiniProgramProperties);
		return inMemoryQqMiniProgramService.getQQMiniProgramByAppid(appid);
	}

}
