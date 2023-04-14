package cloud.xuxiaowei.generate.service.impl;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.bo.TableBo;
import cloud.xuxiaowei.generate.vo.DataSourceVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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

		List<CloudGenerateProperties.DataSource> dataSources = new ArrayList<>();
		CloudGenerateProperties.DataSource dataSource = new CloudGenerateProperties.DataSource();
		dataSources.add(dataSource);
		dataSource.setDataSourceName("微服务数据库");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		// @formatter:off
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8");
		// @formatter:on
		dataSource.setUsername("root");
		dataSource.setPassword("xuxiaowei.com.cn");

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
		CloudGenerateProperties.DataSource dataSource = new CloudGenerateProperties.DataSource();
		dataSource.setDataSourceName("微服务数据库");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		// @formatter:off
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8");
		// @formatter:on
		dataSource.setUsername("root");
		dataSource.setPassword("xuxiaowei.com.cn");

		GenerateServiceImpl generateService = new GenerateServiceImpl();

		generateService.test(dataSource);
	}

	@Test
	void listTables() {
		List<CloudGenerateProperties.DataSource> dataSources = new ArrayList<>();
		CloudGenerateProperties.DataSource dataSource = new CloudGenerateProperties.DataSource();
		dataSources.add(dataSource);
		dataSource.setDataSourceName("微服务数据库");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		// @formatter:off
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8");
		// @formatter:on
		dataSource.setUsername("root");
		dataSource.setPassword("xuxiaowei.com.cn");

		CloudGenerateProperties cloudGenerateProperties = new CloudGenerateProperties();
		cloudGenerateProperties.setDataSources(dataSources);

		GenerateServiceImpl generateService = new GenerateServiceImpl();
		generateService.setCloudGenerateProperties(cloudGenerateProperties);

		TableBo tableBo = new TableBo();
		// @formatter:off
		tableBo.setJdbcUrl("jdbc:mysql://127.0.0.1/xuxiaowei_cloud?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8");
		// @formatter:on
		IPage<TableVo> page = generateService.listTables(tableBo);
		log.info(String.valueOf(page.getCurrent()));
		log.info(String.valueOf(page.getSize()));
		log.info(String.valueOf(page.getTotal()));
		for (TableVo tableVo : page.getRecords()) {
			log.info(String.valueOf(tableVo));
		}
	}

}
