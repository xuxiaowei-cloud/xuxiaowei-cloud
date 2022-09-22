package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.system.annotation.EncryptAnnotation;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.utils.Encrypt;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.SecurityUtils;
import cloud.xuxiaowei.utils.map.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全设置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/security")
public class SecurityRestController {

	private IUsersService usersService;

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	/**
	 * 安全设置 由于返回的数据包含手机号、邮箱等，推荐使用注解配置加密
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 安全设置
	 */
	@EncryptAnnotation(Encrypt.AesVersion.V1)
	@ControllerAnnotation(description = "安全设置")
	@PreAuthorize("hasAuthority('user_info')")
	@RequestMapping
	public Response<?> index(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		Long usersId = SecurityUtils.getUsersId(authentication);
		Users users = usersService.getById(usersId);
		if (users == null) {
			return Response.error("未找到用户信息");
		}

		// @formatter:off
		return ResponseMap.ok()
				.put("phone", users.getPhone())
				.put("email", users.getEmail());
		// @formatter:on
	}

}
