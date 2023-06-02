package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.passport.bo.Oauth2RegisteredClientPageBo;
import cloud.xuxiaowei.passport.bo.Oauth2RegisteredClientSaveBo;
import cloud.xuxiaowei.passport.bo.Oauth2RegisteredClientUpdateBo;
import cloud.xuxiaowei.passport.service.IOauth2RegisteredClientService;
import cloud.xuxiaowei.passport.vo.Oauth2RegisteredClientVo;
import cloud.xuxiaowei.passport.vo.OptionVo;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.authentication.*;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 客户表。
 * 原表结构：oauth2-authorization-server-*.*.*.jar!/org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql
 * 原表结构：https://github.com/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql
 * 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-18
 */
@RestController
@RequestMapping("/oauth2-registered-client")
@Tag(name = "Oauth2RegisteredClientRestController", description = "客户表")
public class Oauth2RegisteredClientRestController {

	public static final String ALGORITHM_SPLIT = "#";

	private IOauth2RegisteredClientService oauth2RegisteredClientService;

	@Autowired
	public void setOauth2RegisteredClientService(IOauth2RegisteredClientService oauth2RegisteredClientService) {
		this.oauth2RegisteredClientService = oauth2RegisteredClientService;
	}

	/**
	 * 授权类型选项
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 授权类型选项
	 */
	@GetMapping("/grant-type-options")
	@ControllerAnnotation(description = "授权类型选项")
	public Response<?> grantTypeOptions(HttpServletRequest request, HttpServletResponse response) {
		List<OptionVo> list = new ArrayList<>();
		// @formatter:off
		list.add(new OptionVo(AuthorizationGrantType.AUTHORIZATION_CODE.getValue(), AuthorizationGrantType.AUTHORIZATION_CODE.getValue()));
		list.add(new OptionVo(AuthorizationGrantType.REFRESH_TOKEN.getValue(), AuthorizationGrantType.REFRESH_TOKEN.getValue()));
		list.add(new OptionVo(AuthorizationGrantType.CLIENT_CREDENTIALS.getValue(), AuthorizationGrantType.CLIENT_CREDENTIALS.getValue()));
		list.add(new OptionVo(AuthorizationGrantType.PASSWORD.getValue(), AuthorizationGrantType.PASSWORD.getValue()));

		list.add(new OptionVo(OAuth2AlipayMiniProgramAuthenticationToken.ALIPAY_MINIPROGRAM.getValue(), OAuth2AlipayMiniProgramAuthenticationToken.ALIPAY_MINIPROGRAM.getValue()));
		list.add(new OptionVo(OAuth2AlipayOplatformWebsiteAuthenticationToken.ALIPAY_OPLATFORM_WEBSITE.getValue(), OAuth2AlipayOplatformWebsiteAuthenticationToken.ALIPAY_OPLATFORM_WEBSITE.getValue()));
		list.add(new OptionVo(OAuth2DingtalkAuthenticationToken.DINGTALK.getValue(), OAuth2DingtalkAuthenticationToken.DINGTALK.getValue()));
		list.add(new OptionVo(OAuth2GiteeAuthenticationToken.GITEE.getValue(), OAuth2GiteeAuthenticationToken.GITEE.getValue()));
		list.add(new OptionVo(OAuth2GitHubAuthenticationToken.GITHUB.getValue(), OAuth2GitHubAuthenticationToken.GITHUB.getValue()));
		list.add(new OptionVo(OAuth2GitLabAuthenticationToken.GITLAB.getValue(), OAuth2GitLabAuthenticationToken.GITLAB.getValue()));
		list.add(new OptionVo(OAuth2QQMiniProgramAuthenticationToken.QQ_MINIPROGRAM.getValue(), OAuth2QQMiniProgramAuthenticationToken.QQ_MINIPROGRAM.getValue()));
		list.add(new OptionVo(OAuth2QQWebsiteAuthenticationToken.QQ_WEBSITE.getValue(), OAuth2QQWebsiteAuthenticationToken.QQ_WEBSITE.getValue()));
		list.add(new OptionVo(OAuth2WeChatMiniProgramAuthenticationToken.WECHAT_MINIPROGRAM.getValue(), OAuth2WeChatMiniProgramAuthenticationToken.WECHAT_MINIPROGRAM.getValue()));
		list.add(new OptionVo(OAuth2WeChatOffiaccountAuthenticationToken.WECHAT_OFFIACCOUNT.getValue(), OAuth2WeChatOffiaccountAuthenticationToken.WECHAT_OFFIACCOUNT.getValue()));
		list.add(new OptionVo(OAuth2WeChatOplatformWebsiteAuthenticationToken.WECHAT_OPLATFORM_WEBSITE.getValue(), OAuth2WeChatOplatformWebsiteAuthenticationToken.WECHAT_OPLATFORM_WEBSITE.getValue()));
		list.add(new OptionVo(OAuth2WeChatWorkWebsiteAuthenticationToken.WECHAT_WORK_WEBSITE.getValue(), OAuth2WeChatWorkWebsiteAuthenticationToken.WECHAT_WORK_WEBSITE.getValue()));
		list.add(new OptionVo(OAuth2WeiBoWebsiteAuthenticationToken.WEIBO_WEBSITE.getValue(), OAuth2WeiBoWebsiteAuthenticationToken.WEIBO_WEBSITE.getValue()));
		// @formatter:on
		return Response.ok(list);
	}

