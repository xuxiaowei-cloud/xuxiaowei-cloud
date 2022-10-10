package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.GiteeUsers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 码云Gitee用户表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-10
 */
public interface IGiteeUsersService extends IService<GiteeUsers> {

	/**
	 * 根据 码云Gitee唯一标识 查询 码云Gitee用户表
	 * @param appid AppID(码云Gitee client_id)
	 * @param id 码云Gitee唯一标识，不为空，唯一键：uk__gitee_users__id
	 * @return 返回 码云Gitee用户表
	 */
	GiteeUsers getByAppidAndId(String appid, Integer id);

}
