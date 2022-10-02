package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.user.bo.SocialBo;
import cloud.xuxiaowei.user.service.SocialService;
import cloud.xuxiaowei.user.vo.SocialVo;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 社交
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/social")
public class SocialRestController {

	private SocialService socialService;

	@Autowired
	public void setSocialService(SocialService socialService) {
		this.socialService = socialService;
	}

	/**
	 * 获取社交绑定
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "获取社交绑定")
	@PreAuthorize("hasAuthority('user_info')")
	@RequestMapping
	public Response<?> list(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Long usersId = SecurityUtils.getUsersId(authentication);
		List<SocialVo> socialVoList = socialService.listByUsersId(usersId);
		return Response.ok(socialVoList);
	}

	/**
	 * 社交解绑
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "社交解绑")
	@PreAuthorize("hasAuthority('user_info')")
	@RequestMapping("/unbinding")
	public Response<?> unbinding(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication, @Valid @RequestBody SocialBo socialBo) {
		Long usersId = SecurityUtils.getUsersId(authentication);
		boolean unbinding = socialService.unbinding(usersId, socialBo.getSocialCode());
		return Response.ok(unbinding);
	}

}
