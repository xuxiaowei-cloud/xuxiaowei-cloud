package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.bo.Oauth2AuthorizationConsentPageBo;
import cloud.xuxiaowei.passport.bo.Oauth2AuthorizationConsentPrimaryKey;
import cloud.xuxiaowei.passport.entity.Oauth2AuthorizationConsent;
import cloud.xuxiaowei.passport.mapper.Oauth2AuthorizationConsentMapper;
import cloud.xuxiaowei.passport.service.IOauth2AuthorizationConsentService;
import cloud.xuxiaowei.passport.vo.Oauth2AuthorizationConsentVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 授权同意书表。
 * 原表结构：oauth2-authorization-server-*.*.*.jar!/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * 原表结构：https://github.com/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-18
 */
@Service
public class Oauth2AuthorizationConsentServiceImpl
		extends ServiceImpl<Oauth2AuthorizationConsentMapper, Oauth2AuthorizationConsent>
		implements IOauth2AuthorizationConsentService {

	/**
	 * 分页查询 授权同意书表
	 * @param oauth2AuthorizationConsentPageBo 授权同意书表 分页参数
	 * @return 返回 分页结果
	 */
	@Override
	public IPage<Oauth2AuthorizationConsentVo> pageByOauth2AuthorizationConsentPageBo(
			Oauth2AuthorizationConsentPageBo oauth2AuthorizationConsentPageBo) {
		long size = oauth2AuthorizationConsentPageBo.getSize();
		long current = oauth2AuthorizationConsentPageBo.getCurrent();

		int total = baseMapper.countByOauth2AuthorizationConsentPageBo(oauth2AuthorizationConsentPageBo);
		List<Oauth2AuthorizationConsentVo> records = baseMapper
			.listByOauth2AuthorizationConsentPageBo(oauth2AuthorizationConsentPageBo);

		IPage<Oauth2AuthorizationConsentVo> page = new Page<>(current, size);
		page.setTotal(total);
		page.setRecords(records);

		return page;
	}

	/**
	 * 根据联合主键删除授权同意书表
	 * @param oauth2AuthorizationConsentPrimaryKey 联合主键
	 * @return 返回 删除结果
	 */
	@Override
	public boolean removeByPrimaryKey(Oauth2AuthorizationConsentPrimaryKey oauth2AuthorizationConsentPrimaryKey) {
		QueryWrapper<Oauth2AuthorizationConsent> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("registered_client_id", oauth2AuthorizationConsentPrimaryKey.getRegisteredClientId());
		queryWrapper.eq("principal_name", oauth2AuthorizationConsentPrimaryKey.getPrincipalName());
		return remove(queryWrapper);
	}

	/**
	 * 根据联合主键批量删除授权同意书表
	 * @param oauth2AuthorizationConsentPrimaryKeys 联合主键
	 * @return 返回 删除结果
	 */
	@Override
	public boolean removeByPrimaryKeys(
			List<Oauth2AuthorizationConsentPrimaryKey> oauth2AuthorizationConsentPrimaryKeys) {
		QueryWrapper<Oauth2AuthorizationConsent> queryWrapper = new QueryWrapper<>();
		for (Oauth2AuthorizationConsentPrimaryKey oauth2AuthorizationConsentPrimaryKey : oauth2AuthorizationConsentPrimaryKeys) {
			queryWrapper.or(qw -> {
				qw.eq("registered_client_id", oauth2AuthorizationConsentPrimaryKey.getRegisteredClientId());
				qw.eq("principal_name", oauth2AuthorizationConsentPrimaryKey.getPrincipalName());
			});
		}
		return remove(queryWrapper);
	}

}
