package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.AlipayMiniprogramUsers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付宝小程序用户 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-02-02
 */
public interface IAlipayMiniprogramUsersService extends IService<AlipayMiniprogramUsers> {

	/**
	 * @param appid
	 * @param openId
	 * @return
	 */
	AlipayMiniprogramUsers getByAppidAndOpenId(String appid, String openId);

	/**
	 * @param appid
	 * @param userId
	 * @return
	 */
	AlipayMiniprogramUsers getByAppidAndUserId(String appid, String userId);

	/**
	 * @param appid
	 * @param unionId
	 * @return
	 */
	AlipayMiniprogramUsers getByAppidAndUnionId(String appid, String unionId);

}
