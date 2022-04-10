package cloud.xuxiaowei.authorizationserver.controller;

import cloud.xuxiaowei.oauth2.bo.AuditCodePageBo;
import cloud.xuxiaowei.oauth2.entity.OauthCode;
import cloud.xuxiaowei.oauth2.service.IOauthCodeService;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    private IOauthCodeService oauthCodeService;

    @Autowired
    public void setOauthCodeService(IOauthCodeService oauthCodeService) {
        this.oauthCodeService = oauthCodeService;
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
    public Response<?> page(HttpServletRequest request, HttpServletResponse response, @RequestBody AuditCodePageBo auditCodePageBo) {

        IPage<OauthCode> page = oauthCodeService.pageByAuditCode(auditCodePageBo);

        return Response.ok(page);
    }

}
