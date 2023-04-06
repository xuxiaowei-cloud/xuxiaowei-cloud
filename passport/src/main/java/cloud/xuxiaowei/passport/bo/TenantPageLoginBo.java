package cloud.xuxiaowei.passport.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询租户参数-登录页面
 *
 * @since 0.0.1
 * @author xuxiaowei
 */
@Data
public class TenantPageLoginBo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long current;

	private Long size;

}
