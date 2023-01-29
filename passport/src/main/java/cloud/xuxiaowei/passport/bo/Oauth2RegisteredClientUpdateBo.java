package cloud.xuxiaowei.passport.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

/**
 * <p>
 * 客户表 更新参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Oauth2RegisteredClientUpdateBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户内部ID
	 */
	private String id;

	/**
	 * 客户ID
	 */
	private String clientId;

	/**
	 * 客户ID发布时间
	 */
	@Schema(pattern = NORM_DATETIME_PATTERN)
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime clientIdIssuedAt;

	/**
	 * 客户秘钥
	 */
	private String clientSecret;

	/**
	 * 客户秘钥过期时间
	 */
	@Schema(pattern = NORM_DATETIME_PATTERN)
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime clientSecretExpiresAt;

	/**
	 * 用户识别码
	 */
	@NotEmpty(message = "用户识别码 不能为空")
	private String code;

	/**
	 * 客户名称
	 */
	@NotEmpty(message = "clientName 不能为空")
	private String clientName;

	/**
	 * 客户授权方法
	 */
	@NotEmpty(message = "clientAuthenticationMethods 不能为空")
	private String clientAuthenticationMethods;

	/**
	 * 授权类型
	 */
	private String authorizationGrantTypes;

	/**
	 * 重定向地址
	 */
	private String redirectUris;

	/**
	 * 授权范围
	 */
	@NotEmpty(message = "scopes 不能为空")
	private String scopes;

	/**
	 * 请求验证秘钥
	 */
	@NotNull(message = "requireProofKey 不能为空")
	private Boolean requireProofKey;

	/**
	 * 授权同意书
	 */
	@NotNull(message = "requireAuthorizationConsent 不能为空")
	private Boolean requireAuthorizationConsent;

	/**
	 * 授权码有效期
	 */
	@NotNull(message = "authorizationCodeTimeToLive 不能为空")
	private Long authorizationCodeTimeToLive;

	/**
	 * 授权Token有效期
	 */
	@NotNull(message = "accessTokenTimeToLive 不能为空")
	private Long accessTokenTimeToLive;

	/**
	 * 刷新Token有效期
	 */
	@NotNull(message = "refreshTokenTimeToLive 不能为空")
	private Long refreshTokenTimeToLive;

	/**
	 * 令牌端点对客户端进行身份验证的算法
	 */
	private String tokenSigningAlgorithm;

	/**
	 * 签名算法
	 */
	private String tokenSignatureAlgorithm;

	/**
	 * JWT URL
	 */
	@NotNull(message = "jwkSetUrl 不能为空")
	@URL(message = "jwkSetUrl 不合法")
	private String jwkSetUrl;

	/**
	 * 重用刷新令牌
	 */
	private Boolean reuseRefreshTokens;

	/**
	 * 授权Token格式
	 */
	private String accessTokenFormat;

}
