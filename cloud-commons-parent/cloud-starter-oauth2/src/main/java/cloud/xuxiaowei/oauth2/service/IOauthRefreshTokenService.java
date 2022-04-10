package cloud.xuxiaowei.oauth2.service;

import cloud.xuxiaowei.oauth2.bo.AuditRefreshTokenPageBo;
import cloud.xuxiaowei.oauth2.entity.OauthRefreshToken;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
public interface IOauthRefreshTokenService extends IService<OauthRefreshToken> {

    /**
     * 分页查询刷新Token
     *
     * @param auditRefreshTokenPageBo 审计刷新Token分页参数
     * @return 返回 分页查询结果
     */
    IPage<OauthRefreshToken> pageByAuditRefreshToken(AuditRefreshTokenPageBo auditRefreshTokenPageBo);

}
