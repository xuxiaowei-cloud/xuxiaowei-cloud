package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.AlipayOplatformWebsiteUsers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付宝网站用户 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-02-03
 */
public interface IAlipayOplatformWebsiteUsersService extends IService<AlipayOplatformWebsiteUsers> {

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 支付宝-网站用户ID
	 * @param userId 用户唯一标识
	 */
	void binding(long usersId, String appid, String userId);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	boolean unbinding(Long usersId);

	AlipayOplatformWebsiteUsers getByAppidAndUserId(String appid, String userId);

}
