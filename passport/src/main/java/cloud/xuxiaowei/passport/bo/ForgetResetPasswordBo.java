package cloud.xuxiaowei.passport.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 重置密码
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class ForgetResetPasswordBo {

	/**
	 * 用户ID
	 */
	@NotNull(message = "用户ID不能为空")
	private Long usersId;

	/**
	 * 重置密码凭证
	 */
	@NotEmpty(message = "重置密码凭证不能为空")
	private String resetPasswordToken;

	/**
	 * 密码
	 */
	@NotEmpty(message = "密码不能为空")
	private String password;

}
