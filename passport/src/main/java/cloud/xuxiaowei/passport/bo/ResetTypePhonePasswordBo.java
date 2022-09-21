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
public class ResetTypePhonePasswordBo {

	/**
	 * 用户ID
	 */
	@NotNull(message = "用户ID不能为空")
	private Long usersId;

	/**
	 * 短信验证码
	 */
	@NotEmpty(message = "短信验证码不能为空")
	private String code;

	/**
	 * 密码
	 */
	@NotEmpty(message = "密码不能为空")
	private String password;

}
