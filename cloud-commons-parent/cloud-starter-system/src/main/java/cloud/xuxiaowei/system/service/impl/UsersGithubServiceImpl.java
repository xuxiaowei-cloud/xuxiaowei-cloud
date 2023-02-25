package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.UsersGithub;
import cloud.xuxiaowei.system.mapper.UsersGithubMapper;
import cloud.xuxiaowei.system.service.IUsersGithubService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * Github用户表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-30
 */
@Service
public class UsersGithubServiceImpl extends ServiceImpl<UsersGithubMapper, UsersGithub> implements IUsersGithubService {

	/**
	 * 根据 GitHub唯一标识 查询 GitHub用户表
	 * @param appid 此处是 {@link String} 类型的 clientId，并不是 GitHub 中的 {@link Integer} 类型的appid
	 * @param id GitHub唯一标识
	 * @return 返回 GitHub用户表
	 */
	@Override
	public UsersGithub getByAppidAndId(String appid, int id) {
		return baseMapper.getByAppidAndId(appid, id);
	}

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid github-网站用户ID
	 * @param id 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, int id) {
		UpdateWrapper<UsersGithub> updateWrapper = new UpdateWrapper<>();
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
		UpdateWrapper<UsersGithub> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

}
