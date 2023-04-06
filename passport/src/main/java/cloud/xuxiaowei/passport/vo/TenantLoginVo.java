package cloud.xuxiaowei.passport.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询租户结果-登录页面
 *
 * @since 0.0.1
 * @author xuxiaowei
 */
@Data
public class TenantLoginVo implements Serializable {

	private static final long serialVersionUID = -1539425664942086369L;

	private Long tenantId;

	private String tenantName;

}
