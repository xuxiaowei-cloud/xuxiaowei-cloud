package cloud.xuxiaowei.passport.handler;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.passport.entity.UsersLogin;
import cloud.xuxiaowei.passport.service.IUsersLoginService;
import cloud.xuxiaowei.passport.utils.HandlerUtils;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证成功处理程序
 *
 * @author xuxiaowei
 * @see ForwardAuthenticationSuccessHandler
 * @since 0.0.1
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private CloudSecurityProperties cloudSecurityProperties;

    private IUsersLoginService usersLoginService;

    @Autowired
    public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
        this.cloudSecurityProperties = cloudSecurityProperties;
    }

    @Autowired
    public void setUsersLoginService(IUsersLoginService usersLoginService) {
        this.usersLoginService = usersLoginService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String userName = SecurityUtils.getUserName(authentication);

        MDC.put(Constant.NAME, userName);

        UsersLogin usersLogin = HandlerUtils.usersLogin(userName, true, request, null);
        usersLoginService.save(usersLogin);

        String successForwardUrl = cloudSecurityProperties.getSuccessForwardUrl();
        Assert.isTrue(UrlUtils.isValidRedirectUrl(successForwardUrl), () -> "'" + successForwardUrl + "' 不是有效的转发URL");

        request.getRequestDispatcher(successForwardUrl).forward(request, response);
    }

}
