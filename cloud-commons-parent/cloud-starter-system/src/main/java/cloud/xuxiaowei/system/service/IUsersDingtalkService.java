package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersDingtalk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 钉钉用户表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-31
 */
public interface IUsersDingtalkService extends IService<UsersDingtalk> {

	/**
	 * 根据 钉钉-网站用户ID、用户唯一标识 查询用户
	 * @param appid 钉钉-网站用户ID
	 * @param openId 用户唯一标识
	 * @return 返回 用户
	 */
	UsersDingtalk getByAppidAndOpenId(String appid, String openId);

	/**
	 * 社交绑定
	 * @param usersId 用户主键
	 * @param appid 钉钉-网站用户ID
	 * @param openId 用户唯一标识
	 */
	void binding(long usersId, String appid, String openId);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @return 返回 解绑结果
	 */
	boolean unbinding(Long usersId);

}
