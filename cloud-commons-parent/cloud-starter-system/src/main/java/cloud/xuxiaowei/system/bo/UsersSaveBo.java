package cloud.xuxiaowei.system.bo;

import cloud.xuxiaowei.system.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户表
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class UsersSaveBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名，不能为空，唯一键：uk__users__username
     */
    @UsernameExistAnnotation
    @UsernameLogicAnnotation
    @Length(min = 2, max = 10, message = "用户名 长度限制：2-10")
    @NotEmpty(message = "用户名 不能为空")
    private String username;

    /**
     * 昵称，不能为空，唯一键：uk__users__nickname
     */
    @NicknameExistAnnotation
    @NicknameLogicAnnotation
    @Length(min = 2, max = 10, message = "昵称 长度限制：2-10")
    @NotEmpty(message = "昵称 不能为空")
    private String nickname;

    /**
     * 密码，不能为空
     */
    @NumberAnnotation(message = "密码必须包含数字")
    @LowerCaseAnnotation(message = "密码必须包含小写字母")
    @UpperCaseAnnotation(message = "密码必须包含大写字母")
    @SymbolAnnotation(message = "密码必须包含特殊符号")
    @Length(min = 6, max = 16, message = "密码 长度限制：6-16")
    @NotEmpty(message = "密码 不能为空")
    private String password;

    /**
     * 是否启用，不能为空
     */
    @NotNull(message = "是否启用 不能为空")
    private Boolean enabled;

    /**
     * 帐户未过期，不能为空
     */
    @NotNull(message = "帐户未过期 不能为空")
    private Boolean accountNonExpired;

    /**
     * 凭证未过期，不能为空
     */
    @NotNull(message = "凭证未过期 不能为空")
    private Boolean credentialsNonExpired;

    /**
     * 帐户未锁定，不能为空
     */
    @NotNull(message = "帐户未锁定 不能为空")
    private Boolean accountNonLocked;

}
