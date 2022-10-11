package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.GiteeUsers;
import cloud.xuxiaowei.system.mapper.GiteeUsersMapper;
import cloud.xuxiaowei.system.service.IGiteeUsersService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 码云Gitee用户表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-10
 */
@Service
public class GiteeUsersServiceImpl extends ServiceImpl<GiteeUsersMapper, GiteeUsers> implements IGiteeUsersService {

	/**
	 * 根据 码云Gitee唯一标识 查询 码云Gitee用户表
	 * @param appid AppID(码云Gitee client_id)
	 * @param id 码云Gitee唯一标识，不为空，唯一键：uk__gitee_users__id
	 * @return 返回 码云Gitee用户表
	 */
	@Override
	public GiteeUsers getByAppidAndId(String appid, Integer id) {
		return baseMapper.getByAppidAndId(appid, id);
	}

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	@Override
	public boolean unbinding(Long usersId) {
		UpdateWrapper<GiteeUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 微信开放平台-网站用户ID
	 * @param id 用户唯一标识
	 */
	@Override
	public void binding(long usersId, String appid, Integer id) {
		UpdateWrapper<GiteeUsers> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("appid", appid);
		updateWrapper.eq("id", id);
		updateWrapper.set("users_id", usersId);
		updateWrapper.set("binding_date", LocalDateTime.now());
		update(updateWrapper);
	}

}