	/**
	 * 客户端身份验证方法选项
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 客户端身份验证方法选项
	 */
	@GetMapping("/authentication-method-options")
	@ControllerAnnotation(description = "客户端身份验证方法选项")
	public Response<?> authenticationMethodOptions(HttpServletRequest request, HttpServletResponse response) {
		List<OptionVo> list = new ArrayList<>();
		// @formatter:off
		list.add(new OptionVo(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue(), ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue()));
		list.add(new OptionVo(ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue(), ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue()));
		list.add(new OptionVo(ClientAuthenticationMethod.CLIENT_SECRET_JWT.getValue(), ClientAuthenticationMethod.CLIENT_SECRET_JWT.getValue()));
		list.add(new OptionVo(ClientAuthenticationMethod.PRIVATE_KEY_JWT.getValue(), ClientAuthenticationMethod.PRIVATE_KEY_JWT.getValue()));
		list.add(new OptionVo(ClientAuthenticationMethod.NONE.getValue(), ClientAuthenticationMethod.NONE.getValue()));
		// @formatter:on
		return Response.ok(list);
	}

	/**
	 * 授权Token格式选项
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 授权Token格式选项
	 */
	@GetMapping("/access-token-format-options")
	@ControllerAnnotation(description = "授权Token格式选项")
	public Response<?> accessTokenFormatOptions(HttpServletRequest request, HttpServletResponse response) {
		List<OptionVo> list = new ArrayList<>();
		String selfContained = OAuth2TokenFormat.SELF_CONTAINED.getValue();
		String reference = OAuth2TokenFormat.REFERENCE.getValue();
		list.add(new OptionVo(selfContained, selfContained));
		list.add(new OptionVo(reference, reference));
		return Response.ok(list);
	}

	/**
	 * 授权范围选项
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 授权范围选项
	 */
	@GetMapping("/scope-options")
	@ControllerAnnotation(description = "授权范围选项")
	public Response<?> scopeOptions(HttpServletRequest request, HttpServletResponse response) {
		List<OptionVo> list = new ArrayList<>();
		list.add(new OptionVo("snsapi_base", "snsapi_base"));
		list.add(new OptionVo("snsapi_info", "snsapi_info"));
		return Response.ok(list);
	}

	/**
	 * 令牌端点认证签名算法选项
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 令牌端点认证签名算法选项
	 */
	@GetMapping("/token-signing-algorithm-options")
	@ControllerAnnotation(description = "令牌端点认证签名算法选项")
	public Response<?> tokenSigningAlgorithmOptions(HttpServletRequest request, HttpServletResponse response) {
		List<OptionVo> list = new ArrayList<>();

		MacAlgorithm[] macAlgorithms = MacAlgorithm.values();
		for (MacAlgorithm value : macAlgorithms) {
			String algorithm = value.getClass().getName() + ALGORITHM_SPLIT + value.getName();
			list.add(new OptionVo(algorithm, algorithm));
		}

		SignatureAlgorithm[] signatureAlgorithms = SignatureAlgorithm.values();
		for (SignatureAlgorithm value : signatureAlgorithms) {
			String algorithm = value.getClass().getName() + ALGORITHM_SPLIT + value.getName();
			list.add(new OptionVo(algorithm, algorithm));
		}

		return Response.ok(list);
	}

