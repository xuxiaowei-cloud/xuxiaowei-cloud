package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersGitee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 码云Gitee用户表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-10
 */
public interface IUsersGiteeService extends IService<UsersGitee> {

	/**
	 * 根据 码云Gitee唯一标识 查询 码云Gitee用户表
	 * @param appid AppID(码云Gitee client_id)
	 * @param id 码云Gitee唯一标识，不为空
	 * @return 返回 码云Gitee用户表
	 */
	UsersGitee getByAppidAndId(String appid, Integer id);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	boolean unbinding(Long usersId);

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 微信开放平台-网站用户ID
	 * @param id 用户唯一标识
	 */
	void binding(long usersId, String appid, Integer id);

}
