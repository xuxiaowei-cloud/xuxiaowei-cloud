package cloud.xuxiaowei.generate.service;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.vo.DataSourceVo;
import cloud.xuxiaowei.generate.vo.TableColumnVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 代码生成 服务接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface GenerateService {

	/**
	 * 列出所有的数据源信息
	 * @return 返回 数据源信息
	 */
	List<DataSourceVo> listDataSources();

	/**
	 * 测试数据源是否能正常连接
	 * @param dataSource 数据源配置
	 * @throws RuntimeException 连接失败时将抛出运行时异常
	 */
	void test(@NonNull CloudGenerateProperties.DataSource dataSource) throws RuntimeException;

	/**
	 * 列出所有的表信息
	 * @param dataSource 数据源配置
	 * @param current 当前页，默认值：1
	 * @param size 每页条数，默认值：10
	 * @param tableName 表名，模糊搜索
	 * @param tableComment 表注释，模糊搜索
	 * @return 返回 表信息
	 */
	IPage<TableVo> listTables(CloudGenerateProperties.DataSource dataSource, Long current, Long size, String tableName,
			String tableComment);

	/**
	 * 列出所有的字段信息
	 * @param dataSource 数据源配置
	 * @param tableNames 表名
	 * @return 返回
	 */
	List<TableColumnVo> listTableColumns(CloudGenerateProperties.DataSource dataSource, List<String> tableNames);

}
