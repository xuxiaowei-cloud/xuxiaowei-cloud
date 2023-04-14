package cloud.xuxiaowei.generate.service.impl;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.bo.GenerateBo;
import cloud.xuxiaowei.generate.bo.TableBo;
import cloud.xuxiaowei.generate.bo.TableColumnBo;
import cloud.xuxiaowei.generate.vo.ColumnFieldVo;
import cloud.xuxiaowei.generate.vo.DataSourceVo;
import cloud.xuxiaowei.generate.vo.TableColumnVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成 服务接口 实现类 测试类
 * <p>
 * 非 Spring Boot 测试类
 * <p>
 * 即：代码生成可以脱离 Spring Boot 直接运行
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class GenerateServiceImplTests {

	@Test
	void listDataSources() {

		String dataSourceName = "微服务数据库";
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
		String username = "root";
		String password = "xuxiaowei.com.cn";

		List<CloudGenerateProperties.DataSource> dataSources = new ArrayList<>();
		CloudGenerateProperties.DataSource dataSource = new CloudGenerateProperties.DataSource();
		dataSources.add(dataSource);
		dataSource.setDataSourceName(dataSourceName);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		CloudGenerateProperties cloudGenerateProperties = new CloudGenerateProperties();
		cloudGenerateProperties.setDataSources(dataSources);

		GenerateServiceImpl generateService = new GenerateServiceImpl();
		generateService.setCloudGenerateProperties(cloudGenerateProperties);

		List<DataSourceVo> dataSourceVos = generateService.listDataSources();
		for (DataSourceVo dataSourceVo : dataSourceVos) {
			log.info(String.valueOf(dataSourceVo));
		}
	}

	@Test
	void test() {
		String dataSourceName = "微服务数据库";
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
		String username = "root";
		String password = "xuxiaowei.com.cn";

		CloudGenerateProperties.DataSource dataSource = new CloudGenerateProperties.DataSource();
		dataSource.setDataSourceName(dataSourceName);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		GenerateServiceImpl generateService = new GenerateServiceImpl();

		generateService.test(dataSource);
	}

	@Test
	void listTables() {
		String dataSourceName = "微服务数据库";
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
		String username = "root";
		String password = "xuxiaowei.com.cn";

		List<CloudGenerateProperties.DataSource> dataSources = new ArrayList<>();
		CloudGenerateProperties.DataSource dataSource = new CloudGenerateProperties.DataSource();
		dataSources.add(dataSource);
		dataSource.setDataSourceName(dataSourceName);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		CloudGenerateProperties cloudGenerateProperties = new CloudGenerateProperties();
		cloudGenerateProperties.setDataSources(dataSources);

		GenerateServiceImpl generateService = new GenerateServiceImpl();
		generateService.setCloudGenerateProperties(cloudGenerateProperties);

		TableBo tableBo = new TableBo();
		tableBo.setJdbcUrl(jdbcUrl);
		IPage<TableVo> page = generateService.listTables(tableBo);
		log.info(String.valueOf(page.getCurrent()));
		log.info(String.valueOf(page.getSize()));
		log.info(String.valueOf(page.getTotal()));
		for (TableVo tableVo : page.getRecords()) {
			log.info(String.valueOf(tableVo));
		}
	}

	@Test
	void listTableColumns() {

		String dataSourceName = "微服务数据库";
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
		String username = "root";
		String password = "xuxiaowei.com.cn";

		List<CloudGenerateProperties.DataSource> dataSources = new ArrayList<>();
		CloudGenerateProperties.DataSource dataSource = new CloudGenerateProperties.DataSource();
		dataSources.add(dataSource);
		dataSource.setDataSourceName(dataSourceName);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		List<String> tableNames = new ArrayList<>();
		tableNames.add("users");

		TableColumnBo tableColumnBo = new TableColumnBo();
		tableColumnBo.setTableNames(tableNames);
		tableColumnBo.setJdbcUrl(jdbcUrl);

		CloudGenerateProperties cloudGenerateProperties = new CloudGenerateProperties();
		cloudGenerateProperties.setDataSources(dataSources);

		GenerateServiceImpl generateService = new GenerateServiceImpl();
		generateService.setCloudGenerateProperties(cloudGenerateProperties);

		List<TableColumnVo> tableColumnVos = generateService.listTableColumns(tableColumnBo);
		for (TableColumnVo tableColumnVo : tableColumnVos) {
			String tableName = tableColumnVo.getTableName();
			String tableComment = tableColumnVo.getTableComment();
			List<ColumnFieldVo> columnFieldVoList = tableColumnVo.getFields();
			log.info(tableName);
			log.info(tableComment);
			for (ColumnFieldVo columnFieldVo : columnFieldVoList) {
				log.info(String.valueOf(columnFieldVo));
			}
		}
	}

	@Test
	void generate() {
		String dataSourceName = "微服务数据库";
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
		String username = "root";
		String password = "xuxiaowei.com.cn";

		List<CloudGenerateProperties.DataSource> dataSources = new ArrayList<>();
		CloudGenerateProperties.DataSource dataSource = new CloudGenerateProperties.DataSource();
		dataSources.add(dataSource);
		dataSource.setDataSourceName(dataSourceName);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		List<String> tableNames = new ArrayList<>();
		tableNames.add("users");

		CloudGenerateProperties cloudGenerateProperties = new CloudGenerateProperties();
		cloudGenerateProperties.setDataSources(dataSources);
		GenerateBo generateBo = new GenerateBo();
		BeanUtils.copyProperties(cloudGenerateProperties, generateBo);
		generateBo.setJdbcUrl(jdbcUrl);
		generateBo.setTableNames(tableNames);
		generateBo.setModule("system");
		generateBo.setAuthor("xuxiaowei");
		generateBo.setSince("0.0.1");

		GenerateServiceImpl generateService = new GenerateServiceImpl();
		generateService.setCloudGenerateProperties(cloudGenerateProperties);

		generateService.generate(generateBo);

	}

}
