package cloud.xuxiaowei.oauth2.service;

import cloud.xuxiaowei.oauth2.bo.AuditAccessTokenPageBo;
import cloud.xuxiaowei.oauth2.entity.OauthAccessToken;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
public interface IOauthAccessTokenService extends IService<OauthAccessToken> {

    /**
     * 分页查询授权Token
     *
     * @param auditAccessTokenPageBo 审计授权Token分页参数
     * @return 返回 分页查询结果
     */
    IPage<OauthAccessToken> pageByAuditAccessToken(AuditAccessTokenPageBo auditAccessTokenPageBo);

    /**
     * 根据 用户名 删除 Token
     *
     * @param usernames 用户名
     * @return 返回 删除结果
     */
    boolean removeByUsernames(List<String> usernames);

    /**
     * 根据 客户ID 删除 Token
     *
     * @param clientIds 客户ID
     * @return 返回 删除结果
     */
    boolean removeByClientIds(List<String> clientIds);

}
