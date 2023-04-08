package cloud.xuxiaowei.utils;

import java.io.Serializable;

/**
 * MDC 常量
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class MdcConstants implements Serializable {

	/**
	 * 租户ID
	 */
	public static final String TENANT_ID = "TENANT_ID";

	/**
	 * 临时租户ID
	 * <p>
	 * 仅在特殊场合下使用，如：登录查询用户前，将请求参数中的租户ID放入MDC中，从Security中获取不到租户ID时会使用MDC中的此值
	 */
	public static final String TMP_TENANT_ID = "TMP_TENANT_ID";

	/**
	 * 用户ID
	 */
	public static final String USERS_ID = "USERS_ID";

	/**
	 * NAME
	 */
	public static final String NAME = "NAME";

	/**
	 * 请求ID
	 */
	public static final String REQUEST_ID = "REQUEST_ID";

	/**
	 * 主机名
	 */
	public static final String HOST_NAME = "HOST_NAME";

	/**
	 * IP
	 */
	public static final String IP = "IP";

}
