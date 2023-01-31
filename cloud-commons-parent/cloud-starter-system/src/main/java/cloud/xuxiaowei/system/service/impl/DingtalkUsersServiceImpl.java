package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.DingtalkUsers;
import cloud.xuxiaowei.system.mapper.DingtalkUsersMapper;
import cloud.xuxiaowei.system.service.IDingtalkUsersService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 钉钉用户表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-31
 */
@Service
public class DingtalkUsersServiceImpl extends ServiceImpl<DingtalkUsersMapper, DingtalkUsers>
		implements IDingtalkUsersService {

	/**
	 * 根据 钉钉-网站用户ID、用户唯一标识 查询用户
	 * @param appid 钉钉-网站用户ID
	 * @param openId 用户唯一标识
	 * @return 返回 用户
	 */
	@Override
	public DingtalkUsers getByAppidAndOpenId(String appid, String openId) {
		return baseMapper.getByAppidAndOpenId(appid, openId);
	}

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 钉钉-网站用户ID
	 * @param openId 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, String openId) {
		UpdateWrapper<DingtalkUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("appid", appid);
		updateWrapper.eq("open_id", openId);
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
		UpdateWrapper<DingtalkUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

}
