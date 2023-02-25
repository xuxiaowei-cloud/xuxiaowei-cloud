package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersWeiBoWebsite;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 微博网站用户 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-22
 */
public interface IUsersWeiBoWebsiteService extends IService<UsersWeiBoWebsite> {

	/**
	 * 根据 微博开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid 微博开放平台-网站用户ID
	 * @param id 用户唯一标识
	 * @return 返回 用户
	 */
	UsersWeiBoWebsite getByAppidAndId(String appid, String id);

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 微博开放平台-网站用户ID
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
