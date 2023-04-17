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
public class ColumnFieldVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据库 字段名
	 */
	private String field;

	/**
	 * 数据库 字段类型
	 */
	private String type;

	/**
	 * Java 属性类型
	 */
	private String propertyType;

	/**
	 * Java 属性名
	 */
	private String propertyName;

	/**
	 * 首字母大写的驼峰命名法，用于生成 Getter/Setter
	 */
	private String capitalName;

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

	/**
	 * 字段长度
	 */
	private Integer length;

	/**
	 * 验证注解
	 */
	private String validation;

	/**
	 * 验证注解消息
	 */
	private String message;

}
