package cloud.xuxiaowei.generate.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
	 * 表注释（for java）
	 */
	private String tableComment4j;

	/**
	 * BO 忽略字段名
	 */
	private List<String> boIgnorePropertyNames;

	/**
	 * VO 忽略字段名
	 */
	private List<String> voIgnorePropertyNames;

	/**
	 * class 名称
	 */
	private String className;

	/**
	 * BO 基础 class 名称
	 */
	private String boBaseClassName;

	/**
	 * BO 分页 class 名称
	 */
	private String boPageClassName;

	/**
	 * BO 保存 class 名称
	 */
	private String boSaveClassName;

	/**
	 * BO 更新 class 名称
	 */
	private String boUpdateClassName;

	/**
	 * VO class 名称
	 */
	private String voClassName;

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
	 * {@link JsonFormat}
	 */
	private boolean jsonFormat;

	/**
	 * {@link DatePattern}
	 */
	private boolean datePattern;

	/**
	 * {@link Length}
	 */
	private boolean length;

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
