package cloud.xuxiaowei.generate.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 表信息
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class TableVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * JDBC 连接串
	 * <p>
	 * 多个配置中不能有重复（循环遍历根据此配置进行选择）
	 */
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
