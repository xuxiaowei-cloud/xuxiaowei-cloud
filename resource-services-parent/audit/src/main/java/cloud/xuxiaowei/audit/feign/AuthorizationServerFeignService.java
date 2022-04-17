package cloud.xuxiaowei.audit.feign;

import cloud.xuxiaowei.oauth2.bo.AuditAccessTokenPageBo;
import cloud.xuxiaowei.oauth2.bo.AuditCodePageBo;
import cloud.xuxiaowei.oauth2.bo.AuditRefreshTokenPageBo;
import cloud.xuxiaowei.openfeign.interceptor.AuthorizationRequestInterceptor;
import cloud.xuxiaowei.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 授权服务 feign 接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@FeignClient(value = "authorization-server", contextId = "authorization-server", configuration = {AuthorizationRequestInterceptor.class})
public interface AuthorizationServerFeignService {

    /**
     * 根据 授权码Code主键 删除
     *
     * @param codeId 授权码Code主键
     * @return 返回 删除结果
     */
    @PostMapping("/oauth-code/removeById/{codeId}")
    Response<?> removeByAuditCodeId(@PathVariable("codeId") Long codeId);

    /**
     * 根据 授权Token主键 删除
     *
     * @param oauthAccessTokenId 授权Token主键
     * @return 返回 删除结果
     */
    @PostMapping("/oauth-access-token/removeById/{oauthAccessTokenId}")
    Response<?> removeByAuditAccessTokenId(@PathVariable("oauthAccessTokenId") Long oauthAccessTokenId);

    /**
     * 根据 刷新Token主键 删除
     *
     * @param oauthRefreshTokenId 刷新Token主键
     * @return 返回 删除结果
     */
    @PostMapping("/oauth-refresh-token/removeById/{oauthRefreshTokenId}")
    Response<?> removeByAuditRefreshTokenId(@PathVariable("oauthRefreshTokenId") Long oauthRefreshTokenId);

    /**
     * 分页查询授权码
     *
     * @param auditCodePageBo 审计授权码分页参数
     * @return 返回 分页查询授权码 结果
     */
    @PostMapping("/oauth-code/page")
    Response<?> pageByAuditCode(AuditCodePageBo auditCodePageBo);

    /**
     * 分页查询授权Token
     *
     * @param auditAccessTokenPageBo 审计授权Token分页参数
     * @return 返回 分页查询结果
     */
    @PostMapping("/oauth-access-token/page")
    Response<?> pageByAuditAccessToken(AuditAccessTokenPageBo auditAccessTokenPageBo);

    /**
     * 分页查询刷新Token
     *
     * @param auditRefreshTokenPageBo 审计刷新Token分页参数
     * @return 返回 分页查询结果
     */
    @PostMapping("/oauth-refresh-token/page")
    Response<?> pageByAuditRefreshToken(AuditRefreshTokenPageBo auditRefreshTokenPageBo);


}
