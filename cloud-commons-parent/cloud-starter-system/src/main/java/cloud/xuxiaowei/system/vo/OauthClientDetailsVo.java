package cloud.xuxiaowei.system.vo;

import lombok.Data;

/**
 * 客户 返回值
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class OauthClientDetailsVo {

    private Long oauthClientDetailsId;

    /**
     * 唯一键：uk__oauth_client_details__client_id
     */
    private String clientId;

    private String resourceIds;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

}
