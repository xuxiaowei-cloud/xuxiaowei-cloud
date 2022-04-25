package cloud.xuxiaowei.oauth2.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 审计授权Token分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class AuditAccessTokenPageBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long current;

    private Long size;

    private Long oauthAccessTokenId;

    private String refreshToken;

    private String userName;

    private String clientId;

    private String remoteAddress;

    private String scope;

    private String redirectUri;

    private String responseType;

    private String accessToken;

    private String authenticationId;

    private String jti;

    private String refreshTokenEncryption;

    private String tokenId;

    private String sessionId;

    private String state;

}
