package cloud.xuxiaowei.audit.controller;

import cloud.xuxiaowei.audit.resilience4j.AuthorizationServerResilience4jService;
import cloud.xuxiaowei.oauth2.bo.AuditAccessTokenPageBo;
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
 * 授权Token
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/oauth-access-token")
public class OauthAccessTokenController {

    private AuthorizationServerResilience4jService authorizationServerResilience4jService;

    @Autowired
    public void setAuthorizationServerResilience4jService(AuthorizationServerResilience4jService authorizationServerResilience4jService) {
        this.authorizationServerResilience4jService = authorizationServerResilience4jService;
    }

    /**
     * 根据 授权Token主键 删除
     *
     * @param request            请求
     * @param response           响应
     * @param oauthAccessTokenId 授权Token主键
     * @return 返回 删除结果
     */
    @PreAuthorize("hasAuthority('audit_accessToken_delete')")
    @RequestMapping("/removeById/{oauthAccessTokenId}")
    public Response<?> removeById(HttpServletRequest request, HttpServletResponse response, @PathVariable("oauthAccessTokenId") Long oauthAccessTokenId) {

        return authorizationServerResilience4jService.removeByAuditAccessTokenId(oauthAccessTokenId);
    }

    /**
     * 根据 授权Token主键 删除
     *
     * @param request             请求
     * @param response            响应
     * @param oauthAccessTokenIds 授权Token主键
     * @return 返回 删除结果
     */
    @PreAuthorize("hasAuthority('audit_accessToken_delete')")
    @RequestMapping("/removeById")
    public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Long> oauthAccessTokenIds) {

        AssertUtils.sizeNonNull(oauthAccessTokenIds, 1, 50, "非法数据长度");

        return authorizationServerResilience4jService.removeByAuditAccessTokenIds(oauthAccessTokenIds);
    }

    /**
     * 分页查询授权Token
     *
     * @param request                请求
     * @param response               响应
     * @param auditAccessTokenPageBo 审计授权Token分页参数
     * @return 返回 分页查询结果
     */
    @PreAuthorize("hasAuthority('audit_accessToken_read')")
    @RequestMapping("/page")
    public Response<?> page(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody AuditAccessTokenPageBo auditAccessTokenPageBo) {

        return authorizationServerResilience4jService.pageByAuditAccessToken(auditAccessTokenPageBo);
    }

}
