package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.GitlabUsers;
import cloud.xuxiaowei.system.mapper.GitlabUsersMapper;
import cloud.xuxiaowei.system.service.IGitlabUsersService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * GitLab用户 服务实现类
 * </p>
 *
 * @author 徐晓伟
 * @since 2022-12-01
 */
@Service
public class GitlabUsersServiceImpl extends ServiceImpl<GitlabUsersMapper, GitlabUsers> implements IGitlabUsersService {

	/**
	 * 根据 GitLab-网站用户ID、用户唯一标识 查询用户
	 * @param appid GitLab-网站用户ID
	 * @param id 用户唯一标识
	 * @return 返回 用户
	 */
	@Override
	public GitlabUsers getByAppidAndId(String appid, long id) {
		return baseMapper.getByAppidAndOpenid(appid, id);
	}

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid GitLab-网站用户ID
	 * @param id 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, long id) {
		UpdateWrapper<GitlabUsers> updateWrapper = new UpdateWrapper<>();
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
		UpdateWrapper<GitlabUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

}
