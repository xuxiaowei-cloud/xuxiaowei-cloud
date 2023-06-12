package cloud.xuxiaowei.generate.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 表
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class TableBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页，默认值：1
	 */
	private long current = 1;

	/**
	 * 每页显示条数，默认值：10
	 */
	private long size = 10;

	/**
	 * JDBC 连接串
	 * <p>
	 * 多个配置中不能有重复（循环遍历根据此配置进行选择）
	 */
	@NotEmpty(message = "JDBC 连接串 不能为空")
	private String jdbcUrl;

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 表注释
	 */
	private String tableComment;

}
