package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.service.IDictService;
import cloud.xuxiaowei.masterdata.vo.DictVo;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/dict")
public class DictController {

	private IDictService dictService;

	@Autowired
	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	/**
	 * 查询字典代码列表
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "查询字典代码列表")
	@PreAuthorize("hasAnyAuthority('region_read', 'user_info')")
	@RequestMapping("/list")
	public Response<?> listByDictCode(HttpServletRequest request, HttpServletResponse response) {
		List<DictVo> dictVoList = dictService.listDictVo();
		return Response.ok(dictVoList);
	}

}
