package cloud.xuxiaowei.audit.controller;

import cloud.xuxiaowei.audit.resilience4j.AuthorizationServerResilience4jService;
import cloud.xuxiaowei.oauth2.bo.AuditCodePageBo;
import cloud.xuxiaowei.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权码Code
 *
 * <code>
 * 用户权限判断：@PreAuthorize("hasAuthority('user')")
 * </code>
 * <code>
 * 客户范围判断：@PreAuthorize("#oauth2.hasScope('snsapi_base')")
 * </code>
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/oauth-code")
public class OauthCodeController {

    private AuthorizationServerResilience4jService authorizationServerResilience4jService;

    @Autowired
    public void setAuthorizationServerResilience4jService(AuthorizationServerResilience4jService authorizationServerResilience4jService) {
        this.authorizationServerResilience4jService = authorizationServerResilience4jService;
    }

    /**
     * 分页查询授权码
     *
     * @param request         请求
     * @param response        响应
     * @param auditCodePageBo 审计授权码分页参数
     * @return 返回 分页查询结果
     */
    @PreAuthorize("hasAuthority('audit_code_read')")
    @RequestMapping("/page")
    public Response<?> page(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody AuditCodePageBo auditCodePageBo) {

        return authorizationServerResilience4jService.pageByAuditCode(auditCodePageBo);
    }

}
