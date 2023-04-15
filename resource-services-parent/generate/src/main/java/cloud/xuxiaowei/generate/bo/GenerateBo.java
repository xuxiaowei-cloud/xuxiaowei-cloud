package cloud.xuxiaowei.generate.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 生成
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class GenerateBo implements Serializable {

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

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 版本号
	 */
	private String since;

	/**
	 * 父包名
	 */
	private String basePackage;

	/**
	 * 模块名
	 */
	private String module;

	/**
	 * lombok 注解
	 */
	private boolean lombokModel;

	/**
	 * BO 包名
	 */
	private String boPackageName;

	/**
	 * BO 后缀名
	 */
	private String boSuffixName;

}
