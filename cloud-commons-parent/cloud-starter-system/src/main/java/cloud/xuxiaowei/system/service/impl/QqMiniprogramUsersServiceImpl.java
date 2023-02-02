package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.QqMiniprogramUsers;
import cloud.xuxiaowei.system.mapper.QqMiniprogramUsersMapper;
import cloud.xuxiaowei.system.service.IQqMiniprogramUsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * QQ小程序用户 服务实现类
 * </p>
 *
 * @author xuxiaoweei
 * @since 2023-02-02
 */
@Service
public class QqMiniprogramUsersServiceImpl extends ServiceImpl<QqMiniprogramUsersMapper, QqMiniprogramUsers>
		implements IQqMiniprogramUsersService {

	/**
	 * 根据 appid、openid 查询微信小程序用户
	 * @param appid 小程序标识
	 * @param openid 用户标识（针对于某个小程序）
	 * @return 返回 微信小程序用户
	 */
	@Override
	public QqMiniprogramUsers getByAppidAndOpenid(String appid, String openid) {
		QueryWrapper<QqMiniprogramUsers> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("appid", appid);
		queryWrapper.eq("openid", openid);
		return getOne(queryWrapper);
	}

}
