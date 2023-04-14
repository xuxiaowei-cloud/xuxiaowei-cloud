package cloud.xuxiaowei.generate.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据源信息
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class DataSourceVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据源名称/别名，用于前端展示
	 */
	private String dataSourceName;

	/**
	 * 驱动
	 */
	private String driverClassName;

	/**
	 * JDBC 连接串
	 * <p>
	 * 多个配置中不能有重复（循环遍历根据此配置进行选择）
	 */
	private String jdbcUrl;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 测试结果
	 */
	private boolean testResult;

	/**
	 * 异常信息
	 */
	private String errorMessage;

}
