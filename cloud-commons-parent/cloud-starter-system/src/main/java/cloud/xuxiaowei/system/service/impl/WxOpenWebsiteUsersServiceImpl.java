package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.WxOpenWebsiteUsers;
import cloud.xuxiaowei.system.mapper.WxOpenWebsiteUsersMapper;
import cloud.xuxiaowei.system.service.IWxOpenWebsiteUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信开放平台-网站用户 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-01
 */
@Service
public class WxOpenWebsiteUsersServiceImpl extends ServiceImpl<WxOpenWebsiteUsersMapper, WxOpenWebsiteUsers>
		implements IWxOpenWebsiteUsersService {

	/**
	 * 根据 微信开放平台-网站用户ID、用户唯一标识 查询用户
	 * @param appid 微信开放平台-网站用户ID
	 * @param openid 用户唯一标识
	 * @return 返回 用户
	 */
	@Override
	public WxOpenWebsiteUsers getByAppidAndOpenid(String appid, String openid) {
		return baseMapper.getByAppidAndOpenid(appid, openid);
	}

}
