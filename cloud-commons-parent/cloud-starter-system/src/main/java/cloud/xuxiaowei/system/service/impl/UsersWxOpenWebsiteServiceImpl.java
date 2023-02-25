package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.UsersWxOpenWebsite;
import cloud.xuxiaowei.system.mapper.UsersWxOpenWebsiteMapper;
import cloud.xuxiaowei.system.service.IUsersWxOpenWebsiteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 微信开放平台-网站用户 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-01
 */
@Service
public class UsersWxOpenWebsiteServiceImpl extends ServiceImpl<UsersWxOpenWebsiteMapper, UsersWxOpenWebsite>
		implements IUsersWxOpenWebsiteService {

	/**
	 * 根据 微信开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid 微信开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 * @return 返回 用户
	 */
	@Override
	public UsersWxOpenWebsite getByAppidAndOpenid(String appid, String openid) {
		return baseMapper.getByAppidAndOpenid(appid, openid);
	}

	/**
	 * 根据 用户主键，获取绑定的微信信息
	 * @param usersId 用户主键
	 * @return 返回 微信信息
	 */
	@Override
	public UsersWxOpenWebsite getByUsersId(Long usersId) {
		QueryWrapper<UsersWxOpenWebsite> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("users_id", usersId);
		return getOne(queryWrapper);
	}

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	@Override
	public boolean unbinding(@NonNull Long usersId) {
		UpdateWrapper<UsersWxOpenWebsite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 微信开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, String openid) {
		UpdateWrapper<UsersWxOpenWebsite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("appid", appid);
		updateWrapper.eq("openid", openid);
		updateWrapper.set("users_id", usersId);
		updateWrapper.set("binding_date", LocalDateTime.now());
		update(updateWrapper);
	}

}
