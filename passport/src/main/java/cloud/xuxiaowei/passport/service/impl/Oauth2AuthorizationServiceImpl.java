package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.bo.Oauth2AuthorizationPageBo;
import cloud.xuxiaowei.passport.entity.Oauth2Authorization;
import cloud.xuxiaowei.passport.mapper.Oauth2AuthorizationMapper;
import cloud.xuxiaowei.passport.service.IOauth2AuthorizationService;
import cloud.xuxiaowei.passport.vo.Oauth2AuthorizationVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 授权表。
 * 原表结构：oauth2-authorization-server-*.*.*.jar!/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * 原表结构：https://github.com/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-18
 */
@Service
public class Oauth2AuthorizationServiceImpl extends ServiceImpl<Oauth2AuthorizationMapper, Oauth2Authorization>
		implements IOauth2AuthorizationService {

	/**
	 * 分页查询
	 * @param oauth2AuthorizationPageBo 授权表 分页参数
	 * @return 返回 分页结果
	 */
	@Override
	public IPage<Oauth2AuthorizationVo> pageByOauth2AuthorizationPageBo(
			Oauth2AuthorizationPageBo oauth2AuthorizationPageBo) {
		long current = oauth2AuthorizationPageBo.getCurrent();
		long size = oauth2AuthorizationPageBo.getSize();

		int total = baseMapper.countByOauth2AuthorizationPageBo(oauth2AuthorizationPageBo);
		List<Oauth2AuthorizationVo> records = baseMapper.listByOauth2AuthorizationPageBo(oauth2AuthorizationPageBo);

		IPage<Oauth2AuthorizationVo> page = new Page<>(current, size);

		page.setTotal(total);
		page.setRecords(records);

		return page;
	}

	/**
	 * 根据 授权 Token 删除 授权表中的数据
	 * @param accessToken 授权 Token
	 * @return 返回 删除结果
	 */
	@Override
	public boolean removeByAccessTokenValue(String accessToken) {
		QueryWrapper<Oauth2Authorization> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("access_token_value", accessToken);
		return remove(queryWrapper);
	}

	/**
	 * 删除用户的授权（踢用户下线）
	 * @param principalName 用户名
	 * @return 返回 删除结果
	 */
	@Override
	public boolean removeByPrincipalName(String principalName) {
		QueryWrapper<Oauth2Authorization> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("principal_name", principalName);
		return remove(queryWrapper);
	}

}
