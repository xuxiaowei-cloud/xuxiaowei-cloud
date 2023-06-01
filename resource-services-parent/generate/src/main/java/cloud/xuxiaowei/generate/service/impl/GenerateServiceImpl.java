package cloud.xuxiaowei.generate.service.impl;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.bo.GenerateBo;
import cloud.xuxiaowei.generate.bo.TableBo;
import cloud.xuxiaowei.generate.bo.TableColumnBo;
import cloud.xuxiaowei.generate.service.GenerateService;
import cloud.xuxiaowei.generate.utils.ConvertUtils;
import cloud.xuxiaowei.generate.utils.DatabaseConstants;
import cloud.xuxiaowei.generate.vo.ColumnFieldVo;
import cloud.xuxiaowei.generate.vo.DataSourceVo;
import cloud.xuxiaowei.generate.vo.TableColumnVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import cloud.xuxiaowei.utils.DateUtils;
import cloud.xuxiaowei.utils.FileUtils;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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
	 * @param tableColumnBo 表字段
	 * @return 返回
	 */
	@Override
	public List<TableColumnVo> listTableColumns(TableColumnBo tableColumnBo) {

		String jdbcUrl = tableColumnBo.getJdbcUrl();
		List<String> tableNames = tableColumnBo.getTableNames();

		CloudGenerateProperties.DataSource dataSource = getDataSource(jdbcUrl);
		if (dataSource == null) {
			throw new CloudRuntimeException("未找到数据源");
		}

		List<TableColumnVo> tableColumnVos = new ArrayList<>();

		try (HikariDataSource hikariDataSource = new HikariDataSource()) {
			hikariDataSource.setDriverClassName(dataSource.getDriverClassName());
			hikariDataSource.setJdbcUrl(dataSource.getJdbcUrl());
			hikariDataSource.setUsername(dataSource.getUsername());
			hikariDataSource.setPassword(dataSource.getPassword());

			try (Connection connection = hikariDataSource.getConnection()) {

				// 当前连接的数据库名称
				// SELECT DATABASE();
				String database = connection.getCatalog();

				Map<String, String> typeMap = cloudGenerateProperties.getTypeMap();
				List<String> boIgnorePropertyNames = cloudGenerateProperties.getBoIgnorePropertyNames();
				List<String> voIgnorePropertyNames = cloudGenerateProperties.getVoIgnorePropertyNames();
				String boSuffixName = cloudGenerateProperties.getBoSuffixName();
				String voSuffixName = cloudGenerateProperties.getVoSuffixName();

				List<String> notNullTypes = Arrays.asList("Long", "Integer");

				for (String tableName : tableNames) {
					PreparedStatement preparedStatement = connection
						.prepareStatement("SHOW FULL COLUMNS FROM " + tableName);
					ResultSet resultSet = preparedStatement.executeQuery();

					PreparedStatement tablePreparedStatement = connection.prepareStatement(
							"SELECT table_comment FROM information_schema.tables WHERE table_schema = ? AND table_name = ?");
					tablePreparedStatement.setString(1, database);
					tablePreparedStatement.setString(2, tableName);
					ResultSet tableResultSet = tablePreparedStatement.executeQuery();
					tableResultSet.next();
					String tableComment = tableResultSet.getString(1);

					List<ColumnFieldVo> columnFieldVoList = new ArrayList<>();
					TableColumnVo tableColumnVo = new TableColumnVo();
					tableColumnVos.add(tableColumnVo);
					tableColumnVo.setTableName(tableName);
					tableColumnVo.setFields(columnFieldVoList);
					tableColumnVo.setTableComment(tableComment);
					tableColumnVo.setTableComment4j(tableComment.replace("\r\n", "\r\n * "));
					tableColumnVo.setBoIgnorePropertyNames(boIgnorePropertyNames);
					tableColumnVo.setVoIgnorePropertyNames(voIgnorePropertyNames);

					String className = WordUtils.capitalizeFully(tableName, '_').replace("_", "");
					String boBaseTypeName = "Base";
					String boPageTypeName = "Page";
					String boSaveTypeName = "Save";
					String boUpdateTypeName = "Update";

					tableColumnVo.setClassName(className);
					tableColumnVo.setBoBaseClassName(className + boBaseTypeName + boSuffixName);
					tableColumnVo.setBoPageClassName(className + boPageTypeName + boSuffixName);
					tableColumnVo.setBoSaveClassName(className + boSaveTypeName + boSuffixName);
					tableColumnVo.setBoUpdateClassName(className + boUpdateTypeName + boSuffixName);
					tableColumnVo.setVoClassName(className + voSuffixName);

					while (resultSet.next()) {

						String field = resultSet.getString(DatabaseConstants.FIELD);
						String type = resultSet.getString(DatabaseConstants.TYPE);
						String nullColumn = resultSet.getString(DatabaseConstants.NULL);
						String key = resultSet.getString(DatabaseConstants.KEY);
						String defaultColumn = resultSet.getString(DatabaseConstants.DEFAULT);
						String extra = resultSet.getString(DatabaseConstants.EXTRA);
						String comment = resultSet.getString(DatabaseConstants.COMMENT);

						ColumnFieldVo columnFieldVo = new ColumnFieldVo();
						columnFieldVoList.add(columnFieldVo);

						String propertyType = ConvertUtils.fieldTypeToPropertyType(typeMap, type);
						String propertyName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field);
						String capitalName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, field);

						columnFieldVo.setField(field);
						columnFieldVo.setType(type);
						columnFieldVo.setPropertyType(propertyType);
						columnFieldVo.setPropertyName(propertyName);
						columnFieldVo.setCapitalName(capitalName);
						columnFieldVo.setNullColumn(nullColumn);
						columnFieldVo.setKey(key);
						columnFieldVo.setDefaultColumn(defaultColumn);
						columnFieldVo.setExtra(extra);
						columnFieldVo.setComment(comment);

						if (type.startsWith("varchar")) {
							int length = Integer.parseInt(type.replace("varchar(", "").replace(")", ""));
							columnFieldVo.setLength(length);
							tableColumnVo.setLength(true);
						}

						if ("NO".equals(nullColumn)) {
							// 不为空

							if (notNullTypes.contains(propertyType)) {
								columnFieldVo.setValidation("@NotNull");
								columnFieldVo.setMessage(comment + " 不能为空");
								tableColumnVo.setNotNull(true);
							}
							else if ("String".equals(propertyType)) {
								columnFieldVo.setValidation("@NotEmpty");
								columnFieldVo.setMessage(comment + " 不能为空");
								tableColumnVo.setNotEmpty(true);
							}

						}

						local(boIgnorePropertyNames, tableColumnVo, propertyType, propertyName);
						local(voIgnorePropertyNames, tableColumnVo, propertyType, propertyName);

					}
				}
			}
			catch (Exception e) {
				log.error("列出表：{} 所有的字段信息，连接数据库：{} 异常", tableNames, dataSource.getJdbcUrl(), e);
				throw new RuntimeException(e);
			}
		}

		return tableColumnVos;
	}

	/**
	 * 文件夹
	 * @param cloudGenerateProperties 代码生成配置
	 * @param fileName 文件名
	 * @return 返回文件夹
	 */
	@Override
	public String filePath(CloudGenerateProperties cloudGenerateProperties, String fileName) {
		LocalDateTime now = LocalDateTime.now();
		return cloudGenerateProperties.getFolderPath() + File.separator + DateUtils.format(now, "yyyy") + File.separator
				+ DateUtils.format(now, "MM") + File.separator + fileName;
	}

	private void local(List<String> boIgnorePropertyNames, TableColumnVo tableColumnVo, String propertyType,
			String propertyName) {
		if (!boIgnorePropertyNames.contains(propertyName)) {
			switch (propertyType) {
				case "LocalTime":
					tableColumnVo.setLocalTime(true);
					tableColumnVo.setDatePattern(true);
					tableColumnVo.setJsonFormat(true);
					break;
				case "LocalDate":
					tableColumnVo.setLocalDate(true);
					tableColumnVo.setDatePattern(true);
					tableColumnVo.setJsonFormat(true);
					break;
				case "LocalDateTime":
					tableColumnVo.setLocalDateTime(true);
					tableColumnVo.setDatePattern(true);
					tableColumnVo.setJsonFormat(true);
					break;
				default:
			}
		}
	}

	/**
	 * 生成代码
	 * @param generateBo 生成
	 * @param zipOutputStream 压缩包输出流
	 * @param filePath 文件夹
	 * @throws IOException 向压缩包添加文件异常
	 */
	@Override
	public void generate(GenerateBo generateBo, ZipOutputStream zipOutputStream, String filePath) throws IOException {

		List<String> tableNames = generateBo.getTableNames();
		String jdbcUrl = generateBo.getJdbcUrl();
		String module = generateBo.getModule();

		// 创建用于设置模板生成器的参数（第三优先级）
		CloudGenerateProperties properties = new CloudGenerateProperties();
		// 将系统默认配置（支持从配置文件中获取），复制到 变量properties 中，用于后面使用（第二优先级）
		BeanUtils.copyProperties(cloudGenerateProperties, properties);
		// 将用户传递的参数，复制到 变量properties 中，用于后面使用（第一优先级）
		BeanUtils.copyProperties(generateBo, properties);

		TableColumnBo tableColumnBo = new TableColumnBo();
		tableColumnBo.setTableNames(tableNames);
		tableColumnBo.setJdbcUrl(jdbcUrl);

		// 表结构
		List<TableColumnVo> tableColumnVos = listTableColumns(tableColumnBo);

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(getClass(), properties.getTemplatePath());

		String modulePath = "/src/main/java/" + properties.getBasePackage().replace(".", "/") + "/" + module;

		String classPath = filePath + modulePath;

		// BO 生成
		generateBo(classPath, modulePath, module, properties, configuration, tableColumnVos, zipOutputStream);

		// VO 生成
		generateVo(classPath, modulePath, module, properties, configuration, tableColumnVos, zipOutputStream);

	}

	private void generateVo(String classPath, String modulePath, String module, CloudGenerateProperties properties,
			Configuration configuration, List<TableColumnVo> tableColumnVos, ZipOutputStream zipOutputStream)
			throws IOException {

		String voPackageName = properties.getVoPackageName();

		String voFolderPath = classPath + File.separator + voPackageName + File.separator;
		File voFolderFile = new File(voFolderPath);
		if (!voFolderFile.exists()) {
			boolean mkdirs = voFolderFile.mkdirs();
		}

		String voTemplateName = "vo.java.ftl";

		for (TableColumnVo tableColumnVo : tableColumnVos) {

			// @formatter:off
			generateVo(tableColumnVo, properties, tableColumnVo.getVoClassName(), voTemplateName, voFolderPath, module, voPackageName, configuration, modulePath, zipOutputStream);
			// @formatter:on
		}

	}

	private void generateVo(TableColumnVo tableColumnVo, CloudGenerateProperties properties, String voClassName,
			String voTemplateName, String voFolderPath, String module, String voPackageName,
			Configuration configuration, String modulePath, ZipOutputStream zipOutputStream) throws IOException {
		tableColumnVo.setPackageName(properties.getBasePackage() + "." + module + "." + voPackageName);
		tableColumnVo.setAuthor(properties.getAuthor());
		tableColumnVo.setSince(properties.getSince());
		tableColumnVo.setClassName(voClassName);
		tableColumnVo.setLombokModel(properties.isLombokModel());

		Template template;
		try {
			template = configuration.getTemplate(voTemplateName);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		String voClassFileName = voClassName + ".java";
		File file = new File(voFolderPath + voClassFileName);
		String zipEntryName = modulePath + File.separator + voPackageName + File.separator + voClassFileName;
		try (FileOutputStream fileOutputStream = new FileOutputStream(file);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
				Writer out = new BufferedWriter(outputStreamWriter);) {
			try {
				template.process(tableColumnVo, out);
			}
			catch (TemplateException | IOException e) {
				throw new RuntimeException(e);
			}

			String c = FileUtils.reader(file);
			FileUtils.write(zipOutputStream, zipEntryName, c);
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private void generateBo(String classPath, String modulePath, String module, CloudGenerateProperties properties,
			Configuration configuration, List<TableColumnVo> tableColumnVos, ZipOutputStream zipOutputStream)
			throws IOException {

		String boPackageName = properties.getBoPackageName();

		String boFolderPath = classPath + File.separator + boPackageName + File.separator;
		File boFolderFile = new File(boFolderPath);
		if (!boFolderFile.exists()) {
			boolean mkdirs = boFolderFile.mkdirs();
		}

		String boBaseTemplateName = "bo-base.java.ftl";
		String boPageTemplateName = "bo-page.java.ftl";
		String boSaveTemplateName = "bo-save.java.ftl";
		String boUpdateTemplateName = "bo-update.java.ftl";

		for (TableColumnVo tableColumnVo : tableColumnVos) {

			// @formatter:off
			generateBoCommon(tableColumnVo, properties, tableColumnVo.getBoBaseClassName(), boBaseTemplateName, boFolderPath, module, boPackageName, configuration, modulePath, zipOutputStream);
			generateBoCommon(tableColumnVo, properties, tableColumnVo.getBoPageClassName(), boPageTemplateName, boFolderPath, module, boPackageName, configuration, modulePath, zipOutputStream);
			generateBoCommon(tableColumnVo, properties, tableColumnVo.getBoSaveClassName(), boSaveTemplateName, boFolderPath, module, boPackageName, configuration, modulePath, zipOutputStream);
			generateBoCommon(tableColumnVo, properties, tableColumnVo.getBoUpdateClassName(), boUpdateTemplateName, boFolderPath, module, boPackageName, configuration, modulePath, zipOutputStream);
			// @formatter:on
		}
	}

	private void generateBoCommon(TableColumnVo tableColumnVo, CloudGenerateProperties properties, String boClassName,
			String boTemplateName, String boFolderPath, String module, String boPackageName,
			Configuration configuration, String modulePath, ZipOutputStream zipOutputStream) throws IOException {
		tableColumnVo.setPackageName(properties.getBasePackage() + "." + module + "." + boPackageName);
		tableColumnVo.setAuthor(properties.getAuthor());
		tableColumnVo.setSince(properties.getSince());
		tableColumnVo.setClassName(boClassName);
		tableColumnVo.setLombokModel(properties.isLombokModel());

		Template template;
		try {
			template = configuration.getTemplate(boTemplateName);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		String boClassFileName = boClassName + ".java";
		File file = new File(boFolderPath + boClassFileName);
		String zipEntryName = modulePath + File.separator + boPackageName + File.separator + boClassFileName;
		try (FileOutputStream fileOutputStream = new FileOutputStream(file);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
				Writer out = new BufferedWriter(outputStreamWriter);) {
			try {
				template.process(tableColumnVo, out);
			}
			catch (TemplateException | IOException e) {
				throw new RuntimeException(e);
			}

			String c = FileUtils.reader(file);
			FileUtils.write(zipOutputStream, zipEntryName, c);
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
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
