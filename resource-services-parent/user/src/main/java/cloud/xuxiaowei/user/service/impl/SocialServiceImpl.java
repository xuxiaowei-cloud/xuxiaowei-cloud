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

	private IWxOpenWebsiteUsersService wxOpenWebsiteUsersService;

	private IGiteeUsersService giteeUsersService;

	private IQqWebsiteUsersService qqWebsiteUsersService;

	private IWeiBoWebsiteUsersService weiBoWebsiteUsersService;

	private IGitlabUsersService gitlabUsersService;

	private IWxWorkWebsiteUsersService wxWorkWebsiteUsersService;

	private IGithubUsersService githubUsersService;

	private IDingtalkUsersService dingtalkUsersService;

	@Autowired
	public void setWxOpenWebsiteUsersService(IWxOpenWebsiteUsersService wxOpenWebsiteUsersService) {
		this.wxOpenWebsiteUsersService = wxOpenWebsiteUsersService;
	}

	@Autowired
	public void setGiteeUsersService(IGiteeUsersService giteeUsersService) {
		this.giteeUsersService = giteeUsersService;
	}

	@Autowired
	public void setQqWebsiteUsersService(IQqWebsiteUsersService qqWebsiteUsersService) {
		this.qqWebsiteUsersService = qqWebsiteUsersService;
	}

	@Autowired
	public void setWeiBoWebsiteUsersService(IWeiBoWebsiteUsersService weiBoWebsiteUsersService) {
		this.weiBoWebsiteUsersService = weiBoWebsiteUsersService;
	}

	@Autowired
	public void setGitlabUsersService(IGitlabUsersService gitlabUsersService) {
		this.gitlabUsersService = gitlabUsersService;
	}

	@Autowired
	public void setWxWorkWebsiteUsersService(IWxWorkWebsiteUsersService wxWorkWebsiteUsersService) {
		this.wxWorkWebsiteUsersService = wxWorkWebsiteUsersService;
	}

	@Autowired
	public void setGithubUsersService(IGithubUsersService githubUsersService) {
		this.githubUsersService = githubUsersService;
	}

	@Autowired
	public void setDingtalkUsersService(IDingtalkUsersService dingtalkUsersService) {
		this.dingtalkUsersService = dingtalkUsersService;
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
				return wxOpenWebsiteUsersService.unbinding(usersId);
			case "2":
				return giteeUsersService.unbinding(usersId);
			case "3":
				return qqWebsiteUsersService.unbinding(usersId);
			case "4":
				return weiBoWebsiteUsersService.unbinding(usersId);
			case "5":
				return gitlabUsersService.unbinding(usersId);
			case "6":
				return wxWorkWebsiteUsersService.unbinding(usersId);
			case "7":
				return githubUsersService.unbinding(usersId);
			case "8":
				return dingtalkUsersService.unbinding(usersId);
			default:
				return false;
		}
	}

}
