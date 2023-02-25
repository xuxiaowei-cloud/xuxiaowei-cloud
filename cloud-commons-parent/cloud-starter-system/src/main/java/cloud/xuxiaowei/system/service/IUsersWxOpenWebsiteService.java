package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersWxOpenWebsite;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;

/**
 * <p>
 * 微信开放平台-网站用户 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-01
 */
public interface IUsersWxOpenWebsiteService extends IService<UsersWxOpenWebsite> {

	/**
	 * 根据 微信开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid 微信开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 * @return 返回 用户
	 */
	UsersWxOpenWebsite getByAppidAndOpenid(String appid, String openid);

	/**
	 * 根据 用户主键，获取绑定的微信信息
	 * @param usersId 用户主键
	 * @return 返回 微信信息
	 */
	UsersWxOpenWebsite getByUsersId(Long usersId);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	boolean unbinding(@NonNull Long usersId);

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 微信开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 */
	void binding(long usersId, String appid, String openid);

}
