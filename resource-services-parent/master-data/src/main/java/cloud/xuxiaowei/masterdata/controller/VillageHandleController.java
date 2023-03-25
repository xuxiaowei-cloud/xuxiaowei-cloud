package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.bo.VillageHandlePageBo;
import cloud.xuxiaowei.masterdata.entity.VillageHandle;
import cloud.xuxiaowei.masterdata.service.IVillageHandleService;
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
 * 居委会 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/village-handle")
public class VillageHandleController {

	private IVillageHandleService villageHandleService;

	@Autowired
	public void setVillageHandleService(IVillageHandleService villageHandleService) {
		this.villageHandleService = villageHandleService;
	}

	/**
	 * 分页查询居委会
	 * @param request 请求
	 * @param response 响应
	 * @param villageHandlePageBo 分页参数
	 * @return 返回 查询结果
	 */
	@PreAuthorize("@ant.hasAnyAuthority('region:read', 'user:info')")
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody VillageHandlePageBo villageHandlePageBo) {
		IPage<VillageHandle> page = villageHandleService.pageByVillageHandlePageBo(villageHandlePageBo);
		return Response.ok(page);
	}

}
