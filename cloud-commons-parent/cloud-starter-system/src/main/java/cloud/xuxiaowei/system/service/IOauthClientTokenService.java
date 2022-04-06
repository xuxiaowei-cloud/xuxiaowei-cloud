package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.OauthClientToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-06
 */
public interface IOauthClientTokenService extends IService<OauthClientToken> {

}
