package cloud.xuxiaowei.authorizationserver.controller;

import cloud.xuxiaowei.oauth2.bo.AuditAccessTokenPageBo;
import cloud.xuxiaowei.oauth2.entity.OauthAccessToken;
import cloud.xuxiaowei.oauth2.service.IOauthAccessTokenService;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.AssertUtils;
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
import java.util.List;

/**
 * 授权Token
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/oauth-access-token")
public class OauthAccessTokenController {

    private IOauthAccessTokenService oauthAccessTokenService;

    @Autowired
    public void setOauthAccessTokenService(IOauthAccessTokenService oauthAccessTokenService) {
        this.oauthAccessTokenService = oauthAccessTokenService;
    }

    /**
     * 根据 授权Token主键 删除 授权Token
     *
     * @param request            请求
     * @param response           响应
     * @param oauthAccessTokenId 授权Token主键
     * @return 返回 删除结果
     */
    @ControllerAnnotation(description = "根据 授权Token主键 删除 授权Token")
    @PreAuthorize("hasAuthority('audit_accessToken_delete') or #oauth2.hasScope('audit_accessToken_delete')")
    @RequestMapping("/removeById/{oauthAccessTokenId}")
    public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
                                  @PathVariable("oauthAccessTokenId") Long oauthAccessTokenId) {

        boolean removeById = oauthAccessTokenService.removeById(oauthAccessTokenId);

        return Response.ok(removeById);
    }

    /**
     * 根据 授权Token主键 批量删除 授权Token
     *
     * @param request             请求
     * @param response            响应
     * @param oauthAccessTokenIds 授权Token主键
     * @return 返回 删除结果
     */
    @ControllerAnnotation(description = "根据 授权Token主键 批量删除 授权Token")
    @PreAuthorize("hasAuthority('audit_accessToken_delete') or #oauth2.hasScope('audit_accessToken_delete')")
    @RequestMapping("/removeByIds")
    public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody List<Long> oauthAccessTokenIds) {

        AssertUtils.sizeNonNull(oauthAccessTokenIds, 1, 50, "非法数据长度");

        boolean removeByIds = oauthAccessTokenService.removeByIds(oauthAccessTokenIds);

        return Response.ok(removeByIds);
    }

    /**
     * 分页查询授权Token
     *
     * @param request                请求
     * @param response               响应
     * @param auditAccessTokenPageBo 审计授权Token分页参数
     * @return 返回 分页查询结果
     */
    @ControllerAnnotation(description = "分页查询授权Token")
    @PreAuthorize("hasAuthority('audit_accessToken_read') or #oauth2.hasScope('audit_accessToken_read')")
    @RequestMapping("/page")
    public Response<?> page(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody AuditAccessTokenPageBo auditAccessTokenPageBo) {

        IPage<OauthAccessToken> page = oauthAccessTokenService.pageByAuditAccessToken(auditAccessTokenPageBo);

        return Response.ok(page);
    }

}
