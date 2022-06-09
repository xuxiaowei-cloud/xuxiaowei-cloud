package cloud.xuxiaowei.passport.utils;

import cloud.xuxiaowei.passport.entity.UsersLogin;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.RequestUtils;
import cloud.xuxiaowei.utils.exception.ExceptionUtils;
import cloud.xuxiaowei.utils.exception.login.LoginException;
import org.slf4j.MDC;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.WebAttributes.AUTHENTICATION_EXCEPTION;

/**
 * 处理程序 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class HandlerUtils {

    /**
     * 登录成功、失败 处理程序 要保存的数据
     *
     * @param username  用户名
     * @param success   登录结果
     * @param request   请求
     * @param exception 异常
     * @return 返回 要保存的数据
     */
    public static UsersLogin usersLogin(String username, boolean success, HttpServletRequest request, AuthenticationException exception) {
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String headersMap = RequestUtils.getHeadersJson(request);
        String userAgent = RequestUtils.getUserAgent(request);
        String requestId = MDC.get(Constant.REQUEST_ID);
        HttpSession session = request.getSession(false);

        String sessionId;
        if (session == null) {
            sessionId = null;
        } else {

            sessionId = session.getId();

            // Session 创建时间
            long creationTime = session.getCreationTime();
            // 最后一次访问时间
            long lastAccessedTime = session.getLastAccessedTime();
            // 最大非活动时间
            int maxInactiveInterval = session.getMaxInactiveInterval();

            // 在此可以统计一下登录失败的用户信息（需要将登录信息，如：用户名，放入 异常 中）
            if (exception instanceof LoginException) {
                session.removeAttribute(AUTHENTICATION_EXCEPTION);
            }
        }

        String stackTrace = exception == null ? null : ExceptionUtils.getStackTrace(exception);

        UsersLogin usersLogin = new UsersLogin();
        usersLogin.setUsername(username);
        usersLogin.setSuccess(success);
        usersLogin.setMethod(method);
        usersLogin.setQueryString(queryString);
        usersLogin.setHeadersMap(headersMap);
        usersLogin.setUserAgent(userAgent);
        usersLogin.setRequestId(requestId);
        usersLogin.setSessionId(sessionId);
        usersLogin.setException(stackTrace);

        return usersLogin;
    }

}
