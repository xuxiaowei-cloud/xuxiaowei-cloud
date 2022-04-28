package cloud.xuxiaowei.audit.controller;

import cloud.xuxiaowei.audit.resilience4j.AuthorizationServerResilience4jService;
import cloud.xuxiaowei.oauth2.bo.AuditRefreshTokenPageBo;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 刷新Token
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/oauth-refresh-token")
public class OauthRefreshTokenController {

    private AuthorizationServerResilience4jService authorizationServerResilience4jService;

    @Autowired
    public void setAuthorizationServerResilience4jService(AuthorizationServerResilience4jService authorizationServerResilience4jService) {
        this.authorizationServerResilience4jService = authorizationServerResilience4jService;
    }

    /**
     * 根据 刷新Token主键 删除
     *
     * @param request             请求
     * @param response            响应
     * @param oauthRefreshTokenId 刷新Token主键
     * @return 返回 删除结果
     */
    @PreAuthorize("hasAuthority('audit_refreshToken_delete')")
    @RequestMapping("/removeById/{oauthRefreshTokenId}")
    public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
                                  @PathVariable("oauthRefreshTokenId") Long oauthRefreshTokenId) {

        return authorizationServerResilience4jService.removeByAuditRefreshTokenId(oauthRefreshTokenId);
    }

    /**
     * 根据 刷新Token主键 删除
     *
     * @param request              请求
     * @param response             响应
     * @param oauthRefreshTokenIds 刷新Token主键
     * @return 返回 删除结果
     */
    @PreAuthorize("hasAuthority('audit_refreshToken_delete')")
    @RequestMapping("/removeByIds")
    public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Long> oauthRefreshTokenIds) {

        AssertUtils.sizeNonNull(oauthRefreshTokenIds, 1, 50, "非法数据长度");

        return authorizationServerResilience4jService.removeByAuditRefreshTokenIds(oauthRefreshTokenIds);
    }

    /**
     * 分页查询刷新Token
     *
     * @param request                 请求
     * @param response                响应
     * @param auditRefreshTokenPageBo 审计刷新Token分页参数
     * @return 返回 分页查询结果
     */
    @PreAuthorize("hasAuthority('audit_refreshToken_read')")
    @RequestMapping("/page")
    public Response<?> page(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody AuditRefreshTokenPageBo auditRefreshTokenPageBo) {

        return authorizationServerResilience4jService.pageByAuditRefreshToken(auditRefreshTokenPageBo);
    }

}
