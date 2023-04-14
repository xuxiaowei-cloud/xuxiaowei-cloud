package cloud.xuxiaowei.generate.service.impl;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.service.GenerateService;
import cloud.xuxiaowei.generate.vo.DataSourceVo;
import cloud.xuxiaowei.generate.vo.TableColumnVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成 服务接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class GenerateServiceImpl implements GenerateService {

	private CloudGenerateProperties cloudGenerateProperties;

	@Autowired
	public void setCloudGenerateProperties(CloudGenerateProperties cloudGenerateProperties) {
		this.cloudGenerateProperties = cloudGenerateProperties;
	}

	/**
	 * 列出所有的数据源信息
	 * @return 返回 数据源信息
	 */
	@Override
	public List<DataSourceVo> listDataSources() {

		List<CloudGenerateProperties.DataSource> dataSources = cloudGenerateProperties.getDataSources();

		List<DataSourceVo> list = new ArrayList<>();
		for (CloudGenerateProperties.DataSource ds : dataSources) {

			DataSourceVo dataSourceVo = new DataSourceVo();
			BeanUtils.copyProperties(ds, dataSourceVo);
			list.add(dataSourceVo);

			try {
				// 连接失败时将抛出运行时异常
				test(ds);
				dataSourceVo.setTestResult(true);
			}
			catch (Exception e) {
				dataSourceVo.setErrorMessage(e.getMessage());
				log.error("连接数据库：{} 测试异常", ds.getJdbcUrl(), e);
			}

		}

		return list;
	}

	/**
	 * 测试数据源是否能正常连接
	 * @param dataSource 数据源配置
	 * @throws RuntimeException 连接失败时将抛出运行时异常
	 */
	@Override
	public void test(@NonNull CloudGenerateProperties.DataSource dataSource) throws RuntimeException {

		try (HikariDataSource hikariDataSource = new HikariDataSource()) {
			hikariDataSource.setDriverClassName(dataSource.getDriverClassName());
			hikariDataSource.setJdbcUrl(dataSource.getJdbcUrl());
			hikariDataSource.setUsername(dataSource.getUsername());
			hikariDataSource.setPassword(dataSource.getPassword());

			try (Connection connection = hikariDataSource.getConnection()) {

				PreparedStatement preparedStatement = connection.prepareStatement("select 3 * 4 as a");

				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					int a = resultSet.getInt("a");
					log.info("连接数据库：{} 测试结果：{}", dataSource.getJdbcUrl(), a);
				}
			}
			catch (SQLException e) {
				log.error("连接数据库：{} 测试异常", dataSource.getJdbcUrl(), e);
				throw new RuntimeException(e);
			}

		}
		catch (Exception e) {
			log.error("HikariCP 异常", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 展示所有的表信息
	 * @param dataSource 数据源配置
	 * @param current 当前页，默认值：1
	 * @param size 每页条数，默认值：10
	 * @param tableName 表名，模糊搜索
	 * @param tableComment 表注释，模糊搜索
	 * @return 返回 表信息
	 */
	@Override
	public IPage<TableVo> listTables(CloudGenerateProperties.DataSource dataSource, Long current, Long size,
			String tableName, String tableComment) {

		current = current == null ? 1 : current;
		size = size == null ? 10 : size;

		return null;
	}

	/**
	 * 列出所有的字段信息
	 * @param dataSource 数据源配置
	 * @param tableNames 表名
	 * @return 返回
	 */
	@Override
	public List<TableColumnVo> listTableColumns(CloudGenerateProperties.DataSource dataSource,
			List<String> tableNames) {
		return null;
	}

}
