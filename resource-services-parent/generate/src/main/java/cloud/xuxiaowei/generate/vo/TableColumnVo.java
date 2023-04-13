package cloud.xuxiaowei.generate.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表中列信息
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class TableColumnVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 列信息
	 */
	private List<ColumnVo> columnVoList;

}
