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
public class SecurityPhoneBo {

	/**
	 * 手机号
	 */
	@NotEmpty(message = "手机号不能为空")
	private String phone;

}
