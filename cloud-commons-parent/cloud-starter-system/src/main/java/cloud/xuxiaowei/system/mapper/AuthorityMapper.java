package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.Authority;
import cloud.xuxiaowei.system.vo.AuthorityVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 权限与权限说明表 Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-17
 */
public interface AuthorityMapper extends BaseMapper<Authority> {

	/**
	 * 根据 用户ID 查询权限
	 * @param usersId 用户ID
	 * @return 返回 权限
	 */
	Set<AuthorityVo> listByUsersId(@Param("usersId") Long usersId);

}
