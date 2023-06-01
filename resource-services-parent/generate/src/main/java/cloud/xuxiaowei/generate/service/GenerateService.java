package cloud.xuxiaowei.generate.service;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.bo.GenerateBo;
import cloud.xuxiaowei.generate.bo.TableBo;
import cloud.xuxiaowei.generate.bo.TableColumnBo;
import cloud.xuxiaowei.generate.vo.DataSourceVo;
import cloud.xuxiaowei.generate.vo.TableColumnVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

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
	 * @param tableBo 表
	 * @return 返回 表信息
	 */
	IPage<TableVo> listTables(TableBo tableBo);

	/**
	 * 列出所有的字段信息
	 * @param tableColumnBo 表字段
	 * @return 返回
	 */
	List<TableColumnVo> listTableColumns(TableColumnBo tableColumnBo);

	/**
	 * 文件夹
	 * @param cloudGenerateProperties 代码生成配置
	 * @param fileName 文件名
	 * @return 返回文件夹
	 */
	String filePath(CloudGenerateProperties cloudGenerateProperties, String fileName);

	/**
	 * 生成代码
	 * @param generateBo 生成
	 * @param zipOutputStream 压缩包输出流
	 * @param filePath 文件夹
	 * @throws IOException 向压缩包添加文件异常
	 */
	void generate(GenerateBo generateBo, ZipOutputStream zipOutputStream, String filePath) throws IOException;

}
