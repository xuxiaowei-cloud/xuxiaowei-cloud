package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.UsersWxMa;
import cloud.xuxiaowei.system.mapper.UsersWxMaMapper;
import cloud.xuxiaowei.system.service.IUsersWxMaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信小程序用户 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-15
 */
@Service
public class UsersWxMaServiceImpl extends ServiceImpl<UsersWxMaMapper, UsersWxMa> implements IUsersWxMaService {

	/**
	 * 保存 openid
	 * @param appid 小程序标识
	 * @param openid 用户标识（针对于某个小程序）
	 * @param unionid 用户标识（针对于同一开放平台）
	 */
	@Override
	public boolean saveOpenid(String appid, String openid, String unionid) {
		UsersWxMa getOne = getByAppidAndOpenid(appid, openid);
		if (getOne == null) {
			UsersWxMa usersWxMa = new UsersWxMa();
			usersWxMa.setAppid(appid);
			usersWxMa.setOpenid(openid);
			usersWxMa.setUnionid(unionid);
			return save(usersWxMa);
		}

		return false;
	}

	/**
	 * 根据 appid、openid 查询微信小程序用户
	 * @param appid 小程序标识
	 * @param openid 用户标识（针对于某个小程序）
	 * @return 返回 微信小程序用户
	 */
	public UsersWxMa getByAppidAndOpenid(String appid, String openid) {
		QueryWrapper<UsersWxMa> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("appid", appid);
		queryWrapper.eq("openid", openid);
		return getOne(queryWrapper);
	}

}
