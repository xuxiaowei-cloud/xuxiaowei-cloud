package cloud.xuxiaowei.authorizationserver.controller;

import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/oauth-token")
public class OauthTokenController {

    /**
     * 根据 用户名 删除 Token
     *
     * @param request  请求
     * @param response 响应
     * @param username 用户名
     * @return 返回 删除结果
     */
    @ControllerAnnotation(description = "根据 用户名 删除 Token")
    @PreAuthorize("hasAuthority('username_token_delete') or #oauth2.hasScope('username_token_delete')")
    @RequestMapping("/removeByUsername/{username}")
    public Response<?> removeByUsername(HttpServletRequest request, HttpServletResponse response, @PathVariable("username") String username) {

        return Response.ok();
    }

    /**
     * 根据 客户ID 删除 Token
     *
     * @param request  请求
     * @param response 响应
     * @param clientId 客户ID
     * @return 返回 删除结果
     */
    @ControllerAnnotation(description = "根据 客户ID 删除 Token")
    @PreAuthorize("hasAuthority('clientId_token_delete') or #oauth2.hasScope('clientId_token_delete')")
    @RequestMapping("/removeByClientId/{clientId}")
    public Response<?> removeByClientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("clientId") String clientId) {

        return Response.ok();
    }

}
