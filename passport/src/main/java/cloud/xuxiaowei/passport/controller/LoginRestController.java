package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.core.properties.CloudClientProperties;
import cloud.xuxiaowei.passport.service.LoginService;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.map.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 登录
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
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
     * @param request  请求
     * @param response 响应
     * @param session  Session，不存在时自动创建
     * @return 返回 登录成功提示语
     */
    @RequestMapping("/success")
    public Response<?> success(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String state = UUID.randomUUID().toString();
        session.setAttribute(cloudClientProperties.getStateName(), state);

        ResponseMap ok = ResponseMap.ok("登录成功");

        String authorizeUri = cloudClientProperties.authorizeUri(state);
        String checkTokenUri = cloudClientProperties.getCheckTokenUri();

        return ok.put("authorizeUri", authorizeUri).put("checkTokenUri", checkTokenUri);
    }

}
