package cloud.xuxiaowei.oauth2.point;

import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证入口点
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("身份验证入口点", authException);

        Response<?> error = Response.error(CodeEnums.T00000.code, CodeEnums.T00000.msg);

        if (authException instanceof InsufficientAuthenticationException) {

            Throwable cause = authException.getCause();

            if (cause instanceof InvalidTokenException) {
                error.setCode(CodeEnums.T10001.code);
                error.setMsg(CodeEnums.T10001.msg);
            }

        }

        error.setExplain(authException.getMessage());

        ResponseUtils.response(response, error);
    }

}
