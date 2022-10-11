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
public class SecurityEmailBo {

	/**
	 * 邮箱
	 */
	@NotEmpty(message = "邮箱不能为空")
	private String email;

}
