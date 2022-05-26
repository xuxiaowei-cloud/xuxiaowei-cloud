package cloud.xuxiaowei.system.bo;

import cloud.xuxiaowei.system.annotation.OauthClientDetailsIdAnnotation;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 客户表
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class OauthClientDetailsUpdateBo implements Serializable {

    private static final long serialVersionUID = 1L;

    @OauthClientDetailsIdAnnotation
    @NotNull(message = "客户主键 不能为空")
    private Long oauthClientDetailsId;

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
