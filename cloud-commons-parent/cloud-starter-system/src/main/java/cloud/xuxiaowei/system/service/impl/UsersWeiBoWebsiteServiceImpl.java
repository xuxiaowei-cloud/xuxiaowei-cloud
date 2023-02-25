package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.UsersWeiBoWebsite;
import cloud.xuxiaowei.system.mapper.UsersWeiBoWebsiteMapper;
import cloud.xuxiaowei.system.service.IUsersWeiBoWebsiteService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 微博网站用户 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-22
 */
@Service
public class UsersWeiBoWebsiteServiceImpl extends ServiceImpl<UsersWeiBoWebsiteMapper, UsersWeiBoWebsite>
		implements IUsersWeiBoWebsiteService {

	/**
	 * 根据 微博开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid 微博开放平台-网站用户ID
	 * @param id 用户唯一标识
	 * @return 返回 用户
	 */
	@Override
	public UsersWeiBoWebsite getByAppidAndId(String appid, String id) {
		return baseMapper.getByAppidAndId(appid, id);
	}

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 微博开放平台-网站用户ID
	 * @param id 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, long id) {
		UpdateWrapper<UsersWeiBoWebsite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("appid", appid);
		updateWrapper.eq("id", id);
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
		UpdateWrapper<UsersWeiBoWebsite> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

}
