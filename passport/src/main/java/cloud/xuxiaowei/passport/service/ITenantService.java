package cloud.xuxiaowei.passport.service;

import cloud.xuxiaowei.passport.bo.TenantPageBo;
import cloud.xuxiaowei.passport.bo.TenantPageLoginBo;
import cloud.xuxiaowei.passport.bo.TenantSaveBo;
import cloud.xuxiaowei.passport.bo.TenantUpdateBo;
import cloud.xuxiaowei.passport.entity.Tenant;
import cloud.xuxiaowei.passport.vo.TenantLoginVo;
import cloud.xuxiaowei.passport.vo.TenantVo;
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
	 * @return 返回分页结果-登录页面
	 */
	IPage<TenantLoginVo> pageByTenantPageLoginBo(TenantPageLoginBo tenantPageLoginBo);

	/**
	 * 分页查询租户
	 * @param tenantPageBo 分页查询租户参数
	 * @return 返回分页结果
	 */
	IPage<TenantVo> pageByTenantPageBo(TenantPageBo tenantPageBo);

	/**
	 * 根据 租户主键 查询租户
	 * @param tenantId 租户主键
	 * @return 返回租户信息
	 */
	TenantVo getVoById(Long tenantId);

	/**
	 * 根据 租户主键 更新租户
	 * @param tenantUpdateBo 租户更新参数
	 * @return 返回更新结果
	 */
	boolean updateByTenantUpdateBo(TenantUpdateBo tenantUpdateBo);

	/**
	 * 保存租户
	 * @param tenantSaveBo 租户参数
	 * @return 返回 保存结果
	 */
	boolean saveTenantSaveBo(TenantSaveBo tenantSaveBo);

}
