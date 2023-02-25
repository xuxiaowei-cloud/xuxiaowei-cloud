package cloud.xuxiaowei.user.service.impl;

import cloud.xuxiaowei.system.mapper.SocialMapper;
import cloud.xuxiaowei.system.service.*;
import cloud.xuxiaowei.system.vo.SocialVo;
import cloud.xuxiaowei.user.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 社交 服务
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class SocialServiceImpl implements SocialService {

	@Resource
	private SocialMapper socialMapper;

	private IUsersWxOpenWebsiteService usersWxOpenWebsiteService;

	private IUsersGiteeService usersGiteeService;

	private IUsersQqWebsiteService usersQqWebsiteService;

	private IUsersWeiBoWebsiteService usersWeiBoWebsiteService;

	private IUsersGitlabService usersGitlabService;

	private IUsersWxWorkWebsiteService usersWxWorkWebsiteService;

	private IUsersGithubService usersGithubService;

	private IUsersDingtalkService usersDingtalkService;

	private IUsersAlipayOplatformWebsiteService usersAlipayOplatformWebsiteService;

	@Autowired
	public void setUsersWxOpenWebsiteService(IUsersWxOpenWebsiteService usersWxOpenWebsiteService) {
		this.usersWxOpenWebsiteService = usersWxOpenWebsiteService;
	}

	@Autowired
	public void setUsersGiteeService(IUsersGiteeService usersGiteeService) {
		this.usersGiteeService = usersGiteeService;
	}

	@Autowired
	public void setUsersQqWebsiteService(IUsersQqWebsiteService usersQqWebsiteService) {
		this.usersQqWebsiteService = usersQqWebsiteService;
	}

	@Autowired
	public void setUsersWeiBoWebsiteService(IUsersWeiBoWebsiteService usersWeiBoWebsiteService) {
		this.usersWeiBoWebsiteService = usersWeiBoWebsiteService;
	}

	@Autowired
	public void setUsersGitlabService(IUsersGitlabService usersGitlabService) {
		this.usersGitlabService = usersGitlabService;
	}

	@Autowired
	public void setUsersWxWorkWebsiteService(IUsersWxWorkWebsiteService usersWxWorkWebsiteService) {
		this.usersWxWorkWebsiteService = usersWxWorkWebsiteService;
	}

	@Autowired
	public void setUsersGithubService(IUsersGithubService usersGithubService) {
		this.usersGithubService = usersGithubService;
	}

	@Autowired
	public void setUsersDingtalkService(IUsersDingtalkService usersDingtalkService) {
		this.usersDingtalkService = usersDingtalkService;
	}

	@Autowired
	public void setUsersAlipayOplatformWebsiteService(
			IUsersAlipayOplatformWebsiteService usersAlipayOplatformWebsiteService) {
		this.usersAlipayOplatformWebsiteService = usersAlipayOplatformWebsiteService;
	}

	/**
	 * 根据用户主键获取社交绑定
	 * @param usersId 用户主键
	 * @return 返回 社交绑定
	 */
	@Override
	public List<SocialVo> listByUsersId(Long usersId) {
		return socialMapper.listByUsersId(usersId);
	}

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @param socialCode 社交类型，1：微信扫码，2：码云Gitee
	 * @return 返回 解绑结果
	 */
	@Override
	public boolean unbinding(@NonNull Long usersId, @NonNull String socialCode) {
		switch (socialCode) {
			case "1":
				return usersWxOpenWebsiteService.unbinding(usersId);
			case "2":
				return usersGiteeService.unbinding(usersId);
			case "3":
				return usersQqWebsiteService.unbinding(usersId);
			case "4":
				return usersWeiBoWebsiteService.unbinding(usersId);
			case "5":
				return usersGitlabService.unbinding(usersId);
			case "6":
				return usersWxWorkWebsiteService.unbinding(usersId);
			case "7":
				return usersGithubService.unbinding(usersId);
			case "8":
				return usersDingtalkService.unbinding(usersId);
			case "9":
				return usersAlipayOplatformWebsiteService.unbinding(usersId);
			default:
				return false;
		}
	}

}
