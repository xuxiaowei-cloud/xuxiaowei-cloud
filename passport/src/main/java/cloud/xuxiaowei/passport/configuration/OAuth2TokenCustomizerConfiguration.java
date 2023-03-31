package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.WeChatMiniProgramAuthenticationToken;
import org.springframework.security.authentication.WeChatOplatformWebsiteAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.endpoint.OAuth2WeChatMiniProgramParameterNames;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义 JWT 编码器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class OAuth2TokenCustomizerConfiguration implements OAuth2TokenCustomizer<JwtEncodingContext> {

	private IUsersService usersService;

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	/**
	 * JWK 增加用户权限
	 *
	 * @see <a href=
	 * "https://github.com/spring-projects/spring-authorization-server/issues/181#issuecomment-756913042">将用户权限作为声明传播`Jwt`是一种自定义行为</a>
	 * @see <a href=
	 * "https://github.com/spring-projects/spring-authorization-server/blob/a30a1692b28915947a001f1e2a6d1e41a550eaa7/oauth2-authorization-server/src/test/java/org/springframework/security/config/annotation/web/configurers/oauth2/server/authorization/OidcTests.java#L264">自定义
	 * Jwt 声明和标头官方示例代码</a>
	 * @see <a href=
	 * "https://github.com/spring-projects/spring-authorization-server/issues/199">自定义 Jwt
	 * 声明和标头需要更灵活 议题</a>
	 */
	@Override
	public void customize(JwtEncodingContext context) {

		// OAuth2 Token 类型
		OAuth2TokenType tokenType = context.getTokenType();

		// JWT 构建器
		JwtClaimsSet.Builder claims = context.getClaims();

		// 用户认证
		Authentication principal = context.getPrincipal();

		// 客户权限
		Set<String> authorizedScopes = context.getAuthorizedScopes();

		if (OAuth2TokenType.ACCESS_TOKEN.equals(tokenType)) {

			// 放入用户名
			String name = principal.getName();
			claims.claim(Constant.USERNAME, name);

			// 放入用户ID
			Users users = usersService.getByUsername(name);
			if (users != null) {
				// The class with java.lang.Long and name of java.lang.Long is not in
				// the allowlist.
				// If you believe this class is safe to deserialize, please provide an
				// explicit mapping using Jackson annotations or by providing a Mixin.
				// If the serialization is only done by a trusted source, you can also
				// enable default typing.
				// See https://github.com/spring-projects/spring-security/issues/4370
				// for details
				claims.claim(Constant.USERS_ID, String.valueOf(users.getUsersId()));
			}

			// 用户权限
			Set<String> authorities = principal.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toSet());

			// 合并权限
			authorities.addAll(authorizedScopes);

			// 将合并权限放入 JWT 中
			claims.claim(Constant.AUTHORITIES, authorities);

			/// 微信用户的权限特殊处理
			// 增加微信特有数据
			if (principal instanceof WeChatMiniProgramAuthenticationToken) {
				WeChatMiniProgramAuthenticationToken authenticationToken = (WeChatMiniProgramAuthenticationToken) principal;
				// 微信小程序的appid，不能为空
				String appid = authenticationToken.getAppid();
				// 用户唯一标识，不能为空
				String openid = authenticationToken.getOpenid();
				// 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 <a
				// href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/union-id.html">UnionID
				// 机制说明</a>。
				String unionid = authenticationToken.getUnionid();
				claims.claim(OAuth2WeChatMiniProgramParameterNames.APPID, appid);
				claims.claim(OAuth2WeChatMiniProgramParameterNames.OPENID, openid);
				claims.claim(OAuth2WeChatMiniProgramParameterNames.UNIONID, unionid);
			}
			else if (principal instanceof WeChatOplatformWebsiteAuthenticationToken) {
				WeChatOplatformWebsiteAuthenticationToken authenticationToken = (WeChatOplatformWebsiteAuthenticationToken) principal;
				// 微信公众平台 网站应用 的appid，不能为空
				String appid = authenticationToken.getAppid();
				// 用户唯一标识，不能为空
				String openid = authenticationToken.getOpenid();
				// 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 <a
				// href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/union-id.html">UnionID
				// 机制说明</a>。
				String unionid = authenticationToken.getUnionid();
				claims.claim(OAuth2WeChatMiniProgramParameterNames.APPID, appid);
				claims.claim(OAuth2WeChatMiniProgramParameterNames.OPENID, openid);
				claims.claim(OAuth2WeChatMiniProgramParameterNames.UNIONID, unionid);
			}
		}

	}

}
