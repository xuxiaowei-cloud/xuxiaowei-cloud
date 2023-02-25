package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersAlipayMiniprogram;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付宝小程序用户 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-02-02
 */
public interface IUsersAlipayMiniprogramService extends IService<UsersAlipayMiniprogram> {

	/**
	 * @param appid
	 * @param openId
	 * @return
	 */
	UsersAlipayMiniprogram getByAppidAndOpenId(String appid, String openId);

	/**
	 * @param appid
	 * @param userId
	 * @return
	 */
	UsersAlipayMiniprogram getByAppidAndUserId(String appid, String userId);

	/**
	 * @param appid
	 * @param unionId
	 * @return
	 */
	UsersAlipayMiniprogram getByAppidAndUnionId(String appid, String unionId);

}
