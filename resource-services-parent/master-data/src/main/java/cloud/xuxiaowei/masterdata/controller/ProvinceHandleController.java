package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.bo.ProvinceHandlePageBo;
import cloud.xuxiaowei.masterdata.entity.ProvinceHandle;
import cloud.xuxiaowei.masterdata.service.IProvinceHandleService;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>
 * 省份 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/province-handle")
public class ProvinceHandleController {

	private IProvinceHandleService provinceHandleService;

	@Autowired
	public void setProvinceHandleService(IProvinceHandleService provinceHandleService) {
		this.provinceHandleService = provinceHandleService;
	}

	/**
	 * 分页查询省份
	 * @param request 请求
	 * @param response 响应
	 * @param provinceHandlePageBo 分页参数
	 * @return 返回 查询结果
	 */
	@PreAuthorize("hasAnyAuthority('region_read', 'user_info')")
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody ProvinceHandlePageBo provinceHandlePageBo) {
		IPage<ProvinceHandle> page = provinceHandleService.pageByProvinceHandlePageBo(provinceHandlePageBo);
		return Response.ok(page);
	}

}
