package cloud.xuxiaowei.passport.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

/**
 * 授权表
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Oauth2AuthorizationVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String registeredClientId;

	private String principalName;

	private String authorizationGrantType;

	private String attributes;

	private String state;

	private String authorizationCodeValue;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime authorizationCodeIssuedAt;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime authorizationCodeExpiresAt;

	private String authorizationCodeMetadata;

	private String accessTokenValue;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime accessTokenIssuedAt;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime accessTokenExpiresAt;

	private String accessTokenMetadata;

	private String accessTokenType;

	private String accessTokenScopes;

	private String oidcIdTokenValue;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime oidcIdTokenIssuedAt;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime oidcIdTokenExpiresAt;

	private String oidcIdTokenMetadata;

	private String refreshTokenValue;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime refreshTokenIssuedAt;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime refreshTokenExpiresAt;

	private String refreshTokenMetadata;

	private String clientId;

	private String clientName;

}
