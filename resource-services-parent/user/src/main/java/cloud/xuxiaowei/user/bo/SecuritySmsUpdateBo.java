package cloud.xuxiaowei.user.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 安全设置：修改手机号参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class SecuritySmsUpdateBo {

	/**
	 * 手机号
	 */
	@NotEmpty(message = "手机号不能为空")
	private String phone;

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
