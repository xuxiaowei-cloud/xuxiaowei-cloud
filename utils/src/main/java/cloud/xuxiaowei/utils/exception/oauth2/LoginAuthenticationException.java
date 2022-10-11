package cloud.xuxiaowei.utils.exception.oauth2;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * 登录授权异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class LoginAuthenticationException extends OAuth2AuthenticationException {

	public LoginAuthenticationException(String errorCode) {
		super(errorCode);
	}

	public LoginAuthenticationException(OAuth2Error error) {
		super(error);
	}

	public LoginAuthenticationException(OAuth2Error error, Throwable cause) {
		super(error, cause);
	}

	public LoginAuthenticationException(OAuth2Error error, String message) {
		super(error, message);
	}

	public LoginAuthenticationException(OAuth2Error error, String message, Throwable cause) {
		super(error, message, cause);
	}

	@Override
	public OAuth2Error getError() {
		return super.getError();
	}

}
