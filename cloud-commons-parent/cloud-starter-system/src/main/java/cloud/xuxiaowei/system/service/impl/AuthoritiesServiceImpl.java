package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.bo.AuthoritiesSaveBo;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.mapper.AuthoritiesMapper;
import cloud.xuxiaowei.system.service.IAuthoritiesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限表。
 * 原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl
 * 原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements IAuthoritiesService {

	private IAuthoritiesService authoritiesService;

	@Lazy
	@Autowired
	public void setAuthoritiesService(IAuthoritiesService authoritiesService) {
		this.authoritiesService = authoritiesService;
	}

	/**
	 * 根据 用户ID 查询权限
	 * @param usersId 用户ID
	 * @return 返回 权限
	 */
	@Override
	public List<Authorities> listByUsersId(Long usersId) {
		QueryWrapper<Authorities> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("users_id", usersId);
		return list(queryWrapper);
	}

	/**
	 * 根据 用户ID 查询权限
	 * @param usersId 用户ID
	 * @return 返回 权限
	 */
	@Override
	public Set<String> listAuthorityByUsersId(Long usersId) {
		QueryWrapper<Authorities> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("authority");
		queryWrapper.eq("users_id", usersId);
		return new HashSet<>(listObjs(queryWrapper, object -> {
			if (object == null) {
				return null;
			}
			else {
				return String.valueOf(object);
			}
		}));
	}

	/**
	 * 根据 用户名、权限 保存
	 * @param authoritiesSaveBo 权限表保存参数
	 * @return 返回 保存结果
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveByAuthoritiesSaveBo(AuthoritiesSaveBo authoritiesSaveBo) {

		Long usersId = authoritiesSaveBo.getUsersId();
		Set<String> authorityDatabaseList = listAuthorityByUsersId(usersId);

		Set<String> authorityList = authoritiesSaveBo.getAuthorityList();

		Set<String> addSet = new HashSet<>(authorityList);
		Set<String> removeSet = new HashSet<>(authorityDatabaseList);

		addSet.removeAll(authorityDatabaseList);
		removeSet.removeAll(authorityList);

		if (addSet.size() > 0) {
			List<Authorities> authoritiesList = new ArrayList<>();
			for (String add : addSet) {
				Authorities authorities = new Authorities();
				authorities.setAuthority(add);
				authorities.setUsersId(usersId);
				authoritiesList.add(authorities);
			}
			boolean saveBatch = authoritiesService.saveBatch(authoritiesList);
		}

		if (removeSet.size() > 0) {
			boolean remove = authoritiesService.removeByUsersIdAndAuthoritiesList(usersId, removeSet);
		}

		return true;
	}

	/**
	 * 根据 用户ID、权限 删除
	 * @param usersId 用户ID
	 * @param authorityList 权限
	 * @return 返回 删除结果
	 */
	@Override
	public boolean removeByUsersIdAndAuthoritiesList(Long usersId, Set<String> authorityList) {
		QueryWrapper<Authorities> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("users_id", usersId);
		queryWrapper.in("authority", authorityList);
		return remove(queryWrapper);
	}

}
