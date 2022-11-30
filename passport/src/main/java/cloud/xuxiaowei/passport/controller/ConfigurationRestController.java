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
import java.util.List;

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

	@PostMapping
	public Response<?> index(HttpServletRequest request, HttpServletResponse response) {

		List<WeChatOplatformWebsiteProperties.WeChatOplatformWebsite> weChatOplatformWebsiteList = weChatOplatformWebsiteProperties
				.getList();
		String weChatOplatformWebsiteAppid = null;
		if (weChatOplatformWebsiteList != null && weChatOplatformWebsiteList.size() > 0) {
			weChatOplatformWebsiteAppid = weChatOplatformWebsiteList.get(0).getAppid();
		}

		List<GiteeProperties.Gitee> giteeList = giteeProperties.getList();
		String giteeAppid = null;
		if (giteeList != null && giteeList.size() > 0) {
			giteeAppid = giteeList.get(0).getAppid();
		}

		List<QQWebsiteProperties.QQWebsite> qqWebsitePropertiesList = qqWebsiteProperties.getList();
		String qqWebsiteAppid = null;
		if (qqWebsitePropertiesList != null && qqWebsitePropertiesList.size() > 0) {
			qqWebsiteAppid = qqWebsitePropertiesList.get(0).getAppid();
		}

		List<WeiBoWebsiteProperties.WeiBoWebsite> weiBoWebsitePropertiesList = weiBoWebsiteProperties.getList();
		String weiBoWebsiteAppid = null;
		if (weiBoWebsitePropertiesList != null && weiBoWebsitePropertiesList.size() > 0) {
			weiBoWebsiteAppid = weiBoWebsitePropertiesList.get(0).getAppid();
		}

		List<GitLabProperties.GitLab> gitLabPropertiesList = gitLabProperties.getList();
		String gitlabAppid = null;
		if (gitLabPropertiesList != null && gitLabPropertiesList.size() > 0) {
			gitlabAppid = gitLabPropertiesList.get(0).getAppid();
		}

		return ResponseMap.ok()
				// 微信开放平台 网站应用 ID
				.put("weChatOplatformWebsiteAppid", weChatOplatformWebsiteAppid)
				// 码云Gitee 网站应用 ID
				.put("giteeAppid", giteeAppid + "?scope=user_info")
				// QQ 网站应用 ID
				.put("qqWebsiteAppid", qqWebsiteAppid + "?scope=get_user_info")
				// 微博 网站应用 ID
				.put("weiBoWebsiteAppid", weiBoWebsiteAppid)
				// GitLab 网站应用 ID
				.put("gitlabAppid", gitlabAppid + "?scope=read_user");
	}

}
