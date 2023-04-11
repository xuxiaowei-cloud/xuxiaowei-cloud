package cloud.xuxiaowei.system.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * 权限表保存参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class AuthoritiesSaveBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@NotNull(message = "用户ID 不能为空")
	private Long usersId;

	/**
	 * 权限
	 */
	private Set<String> authorityList;

}
