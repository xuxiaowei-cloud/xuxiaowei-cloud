package cloud.xuxiaowei.mybatis.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * MyBatis Plus Generator
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class MyBatisPlusGeneratorTests {

	/**
	 * 数据源
	 *
	 * @author xuxiaowei
	 * @since 0.0.1
	 */
	@Getter
	private enum DataSource {

		/**
		 * 微服务
		 */
		XUXIAOWEI_CLOUD("jdbc:mysql://127.0.0.1:3306/xuxiaowei_cloud", "root", "xuxiaowei.com.cn", "微服务 数据库"),

		/**
		 * 微服务-日志
		 */
		XUXIAOWEI_CLOUD_LOG("jdbc:mysql://127.0.0.1:3306/xuxiaowei_cloud_log", "root", "xuxiaowei.com.cn",
				"微服务 日志 数据库"),

		;

		private final String url;

		private final String username;

		private final String password;

		private final String explain;

		DataSource(String url, String username, String password, String explain) {
			this.url = url;
			this.username = username;
			this.password = password;
			this.explain = explain;
		}

	}

	public static void main(String[] args) {

		DataSource[] values = DataSource.values();

		for (int i = 0; i < values.length; i++) {
			DataSource value = values[i];
			System.out.printf("数据库序号：%s 数据库说明：%s 数据库连接串：%s 数据库用户名：%s%n", i, value.explain, value.url, value.username);
		}

		String dataSourceScanner = scanner("请选择数据库序号");
		int dataSourceNum;

		try {
			int integer = Integer.parseInt(dataSourceScanner);

			if (integer >= 0 && integer < values.length) {
				dataSourceNum = integer;
			}
			else {
				System.err.println("输入数据库序号不在有效范围内");
				main(args);
				return;
			}

		}
		catch (Exception e) {
			System.err.println("输入数据库序号不正确");
			e.printStackTrace();
			main(args);
			return;
		}

		DataSource dataSource = values[dataSourceNum];

		DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig.Builder(dataSource.url, dataSource.username,
				dataSource.password)
			.dbQuery(new MySqlQuery())
			.typeConvert(new MySqlTypeConvert())
			.keyWordsHandler(new MySqlKeyWordsHandler());

		String userDir = System.getProperty("user.dir");
		String fileSeparator = System.getProperty("file.separator");
		// @formatter:off
		List<Module> moduleList = Arrays.asList(new Module("gateway", "cloud.xuxiaowei.gateway"),
				new Module("cloud-commons-parent" + fileSeparator + "cloud-starter-core", "cloud.xuxiaowei.core"),
				new Module("cloud-commons-parent" + fileSeparator + "cloud-starter-log", "cloud.xuxiaowei.log"),
				new Module("cloud-commons-parent" + fileSeparator + "cloud-starter-oauth2", "cloud.xuxiaowei.oauth2"),
				new Module("cloud-commons-parent" + fileSeparator + "cloud-starter-system", "cloud.xuxiaowei.system"),
				new Module("passport", "cloud.xuxiaowei.passport"),
				new Module("resource-services-parent" + fileSeparator + "master-data","cloud.xuxiaowei.masterdata"),
				new Module("resource-services-parent" + fileSeparator + "user","cloud.xuxiaowei.user"),
				new Module("resource-services-parent" + fileSeparator + "wechat-miniprogram","cloud.xuxiaowei.wechatminiprogram")
		);
		// @formatter:on

		System.out.println("项目文件夹：" + userDir);

		System.out.println("模块列表：");
		for (int i = 0; i < moduleList.size(); i++) {
			Module module = moduleList.get(i);
			System.out.println("序号：" + i + "：" + module.getModuleFolder() + "：" + module.getPackageName());
		}

		int moduleNumber = scannerInt("请输入模块名序号？");
		Module module = moduleList.get(moduleNumber);
		String moduleFolder = module.getModuleFolder();
		String packageName = module.getPackageName();

		String[] packageNameSplit = packageName.split("\\.");
		String xmlFolder = packageNameSplit[packageNameSplit.length - 1];

		String mainFolder = userDir + fileSeparator + moduleFolder + fileSeparator + "src" + fileSeparator + "main";

		String javaDir = mainFolder + fileSeparator + "java";
		String xmlDir = mainFolder + fileSeparator + "resources" + fileSeparator + "mapper" + fileSeparator + xmlFolder;

		System.out.println("java 输出目录：" + javaDir);
		System.out.println("xml 输出目录：" + xmlDir);

		FastAutoGenerator.create(dataSourceConfig)
			// 全局配置
			.globalConfig((scanner, builder) -> {
				builder.author(scanner.apply("请输入作者名称？"));

				// 禁止打开输出目录
				builder.disableOpenDir();
				// 输出目录
				builder.outputDir(javaDir);
			})
			// 包配置
			.packageConfig((scanner, builder) -> {
				builder.parent(packageName);

				builder.pathInfo(Collections.singletonMap(OutputFile.xml, xmlDir));
			})
			// 策略配置
			.strategyConfig((scanner, builder) -> builder
				.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
				.controllerBuilder()
				// 开启生成@RestController控制器
				.enableRestStyle()
				// 开启驼峰转连字符
				.enableHyphenStyle()
				.mapperBuilder()
				// 开启baseResultMap
				.enableBaseResultMap()
				.entityBuilder()
				// 开启lombok模型
				.enableLombok()
				.addTableFills(new Column("create_date", FieldFill.INSERT), new Column("update_date", FieldFill.UPDATE),
						new Column("create_users_id", FieldFill.INSERT),
						new Column("update_users_id", FieldFill.UPDATE), new Column("create_ip", FieldFill.INSERT),
						new Column("update_ip", FieldFill.UPDATE))
				.logicDeleteColumnName("deleted")
				.build())
			/*
			 * 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker .templateEngine(new
			 * BeetlTemplateEngine()) .templateEngine(new FreemarkerTemplateEngine())
			 */
			.execute();
	}

	@Data
	@AllArgsConstructor
	private static class Module {

		private String moduleFolder;

		private String packageName;

	}

	/**
	 * 读取控制台内容
	 */
	private static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(("请输入" + tip + "："));
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotBlank(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	/**
	 * 读取控制台内容
	 */
	private static int scannerInt(String tip) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(("请输入" + tip + "："));
		if (scanner.hasNext()) {
			return scanner.nextInt();
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	/**
	 * 处理 all 情况
	 * @param tables 表
	 * @return 返回表名
	 */
	private static List<String> getTables(String tables) {
		return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
	}

}
