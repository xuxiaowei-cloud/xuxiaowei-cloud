package cloud.xuxiaowei.system.bo;

import cloud.xuxiaowei.system.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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

    @NumberAnnotation(message = "客户凭证必须包含数字")
    @LowerCaseAnnotation(message = "客户凭证必须包含小写字母")
    @UpperCaseAnnotation(message = "客户凭证必须包含大写字母")
    @SymbolAnnotation(message = "客户凭证必须包含特殊符号")
    @Length(min = 6, max = 16, message = "客户凭证 长度限制：6-16")
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
