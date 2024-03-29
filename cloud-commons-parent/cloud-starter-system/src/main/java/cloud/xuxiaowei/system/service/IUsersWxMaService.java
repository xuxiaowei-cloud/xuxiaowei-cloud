package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersWxMa;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 微信小程序用户 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-15
 */
public interface IUsersWxMaService extends IService<UsersWxMa> {

	/**
	 * 保存 openid
	 * @param appid 小程序标识
	 * @param openid 用户标识（针对于某个小程序）
	 * @param unionid 用户标识（针对于同一开放平台）
	 * @return 返回 保存结果
	 */
	boolean saveOpenid(String appid, String openid, String unionid);

	/**
	 * 根据 appid、openid 查询微信小程序用户
	 * @param appid 小程序标识
	 * @param openid 用户标识（针对于某个小程序）
	 * @return 返回 微信小程序用户
	 */
	UsersWxMa getByAppidAndOpenid(String appid, String openid);

}
