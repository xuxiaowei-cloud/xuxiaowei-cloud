package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.bo.TenantPageLoginBo;
import cloud.xuxiaowei.passport.entity.Tenant;
import cloud.xuxiaowei.passport.mapper.TenantMapper;
import cloud.xuxiaowei.passport.service.ITenantService;
import cloud.xuxiaowei.passport.vo.TenantLoginVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	 * @return 返回分页结果
	 */
	@Override
	public IPage<TenantLoginVo> pageByTenantPageLoginBo(TenantPageLoginBo tenantPageLoginBo) {
		QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("tenant_id", "tenant_name");
		Long current = tenantPageLoginBo.getCurrent();
		Long size = tenantPageLoginBo.getSize();
		IPage<Tenant> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);
		page(page, queryWrapper);

		IPage<TenantLoginVo> pageVo = new Page<>();
		BeanUtils.copyProperties(page, pageVo);

		List<TenantLoginVo> records = new ArrayList<>();
		for (Tenant tenant : page.getRecords()) {
			TenantLoginVo tenantLoginVo = new TenantLoginVo();
			BeanUtils.copyProperties(tenant, tenantLoginVo);
			records.add(tenantLoginVo);
		}
		pageVo.setRecords(records);

		return pageVo;
	}

}
