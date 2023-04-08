package cloud.xuxiaowei.passport.mapper;

import cloud.xuxiaowei.passport.bo.Oauth2AuthorizationPageBo;
import cloud.xuxiaowei.passport.entity.Oauth2Authorization;
import cloud.xuxiaowei.passport.vo.Oauth2AuthorizationVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 授权表。
 * 原表结构：oauth2-authorization-server-*.*.*.jar!/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * 原表结构：https://github.com/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-18
 */
public interface Oauth2AuthorizationMapper extends BaseMapper<Oauth2Authorization> {

	/**
	 * 分页查询授权的条数
	 * @param oauth2AuthorizationPageBo 分页查询参数
	 * @return 返回 分页查询授权内容
	 */
	int countByOauth2AuthorizationPageBo(@Param("param") Oauth2AuthorizationPageBo oauth2AuthorizationPageBo);

	/**
	 * 分页查询授权的条数
	 * @param oauth2AuthorizationPageBo 分页查询参数
	 * @return 返回 分页查询授权的条数
	 */
	List<Oauth2AuthorizationVo> listByOauth2AuthorizationPageBo(
			@Param("param") Oauth2AuthorizationPageBo oauth2AuthorizationPageBo);

}
