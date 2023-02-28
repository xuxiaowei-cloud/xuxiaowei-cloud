package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.system.entity.UsersWxMa;
import cloud.xuxiaowei.system.service.IUsersWxMaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.authorization.client.*;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.properties.WeChatMiniProgramProperties;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信小程序 账户服务接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 * @see RegisteredClientRepository
 * @see InMemoryRegisteredClientRepository
 * @see JdbcRegisteredClientRepository
 */
@Slf4j
@Service
public class WeChatMiniProgramServiceImpl implements WeChatMiniProgramService {

	private WeChatMiniProgramProperties weChatMiniProgramProperties;

	private IUsersWxMaService usersWxMaService;

	@Autowired
	public void setWeChatMiniProgramProperties(WeChatMiniProgramProperties weChatMiniProgramProperties) {
		this.weChatMiniProgramProperties = weChatMiniProgramProperties;
	}

	@Autowired
	public void setUsersWxMaService(IUsersWxMaService usersWxMaService) {
		this.usersWxMaService = usersWxMaService;
	}

	/**
	 * 认证信息
	 * @param appid AppID(小程序ID)
	 * @param openid 用户唯一标识，<a href=
	 * "https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html">登录
	 * - code2Session</a>
	 * @param unionid 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 <a href=
	 * "https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/union-id.html">UnionID
	 * 机制说明</a>。
	 * @param sessionKey 会话密钥
	 * @param details 登录信息
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

		UsersWxMa usersWxMa = usersWxMaService.getByAppidAndOpenid(appid, openid);
		if (usersWxMa == null) {
			UsersWxMa users = new UsersWxMa();
			users.setAppid(appid);
			users.setOpenid(openid);
			users.setUnionid(unionid);
			users.setSessionKey(sessionKey);

			usersWxMaService.save(users);
		}
		else {
			usersWxMa.setSessionKey(sessionKey);

			usersWxMaService.updateById(usersWxMa);
		}

		InMemoryWeChatMiniProgramService inMemoryWeChatMiniProgramService = new InMemoryWeChatMiniProgramService(
				weChatMiniProgramProperties);
		return inMemoryWeChatMiniProgramService.authenticationToken(clientPrincipal, additionalParameters, details,
				appid, code, openid, credentials, unionid, sessionKey);
	}

	/**
	 * 根据 AppID(小程序ID)、code、jsCode2SessionUrl 获取Token
	 * @param appid AppID(小程序ID)
	 * @param code <a href=
	 * "https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/wx.login.html">开放接口
	 * - 登录 - wx.login</a>，<a href=
	 * "https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html">登录
	 * - code2Session</a>
	 * @param jsCode2SessionUrl <a href=
	 * "https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html">登录
	 * - code2Session</a>
	 * @return 返回 <a href=
	 * "https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html">登录
	 * - code2Session</a>
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public WeChatMiniProgramTokenResponse getAccessTokenResponse(String appid, String code, String jsCode2SessionUrl)
			throws OAuth2AuthenticationException {
		InMemoryWeChatMiniProgramService inMemoryWeChatMiniProgramService = new InMemoryWeChatMiniProgramService(
				weChatMiniProgramProperties);
		return inMemoryWeChatMiniProgramService.getAccessTokenResponse(appid, code, jsCode2SessionUrl);
	}

	/**
	 * 根据 appid 获取 微信小程序属性配置
	 * @param appid 小程序ID
	 * @return 返回 微信小程序属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public WeChatMiniProgramProperties.WeChatMiniProgram getWeChatMiniProgramByAppid(String appid)
			throws OAuth2AuthenticationException {
		InMemoryWeChatMiniProgramService inMemoryWeChatMiniProgramService = new InMemoryWeChatMiniProgramService(
				weChatMiniProgramProperties);
		return inMemoryWeChatMiniProgramService.getWeChatMiniProgramByAppid(appid);
	}

}
