package cloud.xuxiaowei.generate.service.impl;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.bo.TableBo;
import cloud.xuxiaowei.generate.service.GenerateService;
import cloud.xuxiaowei.generate.vo.DataSourceVo;
import cloud.xuxiaowei.generate.vo.TableColumnVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
	}

	/**
	 * 列出所有的表信息
	 * @param tableBo 表
	 * @return 返回 表信息
	 */
	@Override
	public IPage<TableVo> listTables(TableBo tableBo) {
		long current = tableBo.getCurrent();
		long size = tableBo.getSize();
		String jdbcUrl = tableBo.getJdbcUrl();
		String tableName = tableBo.getTableName();
		String tableComment = tableBo.getTableComment();

		CloudGenerateProperties.DataSource dataSource = getDataSource(jdbcUrl);
		if (dataSource == null) {
			throw new CloudRuntimeException("未找到数据源");
		}

		Page<TableVo> page = new Page<>(current, size);
		List<TableVo> records = new ArrayList<>();
		page.setRecords(records);

		try (HikariDataSource hikariDataSource = new HikariDataSource()) {
			hikariDataSource.setDriverClassName(dataSource.getDriverClassName());
			hikariDataSource.setJdbcUrl(dataSource.getJdbcUrl());
			hikariDataSource.setUsername(dataSource.getUsername());
			hikariDataSource.setPassword(dataSource.getPassword());

			try (Connection connection = hikariDataSource.getConnection()) {

				// 当前连接的数据库名称
				// SELECT DATABASE();
				String database = connection.getCatalog();

				// 当前连接的数据库中的表个数
				PreparedStatement totalPreparedStatement = connection.prepareStatement(
						"SELECT COUNT(1) FROM information_schema.tables WHERE table_schema = ? AND table_name LIKE ? AND table_comment LIKE ?");
				totalPreparedStatement.setString(1, database);
				totalPreparedStatement.setString(2, "%" + (tableName == null ? "" : tableName) + "%");
				totalPreparedStatement.setString(3, "%" + (tableComment == null ? "" : tableComment) + "%");
				ResultSet totalResultSet = totalPreparedStatement.executeQuery();
				totalResultSet.next();
				int total = totalResultSet.getInt(1);

				page.setTotal(total);

				// 搜索表
				PreparedStatement preparedStatement = connection.prepareStatement(
						"SELECT table_name, table_comment FROM information_schema.tables WHERE table_schema = ? AND table_name LIKE ? AND table_comment LIKE ? LIMIT ? OFFSET ?");
				preparedStatement.setString(1, database);
				preparedStatement.setString(2, "%" + (tableName == null ? "" : tableName) + "%");
				preparedStatement.setString(3, "%" + (tableComment == null ? "" : tableComment) + "%");
				preparedStatement.setLong(4, size);
				preparedStatement.setLong(5, (current - 1L) * size);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					TableVo tableVo = new TableVo();
					tableVo.setJdbcUrl(jdbcUrl);
					tableVo.setTableName(resultSet.getString("table_name"));
					tableVo.setTableComment(resultSet.getString("table_comment"));
					records.add(tableVo);
				}

			}
			catch (SQLException e) {
				log.error("列出所有的表信息，连接数据库：{} 异常", dataSource.getJdbcUrl(), e);
				throw new RuntimeException(e);
			}

		}

		return page;
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

	/**
	 * 遍历查找数据源
	 * @param jdbcUrl JDBC 连接串
	 * @return 返回 查找到的数据库，可能为空
	 */
	private CloudGenerateProperties.DataSource getDataSource(@NonNull String jdbcUrl) {
		List<CloudGenerateProperties.DataSource> dataSources = cloudGenerateProperties.getDataSources();
		for (CloudGenerateProperties.DataSource ds : dataSources) {
			if (jdbcUrl.equals(ds.getJdbcUrl())) {
				return ds;
			}
		}
		return null;
	}

}
