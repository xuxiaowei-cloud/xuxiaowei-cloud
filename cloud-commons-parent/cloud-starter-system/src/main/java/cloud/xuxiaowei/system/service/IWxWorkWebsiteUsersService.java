package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.WxWorkWebsiteUsers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 企业微信-网站用户 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-29
 */
public interface IWxWorkWebsiteUsersService extends IService<WxWorkWebsiteUsers> {

	/**
	 * 根据 企业ID、应用ID、用户唯一标识 查询用户
	 * @param appid 企业ID
	 * @param agentid 应用ID
	 * @param openid 用户唯一标识
	 * @return 用户
	 */
	WxWorkWebsiteUsers getByAppidAndAgentidAndOpenid(String appid, String agentid, String openid);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	boolean unbinding(Long usersId);

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 企业ID
	 * @param agentid 应用ID
	 * @param openid 用户唯一标识
	 */
	void binding(long usersId, String appid, String agentid, String openid);

}
