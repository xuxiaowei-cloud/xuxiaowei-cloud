package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.core.properties.CloudClientProperties;
import cloud.xuxiaowei.passport.entity.Oauth2RegisteredClient;
import cloud.xuxiaowei.passport.service.IOauth2RegisteredClientService;
import cloud.xuxiaowei.utils.Constants;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.map.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * 登录
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginRestController {

	private CloudClientProperties cloudClientProperties;

	private IOauth2RegisteredClientService oauth2RegisteredClientService;

	@Autowired
	public void setCloudClientProperties(CloudClientProperties cloudClientProperties) {
		this.cloudClientProperties = cloudClientProperties;
	}

	@Autowired
	public void setOauth2RegisteredClientService(IOauth2RegisteredClientService oauth2RegisteredClientService) {
		this.oauth2RegisteredClientService = oauth2RegisteredClientService;
	}

	/**
	 * 登录成功
	 * @param request 请求
	 * @param response 响应
	 * @param session Session，不存在时自动创建
	 * @param tenantId 租户ID
	 * @param id 客户主键
	 * @param redirectUri 授权重定向地址
	 * @param homePage 主页
	 * @return 返回 登录成功提示语
	 */
	@PostMapping("/success")
	public Response<?> success(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String tenantId, String id, String redirectUri, String homePage) {

		if (!StringUtils.hasText(id)) {
			return Response.error("无效的客户主键");
		}

		CloudClientProperties.Client client = null;

		// 先获取配置文件中的配置
		// 与 id 对比，如果存在，直接使用
		List<CloudClientProperties.Client> clientList = cloudClientProperties.getList();
		for (CloudClientProperties.Client c : clientList) {
			if (id.equals(c.getId())) {
				client = c;
				break;
			}
		}

		// 如果不存在，使用数据库里的配置
		if (client == null) {
			// 从数据库中读取

			Oauth2RegisteredClient oauth2RegisteredClient = oauth2RegisteredClientService.getById(id);

			if (oauth2RegisteredClient == null) {
				return Response.error("无效的客户主键");
			}

			client = oauth2RegisteredClient.loadAsConfig();
			if (client == null) {
				return Response.error("无效的客户主键配置");
			}
		}

		String state = UUID.randomUUID().toString();
		session.setAttribute(client.getStateName(), state);

		ResponseMap ok = ResponseMap.ok("登录成功");

		if (UrlUtils.isAbsoluteUrl(redirectUri)) {
			log.info("使用登录参数中的授权重定向地址：{}", redirectUri);
		}
		else {
			redirectUri = client.getRedirectUriPrefix() + "/" + id;
			log.info("使用默认授权重定向地址：{}", redirectUri);
		}

		if (StringUtils.hasText(homePage) && !Constants.UNDEFINED.equals(homePage)) {
			try {
				new URL(homePage);
				log.info("使用登录参数中的登录成功主页地址：{}", homePage);
			}
			catch (Exception e) {
				log.error("非法登录成功主页地址：", e);
				homePage = client.getHomePage();
				log.warn("使用默认登录成功主页地址：{}", homePage);
			}
		}
		else {
			homePage = client.getHomePage();
			log.info("使用默认登录成功主页地址：{}", homePage);
		}
		// 将登录成功主页放入Session中，用于授权成功后页面跳转
		session.setAttribute(state, homePage);

		String authorizeUri = client.authorizeUri(state, redirectUri);
		String checkTokenUri = client.getCheckTokenUri();

		return ok.put("authorizeUri", authorizeUri).put("checkTokenUri", checkTokenUri);
	}

	// /**
	// * 登录成功主页
	// * @param request 请求
	// * @param response 响应
	// * @param session Session，不存在时自动创建
	// * @return 返回 登录成功主页
	// */
	// @GetMapping("/home-page")
	// public Response<?> homePage(HttpServletRequest request, HttpServletResponse
	// response, HttpSession session) {
	//
	// // 返回的主页可根据权限，或者用户设置的默认系统等信息返回不同的主页
	// String homePage = cloudClientProperties.getHomePage();
	//
	// return Response.ok(homePage, CodeEnums.OK.msg);
	// }

}
