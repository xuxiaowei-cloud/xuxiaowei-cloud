package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.bo.CountyHandlePageBo;
import cloud.xuxiaowei.masterdata.entity.CountyHandle;
import cloud.xuxiaowei.masterdata.service.ICountyHandleService;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>
 * 县 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/county-handle")
public class CountyHandleController {

	private ICountyHandleService countyHandleService;

	@Autowired
	public void setCountyHandleService(ICountyHandleService countyHandleService) {
		this.countyHandleService = countyHandleService;
	}

	/**
	 * 分页查询县
	 * @param request 请求
	 * @param response 响应
	 * @param countyHandlePageBo 分页参数
	 * @return 返回 查询结果
	 */
	@PreAuthorize("hasAuthority('region_read')")
	@RequestMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody CountyHandlePageBo countyHandlePageBo) {
		IPage<CountyHandle> page = countyHandleService.pageByCountyHandlePageBo(countyHandlePageBo);
		return Response.ok(page);
	}

}
