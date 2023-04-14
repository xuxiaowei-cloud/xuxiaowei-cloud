package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.bo.TenantPageBo;
import cloud.xuxiaowei.passport.bo.TenantPageLoginBo;
import cloud.xuxiaowei.passport.bo.TenantSaveBo;
import cloud.xuxiaowei.passport.bo.TenantUpdateBo;
import cloud.xuxiaowei.passport.entity.Tenant;
import cloud.xuxiaowei.passport.mapper.TenantMapper;
import cloud.xuxiaowei.passport.service.ITenantService;
import cloud.xuxiaowei.passport.vo.TenantLoginVo;
import cloud.xuxiaowei.passport.vo.TenantVo;
import cloud.xuxiaowei.utils.MdcUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-04
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

	/**
	 * 分页查询租户-登录页面
	 * @param tenantPageLoginBo 分页查询租户参数-登录页面
	 * @return 返回分页结果-登录页面
	 */
	@Override
	public IPage<TenantLoginVo> pageByTenantPageLoginBo(TenantPageLoginBo tenantPageLoginBo) {
		long current = tenantPageLoginBo.getCurrent();
		long size = tenantPageLoginBo.getSize();

		MdcUtils.putIgnoreTable("oauth2_registered_client");
		int total = baseMapper.countByTenantPageLoginBo(tenantPageLoginBo);
		List<TenantLoginVo> records = baseMapper.listByTenantPageLoginBo(tenantPageLoginBo);
		MdcUtils.clearIgnoreTables();

		IPage<TenantLoginVo> page = new Page<>(current, size);
		page.setTotal(total);
		page.setRecords(records);

		return page;
	}

	/**
	 * 分页查询租户
	 * @param tenantPageBo 分页查询租户参数
	 * @return 返回分页结果
	 */
	@Override
	public IPage<TenantVo> pageByTenantPageBo(TenantPageBo tenantPageBo) {
		long current = tenantPageBo.getCurrent();
		long size = tenantPageBo.getSize();

		int total = baseMapper.countByTenantPageBo(tenantPageBo);
		List<TenantVo> records = baseMapper.listByTenantPageBo(tenantPageBo);

		IPage<TenantVo> page = new Page<>(current, size);

		page.setTotal(total);
		page.setRecords(records);

		return page;
	}

	/**
	 * 根据 租户主键 查询租户
	 * @param tenantId 租户主键
	 * @return 返回租户信息
	 */
	@Override
	public TenantVo getVoById(Long tenantId) {
		Tenant tenant = getById(tenantId);
		if (tenant == null) {
			return null;
		}
		TenantVo tenantVo = new TenantVo();
		BeanUtils.copyProperties(tenant, tenantVo);
		return tenantVo;
	}

	/**
	 * 根据 租户主键 更新租户
	 * @param tenantUpdateBo 租户更新参数
	 * @return 返回更新结果
	 */
	@Override
	public boolean updateByTenantUpdateBo(TenantUpdateBo tenantUpdateBo) {
		Tenant tenant = new Tenant();
		BeanUtils.copyProperties(tenantUpdateBo, tenant);
		return updateById(tenant);
	}

	/**
	 * 保存租户
	 * @param tenantSaveBo 租户参数
	 * @return 返回 保存结果
	 */
	@Override
	public boolean saveTenantSaveBo(TenantSaveBo tenantSaveBo) {
		Tenant tenant = new Tenant();
		BeanUtils.copyProperties(tenantSaveBo, tenant);
		return save(tenant);
	}

}
