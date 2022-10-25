package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.passport.bo.PasswordResetBo;
import cloud.xuxiaowei.passport.service.IOauth2AuthorizationService;
import cloud.xuxiaowei.passport.service.IResetPasswordService;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static cloud.xuxiaowei.passport.controller.IndexController.RSA_PRIVATE_KEY_BASE64;

/**
 * 密码
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/password")
public class PasswordRestController {

	private IUsersService usersService;

	private IResetPasswordService resetPasswordService;

	private IOauth2AuthorizationService oauth2AuthorizationService;

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	@Autowired
	public void setResetPasswordService(IResetPasswordService resetPasswordService) {
		this.resetPasswordService = resetPasswordService;
	}

	@Autowired
	public void setOauth2AuthorizationService(IOauth2AuthorizationService oauth2AuthorizationService) {
		this.oauth2AuthorizationService = oauth2AuthorizationService;
	}

	/**
	 * 修改密码（用户修改自己的密码）
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "修改密码（用户修改自己的密码）")
	@PostMapping("/reset")
	public Response<?> reset(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			@Valid @RequestBody PasswordResetBo passwordResetBo) {

		Long usersId = SecurityUtils.getUsersId(authentication);
		Users users = usersService.getById(usersId);

		String password = passwordResetBo.getPassword();

		HttpSession session = request.getSession();

		String rsaPrivateKeyBase64 = (String) session.getAttribute(RSA_PRIVATE_KEY_BASE64);

		// 重置密码
		usersService.updatePasswordById(usersId, password, rsaPrivateKeyBase64);

		// 保存修改日志
		String beforePassword = users.getPassword();
		// 4：用户重置密码（用户修改自己的密码）
		resetPasswordService.saveLog(request, "4", usersId, beforePassword);

		// 删除用户的授权（踢用户下线）
		oauth2AuthorizationService.removeByPrincipalName(users.getUsername());

		return Response.ok();
	}

}
