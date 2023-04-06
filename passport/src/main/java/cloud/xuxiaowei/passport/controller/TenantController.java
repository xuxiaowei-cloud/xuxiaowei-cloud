package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.passport.bo.TenantPageLoginBo;
import cloud.xuxiaowei.passport.service.ITenantService;
import cloud.xuxiaowei.passport.vo.TenantLoginVo;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@GetMapping("/page/login")
	public Response<?> pageLogin(TenantPageLoginBo tenantPageLoginBo) {

		IPage<TenantLoginVo> page = tenantService.pageByTenantPageLoginBo(tenantPageLoginBo);

		return Response.ok(page);
	}

}
