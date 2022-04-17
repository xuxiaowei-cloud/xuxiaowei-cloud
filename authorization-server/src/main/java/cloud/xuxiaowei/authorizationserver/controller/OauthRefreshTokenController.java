package cloud.xuxiaowei.authorizationserver.controller;

import cloud.xuxiaowei.oauth2.bo.AuditRefreshTokenPageBo;
import cloud.xuxiaowei.oauth2.entity.OauthRefreshToken;
import cloud.xuxiaowei.oauth2.service.IOauthRefreshTokenService;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 刷新Token
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/oauth-refresh-token")
public class OauthRefreshTokenController {

    private IOauthRefreshTokenService oauthRefreshTokenService;

    @Autowired
    public void setOauthRefreshTokenService(IOauthRefreshTokenService oauthRefreshTokenService) {
        this.oauthRefreshTokenService = oauthRefreshTokenService;
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

        boolean removeById = oauthRefreshTokenService.removeById(oauthRefreshTokenId);

        return Response.ok(removeById);
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

        IPage<OauthRefreshToken> page = oauthRefreshTokenService.pageByAuditRefreshToken(auditRefreshTokenPageBo);

        return Response.ok(page);
    }

}
