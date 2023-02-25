package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersQqMiniprogram;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * QQ小程序用户 服务类
 * </p>
 *
 * @author xuxiaoweei
 * @since 2023-02-02
 */
public interface IUsersQqMiniprogramService extends IService<UsersQqMiniprogram> {

	/**
	 * 根据 appid、openid 查询微信小程序用户
	 * @param appid 小程序标识
	 * @param openid 用户标识（针对于某个小程序）
	 * @return 返回 微信小程序用户
	 */
	UsersQqMiniprogram getByAppidAndOpenid(String appid, String openid);

}
