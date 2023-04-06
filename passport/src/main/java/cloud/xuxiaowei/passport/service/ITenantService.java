package cloud.xuxiaowei.passport.service;

import cloud.xuxiaowei.passport.bo.TenantPageLoginBo;
import cloud.xuxiaowei.passport.entity.Tenant;
import cloud.xuxiaowei.passport.vo.TenantLoginVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 租户表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-04
 */
public interface ITenantService extends IService<Tenant> {

	/**
	 * 分页查询租户-登录页面
	 * @param tenantPageLoginBo 分页查询租户参数-登录页面
	 * @return 返回分页结果
	 */
	IPage<TenantLoginVo> pageByTenantPageLoginBo(TenantPageLoginBo tenantPageLoginBo);

}
