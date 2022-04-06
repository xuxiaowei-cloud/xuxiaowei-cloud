package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.OauthCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-05
 */
public interface IOauthCodeService extends IService<OauthCode> {

    /**
     * 根据 授权码 查询授权信息
     *
     * @param code 授权码
     * @return 返回 授权信息
     */
    OauthCode getByCode(String code);

}
