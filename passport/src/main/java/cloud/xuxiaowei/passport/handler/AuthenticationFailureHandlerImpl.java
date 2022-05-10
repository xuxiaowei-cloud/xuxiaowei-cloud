package cloud.xuxiaowei.passport.handler;

import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.ResponseUtils;
import cloud.xuxiaowei.utils.exception.login.LoginException;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.springframework.security.web.WebAttributes.AUTHENTICATION_EXCEPTION;

/**
 * 身份验证失败处理程序
 *
 * @author xuxiaowei
 * @see FormLoginConfigurer#failureUrl(String)
 * @see WebAttributes#AUTHENTICATION_EXCEPTION
 * @see WebAttributes#ACCESS_DENIED_403
 * @see WebAttributes#WEB_INVOCATION_PRIVILEGE_EVALUATOR_ATTRIBUTE
 * @since 0.0.1
 */
@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        Response<?> error;

        // Session 不存在，说明非法访问
        if (session == null) {
            error = Response.error(CodeEnums.A10000.code, CodeEnums.A10000.msg);
        } else {

            if (exception instanceof DisabledException) {
                error = Response.error(CodeEnums.A30001.code, CodeEnums.A30001.msg);
            } else if (exception instanceof AccountExpiredException) {
                error = Response.error(CodeEnums.A30002.code, CodeEnums.A30002.msg);
            } else if (exception instanceof CredentialsExpiredException) {
                error = Response.error(CodeEnums.A30003.code, CodeEnums.A30003.msg);
            } else if (exception instanceof LockedException) {
                error = Response.error(CodeEnums.A30004.code, CodeEnums.A30004.msg);
            } else if (exception instanceof LoginParamPasswordValidException) {
                LoginParamPasswordValidException passwordValidException = (LoginParamPasswordValidException) exception;
                error = Response.error(passwordValidException.getCode(), passwordValidException.getMsg());
            } else {
                // 此处可增加其他异常的判断
                error = Response.error();
            }

            log.error(error.getMsg(), exception);

            // 在此可以统计一下登录失败的用户信息（需要将登录信息，如：用户名，放入 异常 中）
            if (exception instanceof LoginException) {
                session.removeAttribute(AUTHENTICATION_EXCEPTION);
            }

            // Session 创建时间
            long creationTime = session.getCreationTime();
            // 最后一次访问时间
            long lastAccessedTime = session.getLastAccessedTime();
            // 最大非活动时间
            int maxInactiveInterval = session.getMaxInactiveInterval();

        }

        ResponseUtils.response(response, error);
    }

}
