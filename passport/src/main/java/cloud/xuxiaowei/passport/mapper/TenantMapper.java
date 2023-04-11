package cloud.xuxiaowei.passport.mapper;

import cloud.xuxiaowei.passport.bo.TenantPageBo;
import cloud.xuxiaowei.passport.bo.TenantPageLoginBo;
import cloud.xuxiaowei.passport.entity.Tenant;
import cloud.xuxiaowei.passport.vo.TenantLoginVo;
import cloud.xuxiaowei.passport.vo.TenantVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 租户表 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-04
 */
public interface TenantMapper extends BaseMapper<Tenant> {

	/**
	 * 分页查询租户的条数-登录页面
	 * @param tenantPageLoginBo 分页参数-登录页面
	 * @return 返回 分页查询租户的条数-登录页面
	 */
	int countByTenantPageLoginBo(@Param("param") TenantPageLoginBo tenantPageLoginBo);

	/**
	 * 分页查询租户的内容-登录页面
	 * @param tenantPageLoginBo 分页参数-登录页面
	 * @return 返回 分页查询租户的内容-登录页面
	 */
	List<TenantLoginVo> listByTenantPageLoginBo(@Param("param") TenantPageLoginBo tenantPageLoginBo);

	/**
	 * 分页查询租户的条数
	 * @param tenantPageBo 分页查询参数
	 * @return 返回 分页查询租户的条数
	 */
	int countByTenantPageBo(@Param("param") TenantPageBo tenantPageBo);

	/**
	 * 分页查询租户的内容
	 * @param tenantPageBo 分页查询参数
	 * @return 分页查询租户的内容
	 */
	List<TenantVo> listByTenantPageBo(@Param("param") TenantPageBo tenantPageBo);

}
