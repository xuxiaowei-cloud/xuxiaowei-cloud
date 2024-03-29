package cloud.xuxiaowei.passport.handler;

import cloud.xuxiaowei.passport.entity.UsersLogin;
import cloud.xuxiaowei.passport.service.IUsersLoginService;
import cloud.xuxiaowei.passport.utils.HandlerUtils;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.utils.*;
import cloud.xuxiaowei.utils.exception.login.LoginException;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

	private JavaMailSender javaMailSender;

	private MailProperties mailProperties;

	private IUsersService usersService;

	private IUsersLoginService usersLoginService;

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	/**
	 * 注意： 当未成功配置邮箱时，{@link Autowired} 直接注入将会失败，导致程序无法启动
	 * <p>
	 * 故将 {@link Autowired} 的 required 设置为 false，避免程序启动失败。使用时请判断该值是否为 null
	 */
	@Autowired(required = false)
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Autowired
	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

	@Autowired
	public void setUsersLoginService(IUsersLoginService usersLoginService) {
		this.usersLoginService = usersLoginService;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String username = request.getParameter(Constants.USERNAME);
		UsersLogin usersLogin = HandlerUtils.usersLogin(username, false, request, exception);
		usersLoginService.save(usersLogin);

		Response<?> error;

		if (exception instanceof DisabledException) {
			error = Response.error(CodeEnums.A30001.code, CodeEnums.A30001.msg);
		}
		else if (exception instanceof AccountExpiredException) {
			error = Response.error(CodeEnums.A30002.code, CodeEnums.A30002.msg);
		}
		else if (exception instanceof CredentialsExpiredException) {
			error = Response.error(CodeEnums.A30003.code, CodeEnums.A30003.msg);
		}
		else if (exception instanceof LockedException) {
			error = Response.error(CodeEnums.A30004.code, CodeEnums.A30004.msg);
		}
		else if (exception instanceof LoginParamPasswordValidException) {
			LoginParamPasswordValidException passwordValidException = (LoginParamPasswordValidException) exception;
			error = Response.error(passwordValidException.getCode(), passwordValidException.getMsg());
		}
		else if (exception instanceof InternalAuthenticationServiceException) {
			InternalAuthenticationServiceException internalAuthenticationServiceException = (InternalAuthenticationServiceException) exception;
			Throwable cause = internalAuthenticationServiceException.getCause();
			if (cause instanceof LoginException) {
				LoginException loginException = (LoginException) cause;
				error = Response.error(loginException.getCode(), loginException.getMsg());
			}
			else {
				error = Response.error(CodeEnums.A10000.code, "内部认证服务异常");
				error.setExplain("异常代码待划分");
			}
		}
		else {
			// 此处可增加其他异常的判断
			error = Response.error(CodeEnums.A10000.code, CodeEnums.A10000.msg);
		}

		log.error(error.getMsg(), exception);

		ResponseUtils.response(response, error);

		String remoteHost = request.getRemoteHost();
		String userAgent = RequestUtils.getUserAgent(request);

		String subject = "登录系统失败";
		String result = "失败";
		Runnable runnable = () -> HandlerUtils.send(usersService, javaMailSender, mailProperties, username, subject,
				result, remoteHost, userAgent);
		new Thread(runnable).start();
	}

}
