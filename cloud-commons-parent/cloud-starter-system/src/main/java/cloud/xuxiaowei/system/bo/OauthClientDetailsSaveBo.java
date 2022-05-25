package cloud.xuxiaowei.system.bo;

import cloud.xuxiaowei.system.annotation.ClientIdExistAnnotation;
import cloud.xuxiaowei.system.annotation.ClientIdLogicAnnotation;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 客户表
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class OauthClientDetailsSaveBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一键：uk__oauth_client_details__client_id
     */
    @ClientIdExistAnnotation
    @ClientIdLogicAnnotation
    @NotEmpty(message = "客户ID 不能为空")
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    /**
     * 用户识别码
     */
    @NotEmpty(message = "用户识别码 不能为空")
    private String code;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

}
