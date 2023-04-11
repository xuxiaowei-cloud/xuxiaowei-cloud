package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.passport.bo.TenantPageBo;
import cloud.xuxiaowei.passport.bo.TenantPageLoginBo;
import cloud.xuxiaowei.passport.bo.TenantSaveBo;
import cloud.xuxiaowei.passport.bo.TenantUpdateBo;
import cloud.xuxiaowei.passport.service.ITenantService;
import cloud.xuxiaowei.passport.vo.TenantLoginVo;
import cloud.xuxiaowei.passport.vo.TenantVo;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/tenant")
public class TenantController {

	private ITenantService tenantService;

	@Autowired
	public void setTenantService(ITenantService tenantService) {
		this.tenantService = tenantService;
	}

	/**
	 * 分页查询租户-登录页面
	 * @param tenantPageLoginBo 分页查询租户参数-登录页面
	 * @return 返回分页结果
	 */
	@PostMapping("/page/login")
	public Response<?> pageLogin(@RequestBody TenantPageLoginBo tenantPageLoginBo) {

		IPage<TenantLoginVo> page = tenantService.pageByTenantPageLoginBo(tenantPageLoginBo);

		return Response.ok(page);
	}

	/**
	 * 根据 租户主键 查询租户
	 * @param request 请求
	 * @param response 响应
	 * @param tenantId 租户主键
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "根据 租户主键 查询租户")
	@PreAuthorize("@ant.hasAuthority('manage_tenant:read')")
	@GetMapping("/getById/{id}")
	public Response<?> getById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long tenantId) {

		TenantVo tenantVo = tenantService.getVoById(tenantId);

		return Response.ok(tenantVo);
	}

	/**
	 * 分页查询租户
	 * @param request 请求
	 * @param response 响应
	 * @param tenantPageBo 租户分页参数
	 * @return 返回 分页查询结果
	 */
	@ControllerAnnotation(description = "分页查询租户")
	@PreAuthorize("@ant.hasAuthority('manage_tenant:read')")
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody TenantPageBo tenantPageBo) {

		IPage<TenantVo> page = tenantService.pageByTenantPageBo(tenantPageBo);

		return Response.ok(page);
	}

	/**
	 * 根据 租户主键 更新租户
	 * @param request 请求
	 * @param response 响应
	 * @param tenantUpdateBo 租户 更新参数
	 * @return 返回 更新结果
	 */
	@ControllerAnnotation(description = "根据 租户主键 更新租户")
	@PreAuthorize("@ant.hasAuthority('manage_tenant:edit')")
	@PostMapping("/updateById")
	public Response<?> updateById(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody TenantUpdateBo tenantUpdateBo) {

		boolean update = tenantService.updateByTenantUpdateBo(tenantUpdateBo);

		return Response.ok(update);
	}

	/**
	 * 保存租户
	 * @param request 请求
	 * @param response 响应
	 * @param tenantSaveBo 租户 保存参数
	 * @return 返回 保存结果
	 */
	@ControllerAnnotation(description = "保存租户")
	@PreAuthorize("@ant.hasAuthority('manage_tenant:add')")
	@PostMapping("/save")
	public Response<?> save(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody TenantSaveBo tenantSaveBo) {

		boolean save = tenantService.saveTenantSaveBo(tenantSaveBo);

		return Response.ok(save);
	}

	/**
	 * 根据 租户主键 删除 授权码
	 * @param request 请求
	 * @param response 响应
	 * @param id 租户主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 租户主键 删除 授权码")
	@PreAuthorize("@ant.hasAuthority('manage_tenant:delete')")
	@PostMapping("/removeById/{id}")
	public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id) {

		boolean removeById = tenantService.removeById(id);

		return Response.ok(removeById);
	}

	/**
	 * 根据 租户主键 批量删除 授权码
	 * @param request 请求
	 * @param response 响应
	 * @param ids 租户主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 租户主键 批量删除 授权码")
	@PreAuthorize("@ant.hasAuthority('manage_tenant:delete')")
	@PostMapping("/removeByIds")
	public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<Long> ids) {

		AssertUtils.sizeNonNull(ids, 1, 50, "非法数据长度");

		boolean removeByIds = tenantService.removeByIds(ids);

		return Response.ok(removeByIds);
	}

}
