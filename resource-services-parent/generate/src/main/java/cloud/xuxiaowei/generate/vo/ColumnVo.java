package cloud.xuxiaowei.generate.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列信息
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class ColumnVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 字段名
	 */
	private String field;

	/**
	 * 字段类型
	 */
	private String type;

	/**
	 * 不为 null
	 */
	private String nullColumn;

	/**
	 * 键
	 */
	private String key;

	/**
	 * 默认
	 */
	private String defaultColumn;

	/**
	 * 拓展
	 */
	private String extra;

	/**
	 * 字段注释
	 */
	private String comment;

}
