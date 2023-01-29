package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.map.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.properties.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/configuration")
public class ConfigurationRestController {

	private WeChatOplatformWebsiteProperties weChatOplatformWebsiteProperties;

	private GiteeProperties giteeProperties;

	private QQWebsiteProperties qqWebsiteProperties;

	private WeiBoWebsiteProperties weiBoWebsiteProperties;

	private GitLabProperties gitLabProperties;

	private WeChatWorkWebsiteProperties weChatWorkWebsiteProperties;

	@Autowired
	public void setWeChatOplatformWebsiteProperties(WeChatOplatformWebsiteProperties weChatOplatformWebsiteProperties) {
		this.weChatOplatformWebsiteProperties = weChatOplatformWebsiteProperties;
	}

	@Autowired
	public void setGiteeProperties(GiteeProperties giteeProperties) {
		this.giteeProperties = giteeProperties;
	}

	@Autowired
	public void setQqWebsiteProperties(QQWebsiteProperties qqWebsiteProperties) {
		this.qqWebsiteProperties = qqWebsiteProperties;
	}

	@Autowired
	public void setWeiBoWebsiteProperties(WeiBoWebsiteProperties weiBoWebsiteProperties) {
		this.weiBoWebsiteProperties = weiBoWebsiteProperties;
	}

	@Autowired
	public void setGitLabProperties(GitLabProperties gitLabProperties) {
		this.gitLabProperties = gitLabProperties;
	}

	@Autowired
	public void setWeChatWorkWebsiteProperties(WeChatWorkWebsiteProperties weChatWorkWebsiteProperties) {
		this.weChatWorkWebsiteProperties = weChatWorkWebsiteProperties;
	}

	@PostMapping
	public Response<?> index(HttpServletRequest request, HttpServletResponse response) {

		String weChatOplatformWebsiteAppid = weChatOplatformWebsiteProperties.getDefaultAppid();
		String giteeAppid = giteeProperties.getDefaultAppid();
		String qqWebsiteAppid = qqWebsiteProperties.getDefaultAppid();
		String weiBoWebsiteAppid = weiBoWebsiteProperties.getDefaultAppid();
		String gitlabAppid = gitLabProperties.getDefaultAppid();
		String weChatWorkWebsiteAppid = weChatWorkWebsiteProperties.getDefaultAppid();
		String weChatWorkWebsiteAgentid = weChatWorkWebsiteProperties.getGetDefaultAgentid();

		return ResponseMap.ok()
				// 微信开放平台 网站应用 ID
				.put("weChatOplatformWebsiteAppid", weChatOplatformWebsiteAppid)
				// 码云Gitee 网站应用 ID
				.put("giteeAppid", giteeAppid)
				// QQ 网站应用 ID
				.put("qqWebsiteAppid", qqWebsiteAppid)
				// 微博 网站应用 ID
				.put("weiBoWebsiteAppid", weiBoWebsiteAppid)
				// GitLab 网站应用 ID
				.put("gitlabAppid", gitlabAppid)
				// 企业微信扫码 网站应用 ID
				.put("weChatWorkWebsiteAppid", weChatWorkWebsiteAppid)
				.put("weChatWorkWebsiteAgentid", weChatWorkWebsiteAgentid);
	}

}
