package cloud.xuxiaowei.oauth2.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 审计刷新Token分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class AuditRefreshTokenPageBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long current;

    private Long size;

    private Long oauthRefreshTokenId;

    private String tokenId;

    private String username;

    private String clientId;

    private String remoteAddress;

    private String scope;

    private String redirectUri;

    private String refreshToken;

    private String sessionId;

    private String state;

}
