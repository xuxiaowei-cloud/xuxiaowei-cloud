package cloud.xuxiaowei.masterdata.controller;

import cloud.xuxiaowei.masterdata.bo.TownHandlePageBo;
import cloud.xuxiaowei.masterdata.entity.TownHandle;
import cloud.xuxiaowei.masterdata.service.ITownHandleService;
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
 * 镇 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/town-handle")
public class TownHandleController {

	private ITownHandleService townHandleService;

	@Autowired
	public void setTownHandleService(ITownHandleService townHandleService) {
		this.townHandleService = townHandleService;
	}

	/**
	 * 分页查询镇
	 * @param request 请求
	 * @param response 响应
	 * @param townHandlePageBo 分页参数
	 * @return 返回 查询结果
	 */
	@PreAuthorize("hasAuthority('region_read')")
	@RequestMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody TownHandlePageBo townHandlePageBo) {
		IPage<TownHandle> page = townHandleService.pageByTownHandlePageBo(townHandlePageBo);
		return Response.ok(page);
	}

}
