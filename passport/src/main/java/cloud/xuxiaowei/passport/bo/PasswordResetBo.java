package cloud.xuxiaowei.passport.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 密码重置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class PasswordResetBo {

	/**
	 * 密码
	 */
	@NotEmpty(message = "密码不能为空")
	private String password;

}