	/**
	 * id 令牌签名算法选项
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 id 令牌签名算法选项
	 */
	@GetMapping("/token-signature-algorithm-options")
	@ControllerAnnotation(description = "id 令牌签名算法选项")
	public Response<?> tokenSignatureAlgorithmOptions(HttpServletRequest request, HttpServletResponse response) {
		List<OptionVo> list = new ArrayList<>();

		SignatureAlgorithm[] signatureAlgorithms = SignatureAlgorithm.values();
		for (SignatureAlgorithm value : signatureAlgorithms) {
			String algorithm = value.getClass().getName() + ALGORITHM_SPLIT + value.getName();
			list.add(new OptionVo(algorithm, algorithm));
		}

		return Response.ok(list);
	}

	/**
	 * 分页查询客户
	 * @param request 请求
	 * @param response 响应
	 * @param oauth2RegisteredClientPageBo 客户分页参数
	 * @return 返回 分页查询结果
	 */
	@ControllerAnnotation(description = "分页查询客户")
	@PreAuthorize("@ant.hasAuthority('manage_client:read')")
	@PostMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody Oauth2RegisteredClientPageBo oauth2RegisteredClientPageBo) {

		IPage<Oauth2RegisteredClientVo> page = oauth2RegisteredClientService
			.pageByOauth2RegisteredClientPageBo(oauth2RegisteredClientPageBo);

		return Response.ok(page);
	}

	/**
	 * 根据 客户主键 删除 授权码
	 * @param request 请求
	 * @param response 响应
	 * @param id 客户主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 客户主键 删除 授权码")
	@PreAuthorize("@ant.hasAuthority('manage_client:delete')")
	@PostMapping("/removeById/{id}")
	public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		boolean removeById = oauth2RegisteredClientService.removeById(id);

		return Response.ok(removeById);
	}

	/**
	 * 根据 客户主键 批量删除
	 * @param request 请求
	 * @param response 响应
	 * @param ids 客户主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 客户主键 批量删除")
	@PreAuthorize("@ant.hasAuthority('manage_client:delete')")
	@PostMapping("/removeByIds")
	public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<String> ids) {

		AssertUtils.sizeNonNull(ids, 1, 50, "非法数据长度");

		boolean removeByIds = oauth2RegisteredClientService.removeByIds(ids);

		return Response.ok(removeByIds);
	}

	/**
	 * 根据 客户主键 查询客户
	 * @param request 请求
	 * @param response 响应
	 * @param id 客户主键
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "根据 客户主键 查询客户")
	@PreAuthorize("@ant.hasAuthority('manage_client:read')")
	@GetMapping("/getById/{id}")
	public Response<?> getById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		Oauth2RegisteredClientVo oauth2RegisteredClientVo = oauth2RegisteredClientService.getVoById(id);

		return Response.ok(oauth2RegisteredClientVo);
	}

	/**
	 * 保存客户
	 * @param request 请求
	 * @param response 响应
	 * @param oauth2RegisteredClientSaveBo 客户 保存参数
	 * @return 返回 保存结果
	 */
	@ControllerAnnotation(description = "保存客户")
	@PreAuthorize("@ant.hasAuthority('manage_client:add')")
	@PostMapping("/save")
	public Response<?> save(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody Oauth2RegisteredClientSaveBo oauth2RegisteredClientSaveBo) {

		boolean save = oauth2RegisteredClientService.saveOauth2RegisteredClientSaveBo(oauth2RegisteredClientSaveBo);

		return Response.ok(save);
	}

	/**
	 * 根据 客户主键 更新客户
	 * @param request 请求
	 * @param response 响应
	 * @param oauth2RegisteredClientUpdateBo 客户 更新参数
	 * @return 返回 更新结果
	 */
	@ControllerAnnotation(description = "根据 客户主键 更新客户")
	@PreAuthorize("@ant.hasAuthority('manage_client:edit')")
	@PostMapping("/updateById")
	public Response<?> updateById(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody Oauth2RegisteredClientUpdateBo oauth2RegisteredClientUpdateBo) {

		boolean update = oauth2RegisteredClientService
			.updateByOauth2RegisteredClientUpdateBo(oauth2RegisteredClientUpdateBo);

		return Response.ok(update);
	}

}
