package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.passport.bo.Oauth2AuthorizationConsentPageBo;
import cloud.xuxiaowei.passport.bo.Oauth2AuthorizationConsentPrimaryKey;
import cloud.xuxiaowei.passport.entity.Oauth2AuthorizationConsent;
import cloud.xuxiaowei.passport.service.IOauth2AuthorizationConsentService;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 授权同意书表。
 * 原表结构：oauth2-authorization-server-*.*.*.jar!/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * 原表结构：https://github.com/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql
 * 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-18
 */
@RestController
@RequestMapping("/oauth2-authorization-consent")
@Tag(name = "Oauth2AuthorizationConsentRestController", description = "授权同意书表")
public class Oauth2AuthorizationConsentRestController {

	private IOauth2AuthorizationConsentService oauth2AuthorizationConsentService;

	@Autowired
	public void setOauth2AuthorizationConsentService(
			IOauth2AuthorizationConsentService oauth2AuthorizationConsentService) {
		this.oauth2AuthorizationConsentService = oauth2AuthorizationConsentService;
	}

	/**
	 * 分页查询授权同意书
	 * @param request 请求
	 * @param response 响应
	 * @param oauth2AuthorizationConsentPageBo 授权同意书分页参数
	 * @return 返回 分页查询结果
	 */
	@ControllerAnnotation(description = "分页查询授权同意书")
	@PreAuthorize("@ant.hasAuthority('audit_authorization_consent:read')")
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody Oauth2AuthorizationConsentPageBo oauth2AuthorizationConsentPageBo) {

		IPage<Oauth2AuthorizationConsent> page = oauth2AuthorizationConsentService
			.pageByOauth2AuthorizationConsentPageBo(oauth2AuthorizationConsentPageBo);

		return Response.ok(page);
	}

	/**
	 * 根据 主键 删除 授权同意书
	 * @param request 请求
	 * @param response 响应
	 * @param oauth2AuthorizationConsentPrimaryKey 授权同意书表 联合主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 主键 删除 授权同意书")
	@PreAuthorize("hasAuthority('audit_authorization_consent_delete')")
	@PostMapping("/removeById")
	public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Oauth2AuthorizationConsentPrimaryKey oauth2AuthorizationConsentPrimaryKey) {

		boolean removeByPrimaryKey = oauth2AuthorizationConsentService
			.removeByPrimaryKey(oauth2AuthorizationConsentPrimaryKey);

		return Response.ok(removeByPrimaryKey);
	}

	/**
	 * 根据 主键 批量删除 授权同意书
	 * @param request 请求
	 * @param response 响应
	 * @param oauth2AuthorizationConsentPrimaryKeys 主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 主键 批量删除 授权同意书")
	@PreAuthorize("hasAuthority('audit_authorization_consent_delete')")
	@PostMapping("/removeByIds")
	public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<Oauth2AuthorizationConsentPrimaryKey> oauth2AuthorizationConsentPrimaryKeys) {

		AssertUtils.sizeNonNull(oauth2AuthorizationConsentPrimaryKeys, 1, 50, "非法数据长度");

		boolean removeByIds = oauth2AuthorizationConsentService
			.removeByPrimaryKeys(oauth2AuthorizationConsentPrimaryKeys);

		return Response.ok(removeByIds);
	}

}
