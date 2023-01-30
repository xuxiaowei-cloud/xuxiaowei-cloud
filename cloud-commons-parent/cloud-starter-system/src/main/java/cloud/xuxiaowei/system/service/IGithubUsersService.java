package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.GithubUsers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * Github用户表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-30
 */
public interface IGithubUsersService extends IService<GithubUsers> {

	/**
	 * 根据 GitHub唯一标识 查询 GitHub用户表
	 * @param appid 此处是 {@link String} 类型的 clientId，并不是 GitHub 中的 {@link Integer} 类型的appid
	 * @param id GitHub唯一标识
	 * @return 返回 GitHub用户表
	 */
	GithubUsers getByAppidAndId(String appid, int id);

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid github-网站用户ID
	 * @param id 用户唯一标识
	 */
    void binding(long usersId, String appid, int id);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
    boolean unbinding(Long usersId);

}
