package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.GiteeUsers;
import cloud.xuxiaowei.system.mapper.GiteeUsersMapper;
import cloud.xuxiaowei.system.service.IGiteeUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
