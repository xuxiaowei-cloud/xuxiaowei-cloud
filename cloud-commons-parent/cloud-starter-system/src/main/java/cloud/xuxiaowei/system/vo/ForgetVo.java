package cloud.xuxiaowei.system.vo;

import cn.hutool.core.util.DesensitizedUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 忘记密码 响应
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class ForgetVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 需要脱敏，防止使用其他信息非法获取绑定的邮箱
	 */
	@Getter(AccessLevel.NONE)
	private String email;

	public String getEmail() {
		return DesensitizedUtil.email(email);
	}

	/**
	 * 需要脱敏，防止使用其他信息非法获取绑定的手机号
	 */
	@Getter(AccessLevel.NONE)
	private String phone;

	public String getPhone() {
		return DesensitizedUtil.mobilePhone(phone);
	}

}
