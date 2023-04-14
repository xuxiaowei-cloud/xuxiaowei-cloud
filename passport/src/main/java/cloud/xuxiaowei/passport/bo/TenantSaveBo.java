package cloud.xuxiaowei.passport.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-04
 */
@Data
public class TenantSaveBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户名称，不为空
	 */
	@NotEmpty(message = "租户名称 不能为空")
	private String tenantName;

	/**
	 * 用户ID，租户超级管理员，不为空
	 */
	@NotNull(message = "用户ID 不能为空")
	private Long usersId;

	/**
	 * 是否启用，不能为空
	 */
	@NotNull(message = "是否启用 不能为空")
	private Boolean enabled;

	/**
	 * 租户域名，用于后期增加租户成员使用该域名邮箱直接登录
	 */
	private String domainName;

	/**
	 * 用户备注信息，只有在查询用户关系时才返回此字段
	 */
	private String remark;

}
