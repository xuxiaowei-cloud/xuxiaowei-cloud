package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.UsersAlipayOplatformWebsite;
import cloud.xuxiaowei.system.mapper.UsersAlipayOplatformWebsiteMapper;
import cloud.xuxiaowei.system.service.IUsersAlipayOplatformWebsiteService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 支付宝网站用户 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-02-03
 */
@Service
public class UsersAlipayOplatformWebsiteServiceImpl
		extends ServiceImpl<UsersAlipayOplatformWebsiteMapper, UsersAlipayOplatformWebsite>
		implements IUsersAlipayOplatformWebsiteService {

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 支付宝-网站用户ID
	 * @param userId 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, String userId) {
		UpdateWrapper<UsersAlipayOplatformWebsite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("appid", appid);
		updateWrapper.eq("user_Id", userId);
		updateWrapper.set("users_id", usersId);
		updateWrapper.set("binding_date", LocalDateTime.now());
		update(updateWrapper);
	}

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	@Override
	public boolean unbinding(Long usersId) {
		UpdateWrapper<UsersAlipayOplatformWebsite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

	@Override
	public UsersAlipayOplatformWebsite getByAppidAndUserId(String appid, String userId) {
		return baseMapper.getByAppidAndUserId(appid, userId);
	}

}
