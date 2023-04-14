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
	 * 表注释
	 */
	private String tableComment;

	/**
	 * class 名称
	 */
	private String className;

	/**
	 * 包名
	 */
	private String packageName;

	/**
	 * lombok 注解
	 */
	private boolean lombokModel;

	/**
	 * {@link javax.validation.constraints.NotEmpty} 注解
	 */
	private boolean notEmpty;

	/**
	 * {@link javax.validation.constraints.NotNull} 注解
	 */
	private boolean notNull;

	/**
	 * {@link java.time.LocalDate} 注解
	 */
	private boolean localDate;

	/**
	 * {@link java.time.LocalDateTime} 注解
	 */
	private boolean localDateTime;

	/**
	 * {@link java.time.LocalTime} 注解
	 */
	private boolean localTime;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 版本号
	 */
	private String since;

	/**
	 * 列信息
	 */
	private List<ColumnFieldVo> fields;

}
