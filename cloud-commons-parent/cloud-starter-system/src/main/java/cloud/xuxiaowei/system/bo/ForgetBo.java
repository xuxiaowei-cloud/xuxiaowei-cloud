package cloud.xuxiaowei.system.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 忘记密码 参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class ForgetBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户名/手机号码/绑定邮箱
	 */
	@NotEmpty(message = "用户名/手机号码/绑定邮箱 不能为空")
	private String username;

}
