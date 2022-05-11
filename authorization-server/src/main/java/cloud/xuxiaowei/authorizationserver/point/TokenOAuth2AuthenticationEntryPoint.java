package cloud.xuxiaowei.authorizationserver.point;

import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

/**
 * Token OAuth2 身份验证入口点
 * <p>
 * 仅在 password 模式中使用
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class TokenOAuth2AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    protected ResponseEntity<?> enhanceResponse(ResponseEntity<?> response, Exception exception) {
        log.error("Token OAuth2 身份验证入口点", exception);

        Response<?> error = Response.error(CodeEnums.C10000.code, CodeEnums.C10000.msg);

        if (exception instanceof BadCredentialsException) {
            error.setCode(CodeEnums.C20002.code);
            error.setMsg(CodeEnums.C20002.msg);
        }

        error.setExplain(exception.getMessage());

        return new ResponseEntity<Response<?>>(error, HttpStatus.OK);
    }

}
