package cloud.xuxiaowei.user.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 社交绑定、解绑 参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class SocialBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 社交类型：1：微信扫码
	 */
	@NotEmpty(message = "社交类型不能为空")
	private String socialCode;

}
