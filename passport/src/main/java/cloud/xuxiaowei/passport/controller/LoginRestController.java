package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.core.properties.CloudClientProperties;
import cloud.xuxiaowei.passport.service.LoginService;
import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.map.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.UUID;

import static cloud.xuxiaowei.utils.Constant.UNDEFINED;

/**
 * 登录
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginRestController {

    private LoginService loginService;

    private CloudClientProperties cloudClientProperties;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setCloudClientProperties(CloudClientProperties cloudClientProperties) {
        this.cloudClientProperties = cloudClientProperties;
    }

    /**
     * 登录失败
     *
     * @param request  请求
     * @param response 响应
     * @return 返回 登录失败提示语
     * @see ProviderNotFoundException
     * @see BadCredentialsException
     */
    @RequestMapping("/failure")
    public Response<?> failure(HttpServletRequest request, HttpServletResponse response) {
        return loginService.failure(request);
    }

    /**
     * 登录成功
     *
     * @param request     请求
     * @param response    响应
     * @param session     Session，不存在时自动创建
     * @param redirectUri 授权重定向地址
     * @param homePage    主页
     * @return 返回 登录成功提示语
     */
    @RequestMapping("/success")
    public Response<?> success(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                               String redirectUri, String homePage) {
        String state = UUID.randomUUID().toString();
        session.setAttribute(cloudClientProperties.getStateName(), state);

        ResponseMap ok = ResponseMap.ok("登录成功");

        if (StringUtils.hasText(redirectUri) && !UNDEFINED.equals(redirectUri)) {
            try {
                new URL(redirectUri);
                log.info("使用登录参数中的授权重定向地址：{}", redirectUri);
            } catch (Exception e) {
                log.error("非法授权重定向地址：", e);
                redirectUri = cloudClientProperties.getRedirectUri();
                log.warn("使用默认授权重定向地址：{}", redirectUri);
            }
        } else {
            redirectUri = cloudClientProperties.getRedirectUri();
            log.info("使用默认授权重定向地址：{}", redirectUri);
        }

        if (StringUtils.hasText(homePage) && !UNDEFINED.equals(homePage)) {
            try {
                new URL(homePage);
                log.info("使用登录参数中的登录成功主页地址：{}", homePage);
            } catch (Exception e) {
                log.error("非法登录成功主页地址：", e);
                homePage = cloudClientProperties.getHomePage();
                log.warn("使用默认登录成功主页地址：{}", homePage);
            }
        } else {
            homePage = cloudClientProperties.getHomePage();
            log.info("使用默认登录成功主页地址：{}", homePage);
        }
        // 将登录成功主页放入Session中，用于授权成功后页面跳转
        session.setAttribute(state, homePage);

        String authorizeUri = cloudClientProperties.authorizeUri(state, redirectUri);
        String checkTokenUri = cloudClientProperties.getCheckTokenUri();

        return ok.put("authorizeUri", authorizeUri).put("checkTokenUri", checkTokenUri);
    }

    /**
     * 登录成功主页
     *
     * @param request  请求
     * @param response 响应
     * @param session  Session，不存在时自动创建
     * @return 返回 登录成功主页
     */
    @RequestMapping("/home-page")
    public Response<?> homePage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        // 返回的主页可根据权限，或者用户设置的默认系统等信息返回不同的主页
        String homePage = cloudClientProperties.getHomePage();

        return Response.ok(homePage, CodeEnums.OK.msg);
    }

}
