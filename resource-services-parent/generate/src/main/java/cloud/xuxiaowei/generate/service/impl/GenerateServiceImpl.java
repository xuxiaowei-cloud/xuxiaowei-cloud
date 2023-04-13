package cloud.xuxiaowei.generate.service.impl;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.service.GenerateService;
import cloud.xuxiaowei.generate.vo.TableColumnVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 代码生成 服务接口 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class GenerateServiceImpl implements GenerateService {

	/**
	 * 测试数据源是否能正常连接
	 * @param dataSource 数据源配置
	 * @return 返回 连接结果
	 */
	@Override
	public boolean test(CloudGenerateProperties.DataSource dataSource) {
		return false;
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
