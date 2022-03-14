package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.configuration.DefaultBeanConfiguration;
import cloud.xuxiaowei.passport.service.LoginService;
import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.exception.LoginException;
import cloud.xuxiaowei.utils.exception.LoginParamPasswordValidException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.WebAttributes.AUTHENTICATION_EXCEPTION;

/**
 * 登录 服务 默认实现类
 *
 * @author xuxiaowei
 * @see DefaultBeanConfiguration#loginService()
 * @since 0.0.1
 */
public class DefaultLoginServiceImpl implements LoginService {

    /**
     * 登录失败处理
     *
     * @param request 请求
     * @return 返回 登录失败处理结果
     */
    @Override
    public Response<?> failure(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        // Session 不存在，说明非法访问
        if (session == null) {
            return new Response<>(CodeEnums.A1000.code, CodeEnums.A1000.msg);
        }

        return session(request);
    }

    /**
     * 登录失败 Session 处理
     *
     * @param request 请求
     * @return 返回 登录失败 Session 处理结果
     */
    public Response<?> session(HttpServletRequest request) {

        Response<?> error;

        HttpSession session = request.getSession();

        // Spring Security 最后一次异常
        // 跨域时，需要 Session 共享才能获取到
        Object exception = session.getAttribute(AUTHENTICATION_EXCEPTION);

        if (exception instanceof LoginParamPasswordValidException) {
            LoginParamPasswordValidException passwordValidException = (LoginParamPasswordValidException) exception;
            error = Response.error(passwordValidException.code, passwordValidException.msg);
        } else {
            // 此处可增加其他异常的判断
            error = Response.error();
        }

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

        return error;
    }

}
