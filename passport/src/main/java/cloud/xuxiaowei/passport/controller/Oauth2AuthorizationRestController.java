package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.passport.bo.Oauth2AuthorizationPageBo;
import cloud.xuxiaowei.passport.service.IOauth2AuthorizationService;
import cloud.xuxiaowei.passport.vo.Oauth2AuthorizationVo;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 授权表。
 * 原表结构：oauth2-authorization-server-*.*.*.jar!/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * 原表结构：https://github.com/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 * 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-18
 */
@RestController
@RequestMapping("/oauth2-authorization")
@Tag(name = "Oauth2AuthorizationRestController", description = "授权表")
public class Oauth2AuthorizationRestController {

	private IOauth2AuthorizationService oauth2AuthorizationService;

	@Autowired
	public void setOauth2AuthorizationService(IOauth2AuthorizationService oauth2AuthorizationService) {
		this.oauth2AuthorizationService = oauth2AuthorizationService;
	}

	/**
	 * 分页查询授权
	 * @param request 请求
	 * @param response 响应
	 * @param oauth2AuthorizationPageBo 授权分页参数
	 * @return 返回 分页查询结果
	 */
	@ControllerAnnotation(description = "分页查询授权")
	@PreAuthorize("@ant.hasAuthority('audit_authorization:read')")
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody Oauth2AuthorizationPageBo oauth2AuthorizationPageBo) {

		IPage<Oauth2AuthorizationVo> page = oauth2AuthorizationService
			.pageByOauth2AuthorizationPageBo(oauth2AuthorizationPageBo);

		return Response.ok(page);
	}

	/**
	 * 根据 主键 删除 授权表
	 * @param request 请求
	 * @param response 响应
	 * @param id 主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 主键 删除 授权表")
	@PreAuthorize("@ant.hasAuthority('audit_authorization:delete')")
	@PostMapping("/removeById/{id}")
	public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		boolean removeById = oauth2AuthorizationService.removeById(id);

		return Response.ok(removeById);
	}

	/**
	 * 根据 主键 批量删除 授权表
	 * @param request 请求
	 * @param response 响应
	 * @param ids 主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 主键 批量删除 授权表")
	@PreAuthorize("@ant.hasAuthority('audit_authorization:delete')")
	@PostMapping("/removeByIds")
	public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<String> ids) {

		AssertUtils.sizeNonNull(ids, 1, 50, "非法数据长度");

		boolean removeByIds = oauth2AuthorizationService.removeByIds(ids);

		return Response.ok(removeByIds);
	}

	/**
	 * 根据 客户ID 批量删除 授权表
	 * @param request 请求
	 * @param response 响应
	 * @param registeredClientIds 客户ID
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 主键 批量删除 授权表")
	@PreAuthorize("@ant.hasAnyAuthority('audit_authorization:delete', 'manage_client:token_delete')")
	@PostMapping("/removeByRegisteredClientIds")
	public Response<?> removeByRegisteredClientIds(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<String> registeredClientIds) {

		AssertUtils.sizeNonNull(registeredClientIds, 1, 50, "非法数据长度");

		boolean removeByRegisteredClientIds = oauth2AuthorizationService
			.removeByRegisteredClientIds(registeredClientIds);

		return Response.ok(removeByRegisteredClientIds);
	}

}
