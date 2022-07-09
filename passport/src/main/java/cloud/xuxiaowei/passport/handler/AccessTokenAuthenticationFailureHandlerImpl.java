package cloud.xuxiaowei.passport.handler;

import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.ResponseUtils;
import cloud.xuxiaowei.utils.map.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权 Token 身份验证失败处理程序
 * <p>
 * 用于处理失败的身份验证尝试的策略。
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
public class AccessTokenAuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		ResponseMap error = ResponseMap.error(CodeEnums.C10000.code, CodeEnums.C10000.msg);
		error.setExplain(exception.getMessage());

		if (exception instanceof OAuth2AuthenticationException) {
			OAuth2AuthenticationException oauth2AuthenticationException = (OAuth2AuthenticationException) exception;
			OAuth2Error oauth2Error = oauth2AuthenticationException.getError();
			String errorCode = oauth2Error.getErrorCode();
			String description = oauth2Error.getDescription();
			String uri = oauth2Error.getUri();

			error.put("errorCode", errorCode);
			error.put("description", description);
			error.put("uri", uri);
		}

		log.info("授权 Token 身份验证失败", exception);

		ResponseUtils.response(response, error);
	}

}
