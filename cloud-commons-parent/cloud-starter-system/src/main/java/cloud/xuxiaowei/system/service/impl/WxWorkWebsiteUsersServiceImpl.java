package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.WxWorkWebsiteUsers;
import cloud.xuxiaowei.system.mapper.WxWorkWebsiteUsersMapper;
import cloud.xuxiaowei.system.service.IWxWorkWebsiteUsersService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业微信-网站用户 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-29
 */
@Service
public class WxWorkWebsiteUsersServiceImpl extends ServiceImpl<WxWorkWebsiteUsersMapper, WxWorkWebsiteUsers>
		implements IWxWorkWebsiteUsersService {

	/**
	 * 根据 企业ID、应用ID、用户唯一标识 查询用户
	 * @param appid 企业ID
	 * @param agentid 应用ID
	 * @param openid 用户唯一标识
	 * @return 用户
	 */
	@Override
	public WxWorkWebsiteUsers getByAppidAndAgentidAndOpenid(String appid, String agentid, String openid) {
		return baseMapper.getByAppidAndAgentidAndOpenid(appid, agentid, openid);
	}

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	@Override
	public boolean unbinding(Long usersId) {
		UpdateWrapper<WxWorkWebsiteUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 企业ID
	 * @param agentid 应用ID
	 * @param openid 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, String agentid, String openid) {
		UpdateWrapper<WxWorkWebsiteUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("appid", appid);
		updateWrapper.eq("openid", openid);
		updateWrapper.eq("agentid", agentid);
		updateWrapper.set("users_id", usersId);
		updateWrapper.set("binding_date", LocalDateTime.now());
		update(updateWrapper);
	}

}
