package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.bo.TenantPageLoginBo;
import cloud.xuxiaowei.passport.entity.Tenant;
import cloud.xuxiaowei.passport.mapper.TenantMapper;
import cloud.xuxiaowei.passport.service.ITenantService;
import cloud.xuxiaowei.passport.vo.TenantLoginVo;
import cloud.xuxiaowei.utils.MdcUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
	 * @return 返回分页结果
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

}
