package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 代码生成 配置
 * <p>
 * 运行时刷新
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties("cloud.generate")
public class CloudGenerateProperties {

	/**
	 * 数据源配置
	 */
	private List<DataSource> dataSources = new ArrayList<>();

	/**
	 * 生成代码文件夹路径（服务器临时文件储存路径）
	 */
	private String folderPath = "/xuxiaowei-cloud-generate";

	/**
	 * 父包名
	 */
	private String basePackage = "cloud.xuxiaowei";

	/**
	 * 模块名
	 */
	private List<String> modules = Arrays.asList("core", "idempotent", "loadbalancer", "log", "mybatis", "oauth2",
			"openfeign", "redis", "session", "system", "validation", "gateway", "passport", "canal", "file",
			"masterdata", "user", "webservice", "websocket", "wechatminiprogram", "wechatoffiaccount");

	/**
	 * Controller 包名
	 */
	private String controllerPackageName = "controller";

	/**
	 * Controller 后缀名
	 */
	private String controllerSuffixName = "RestController";

	/**
	 * Service 接口 包名
	 */
	private String servicePackageName = "service";

	/**
	 * Service 接口 后缀名
	 */
	private String serviceSuffixName = "Service";

	/**
	 * Service 实现类 包名
	 */
	private String serviceImplPackageName = "service.impl";

	/**
	 * Service 实现类 后缀名
	 */
	private String serviceImplSuffixName = "ServiceImpl";

	/**
	 * Mapper 接口 包名
	 */
	private String mapperPackageName = "mapper";

	/**
	 * Mapper 接口 后缀名
	 */
	private String mapperSuffixName = "Mapper";

	/**
	 * Mapper XML 包名
	 */
	private String mapperXmlPackageName = "mapper";

	/**
	 * Mapper XML 后缀名
	 */
	private String mapperXmlSuffixName = "Mapper";

	/**
	 * Mapper XML 是否拼接 module 名称
	 */
	private boolean mapperXmlJoinModule = true;

	/**
	 * Entity 包名
	 */
	private String entityPackageName = "entity";

	/**
	 * Entity 后缀名
	 */
	private String entitySuffixName = "";

	/**
	 * BO 包名
	 */
	private String boPackageName = "bo";

	/**
	 * BO 后缀名
	 */
	private String boSuffixName = "Bo";

	/**
	 * VO 包名
	 */
	private String voPackageName = "vo";

	/**
	 * VO 后缀名
	 */
	private String voSuffixName = "Vo";

	/**
	 * 数据源
	 *
	 * @author xuxiaowei
	 * @since 0.0.1
	 */
	@Data
	public static class DataSource {

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
		 * 密码
		 */
		private String password;

	}

}
