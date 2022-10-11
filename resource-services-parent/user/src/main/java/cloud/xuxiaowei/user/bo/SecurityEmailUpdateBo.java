package cloud.xuxiaowei.user.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 安全设置：修改邮箱参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class SecurityEmailUpdateBo {

	/**
	 * 邮箱
	 */
	@NotEmpty(message = "邮箱不能为空")
	private String email;

	/**
	 * 验证码
	 */
	@NotEmpty(message = "验证码不能为空")
	private String code;

	/**
	 * 标识码
	 */
	@NotEmpty(message = "标识码不能为空")
	private String identification;

}
