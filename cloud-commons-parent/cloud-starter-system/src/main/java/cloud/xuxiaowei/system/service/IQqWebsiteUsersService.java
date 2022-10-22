package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.QqWebsiteUsers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * QQ网站应用表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-13
 */
public interface IQqWebsiteUsersService extends IService<QqWebsiteUsers> {

	/**
	 * 根据 QQ开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid QQ开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 * @return 返回 用户
	 */
	QqWebsiteUsers getByAppidAndOpenid(String appid, String openid);

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid QQ开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 */
	void binding(long usersId, String appid, String openid);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	boolean unbinding(Long usersId);

}
