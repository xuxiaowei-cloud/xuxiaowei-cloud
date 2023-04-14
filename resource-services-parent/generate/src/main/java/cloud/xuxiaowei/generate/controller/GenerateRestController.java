package cloud.xuxiaowei.generate.controller;

import cloud.xuxiaowei.generate.bo.TableBo;
import cloud.xuxiaowei.generate.service.GenerateService;
import cloud.xuxiaowei.generate.vo.DataSourceVo;
import cloud.xuxiaowei.generate.vo.TableVo;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 代码生成 {@link RestController}
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
public class GenerateRestController {

	private GenerateService generateService;

	@Autowired
	public void setGenerateService(GenerateService generateService) {
		this.generateService = generateService;
	}

	/**
	 * 列出所有的数据源及状态
	 * <p>
	 * 仅包含已配置的代码生成的数据源
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 所有的数据源及状态
	 */
	@GetMapping("/list-data-source")
	public Response<?> listDataSource(HttpServletRequest request, HttpServletResponse response) {
		List<DataSourceVo> dataSourceVos = generateService.listDataSources();
		return Response.ok(dataSourceVos);
	}

	/**
	 * 列出所有的表信息
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 所有的表信息
	 */
	@PostMapping("/list-tables")
	public Response<?> listTables(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody TableBo tableBo) {
		IPage<TableVo> page = generateService.listTables(tableBo);
		return Response.ok(page);
	}

}
