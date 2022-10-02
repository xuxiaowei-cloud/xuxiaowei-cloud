package cloud.xuxiaowei.user.service.impl;

import cloud.xuxiaowei.system.entity.WxOpenWebsiteUsers;
import cloud.xuxiaowei.system.service.IWxOpenWebsiteUsersService;
import cloud.xuxiaowei.user.service.SocialService;
import cloud.xuxiaowei.user.vo.SocialVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 社交 服务
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class SocialServiceImpl implements SocialService {

	private IWxOpenWebsiteUsersService wxOpenWebsiteUsersService;

	@Autowired
	public void setWxOpenWebsiteUsersService(IWxOpenWebsiteUsersService wxOpenWebsiteUsersService) {
		this.wxOpenWebsiteUsersService = wxOpenWebsiteUsersService;
	}

	/**
	 * 根据用户主键获取社交绑定
	 * @param usersId 用户主键
	 * @return 返回 社交绑定
	 */
	@Override
	public List<SocialVo> listByUsersId(Long usersId) {
		List<SocialVo> socialVoList = new ArrayList<>();

		WxOpenWebsiteUsers wxOpenWebsiteUsers = wxOpenWebsiteUsersService.getByUsersId(usersId);
		SocialVo socialWxOpenWebsiteVo = new SocialVo();
		socialWxOpenWebsiteVo.setSocialName("微信");
		socialWxOpenWebsiteVo.setSocialCode("1");
		socialVoList.add(socialWxOpenWebsiteVo);
		if (wxOpenWebsiteUsers == null) {
			socialWxOpenWebsiteVo.setBinding(false);
		}
		else {
			socialWxOpenWebsiteVo.setBinding(true);
			socialWxOpenWebsiteVo.setNickname(wxOpenWebsiteUsers.getNickname());
			socialWxOpenWebsiteVo.setHeadimgurl(wxOpenWebsiteUsers.getHeadimgurl());
			socialWxOpenWebsiteVo.setBindingDate(wxOpenWebsiteUsers.getBindingDate());
		}

		return socialVoList;
	}

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @param socialCode 社交类型，1：微信扫码
	 * @return 返回 解绑结果
	 */
	@Override
	public boolean unbinding(@NonNull Long usersId, @NonNull String socialCode) {
		switch (socialCode) {
			case "1":
				return wxOpenWebsiteUsersService.unbinding(usersId);
			default:
				return false;
		}
	}

}
