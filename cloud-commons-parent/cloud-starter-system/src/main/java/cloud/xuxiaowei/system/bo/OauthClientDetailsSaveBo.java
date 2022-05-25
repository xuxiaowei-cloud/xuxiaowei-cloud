package cloud.xuxiaowei.system.bo;

import cloud.xuxiaowei.system.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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

    private Long oauthClientDetailsId;

    /**
     * 唯一键：uk__oauth_client_details__client_id
     */
    @ClientIdExistAnnotation
    @ClientIdLogicAnnotation
    @NotEmpty(message = "客户ID 不能为空")
    private String clientId;

    private String resourceIds;

    @NumberAnnotation(message = "客户凭证必须包含数字")
    @LowerCaseAnnotation(message = "客户凭证必须包含小写字母")
    @UpperCaseAnnotation(message = "客户凭证必须包含大写字母")
    @SymbolAnnotation(message = "客户凭证必须包含特殊符号")
    @Length(min = 6, max = 16, message = "客户凭证 长度限制：6-16")
    @NotEmpty(message = "客户凭证 不能为空")
    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

}
