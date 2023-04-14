package cloud.xuxiaowei.generate.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 表字段
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class TableColumnBo implements Serializable {

	private static final long serialVersionUID = 1L;

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
	@NotNull(message = "表名不能为空")
	@Size(min = 1, message = "表名不能为空")
	private List<String> tableNames;

}
