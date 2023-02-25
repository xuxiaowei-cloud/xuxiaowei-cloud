package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersGitlab;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * GitLab用户 服务类
 * </p>
 *
 * @author 徐晓伟
 * @since 2022-12-01
 */
public interface IUsersGitlabService extends IService<UsersGitlab> {

	/**
	 * 根据 GitLab-网站用户ID、用户唯一标识 查询用户
	 * @param appid GitLab-网站用户ID
	 * @param id 用户唯一标识
	 * @return 返回 用户
	 */
	UsersGitlab getByAppidAndId(String appid, long id);

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid GitLab-网站用户ID
	 * @param id 用户唯一标识
	 */
	void binding(long usersId, String appid, long id);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	boolean unbinding(Long usersId);

}
