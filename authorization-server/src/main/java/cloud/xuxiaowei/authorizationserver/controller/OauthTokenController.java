package cloud.xuxiaowei.authorizationserver.controller;

import cloud.xuxiaowei.oauth2.service.IOauthTokenService;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Token
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/oauth-token")
public class OauthTokenController {

    private IOauthTokenService oauthTokenService;

    @Autowired
    public void setOauthTokenService(IOauthTokenService oauthTokenService) {
        this.oauthTokenService = oauthTokenService;
    }

    /**
     * 根据 用户名 删除 Token
     *
     * @param request   请求
     * @param response  响应
     * @param usernames 用户名
     * @return 返回 删除结果
     */
    @ControllerAnnotation(description = "根据 用户名 删除 Token")
    @PreAuthorize("hasAuthority('username_token_delete') or #oauth2.hasScope('username_token_delete')")
    @RequestMapping("/removeByUsernames")
    public Response<?> removeByUsernames(HttpServletRequest request, HttpServletResponse response, @RequestBody List<String> usernames) {

        AssertUtils.sizeNonNull(usernames, 1, 50, "非法数据长度");

        boolean result = oauthTokenService.removeByUsernames(usernames);

        return Response.ok(result);
    }

    /**
     * 根据 客户ID 删除 Token
     *
     * @param request   请求
     * @param response  响应
     * @param clientIds 客户ID
     * @return 返回 删除结果
     */
    @ControllerAnnotation(description = "根据 客户ID 删除 Token")
    @PreAuthorize("hasAuthority('clientId_token_delete') or #oauth2.hasScope('clientId_token_delete')")
    @RequestMapping("/removeByClientIds")
    public Response<?> removeByClientId(HttpServletRequest request, HttpServletResponse response, @RequestBody List<String> clientIds) {

        AssertUtils.sizeNonNull(clientIds, 1, 50, "非法数据长度");

        boolean result = oauthTokenService.removeByClientIds(clientIds);

        return Response.ok(result);
    }

}
