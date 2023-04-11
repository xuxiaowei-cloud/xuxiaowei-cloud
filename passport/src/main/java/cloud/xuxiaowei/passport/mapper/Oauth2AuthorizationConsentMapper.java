package cloud.xuxiaowei.passport.mapper;

import cloud.xuxiaowei.passport.bo.Oauth2AuthorizationConsentPageBo;
import cloud.xuxiaowei.passport.entity.Oauth2AuthorizationConsent;
import cloud.xuxiaowei.passport.vo.Oauth2AuthorizationConsentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 授权同意书表。
 * 原表结构：oauth2-authorization-server-*.*.*.jar!/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * 原表结构：https://github.com/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-18
 */
public interface Oauth2AuthorizationConsentMapper extends BaseMapper<Oauth2AuthorizationConsent> {

	/**
	 * 分页查询授权同意书的条数
	 * @param oauth2AuthorizationConsentPageBo 分页查询条件
	 * @return 返回 分页查询授权同意书的条数
	 */
	int countByOauth2AuthorizationConsentPageBo(
			@Param("param") Oauth2AuthorizationConsentPageBo oauth2AuthorizationConsentPageBo);

	/**
	 * 分页查询授权同意书的内容
	 * @param oauth2AuthorizationConsentPageBo 分页查询条件
	 * @return 返回 分页查询授权同意书的内容
	 */
	List<Oauth2AuthorizationConsentVo> listByOauth2AuthorizationConsentPageBo(
			@Param("param") Oauth2AuthorizationConsentPageBo oauth2AuthorizationConsentPageBo);

}
