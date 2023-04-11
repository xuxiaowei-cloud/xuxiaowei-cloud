package cloud.xuxiaowei.passport.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 租户 分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class TenantPageBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页，默认值：1
	 */
	private long current = 1L;

	/**
	 * 每页显示条数，默认值：10
	 */
	private long size = 10L;

	/**
	 * 租户ID，自增，主键
	 */
	private Long tenantId;

	/**
	 * 租户名称，不为空
	 */
	private String tenantName;

}
