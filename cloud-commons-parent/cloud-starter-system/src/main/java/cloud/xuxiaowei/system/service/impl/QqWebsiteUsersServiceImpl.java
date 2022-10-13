package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.QqWebsiteUsers;
import cloud.xuxiaowei.system.mapper.QqWebsiteUsersMapper;
import cloud.xuxiaowei.system.service.IQqWebsiteUsersService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * QQ网站应用表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-13
 */
@Service
public class QqWebsiteUsersServiceImpl extends ServiceImpl<QqWebsiteUsersMapper, QqWebsiteUsers>
		implements IQqWebsiteUsersService {

	/**
	 * 根据 QQ开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid QQ开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 * @return 返回 用户
	 */
	@Override
	public QqWebsiteUsers getByAppidAndOpenid(String appid, String openid) {
		return baseMapper.getByAppidAndOpenid(appid, openid);
	}

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid QQ开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, String openid) {
		UpdateWrapper<QqWebsiteUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("appid", appid);
		updateWrapper.eq("openid", openid);
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
		UpdateWrapper<QqWebsiteUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

}
