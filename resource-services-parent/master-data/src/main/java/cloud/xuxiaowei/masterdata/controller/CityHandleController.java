package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.bo.CityHandlePageBo;
import cloud.xuxiaowei.masterdata.entity.CityHandle;
import cloud.xuxiaowei.masterdata.service.ICityHandleService;
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
 * 城市 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/city-handle")
public class CityHandleController {

	private ICityHandleService cityHandleService;

	@Autowired
	public void setCityHandleService(ICityHandleService cityHandleService) {
		this.cityHandleService = cityHandleService;
	}

	/**
	 * 分页查询城市
	 * @param request 请求
	 * @param response 响应
	 * @param cityHandlePageBo 分页参数
	 * @return 返回 查询结果
	 */
	@PreAuthorize("hasAnyAuthority('region_read', 'user_info')")
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody CityHandlePageBo cityHandlePageBo) {
		IPage<CityHandle> page = cityHandleService.pageByCityHandlePageBo(cityHandlePageBo);
		return Response.ok(page);
	}

}
