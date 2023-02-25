package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.UsersAlipayMiniprogram;
import cloud.xuxiaowei.system.mapper.UsersAlipayMiniprogramMapper;
import cloud.xuxiaowei.system.service.IUsersAlipayMiniprogramService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付宝小程序用户 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-02-02
 */
@Service
public class UsersAlipayMiniprogramServiceImpl extends ServiceImpl<UsersAlipayMiniprogramMapper, UsersAlipayMiniprogram>
		implements IUsersAlipayMiniprogramService {

	/**
	 * @param appid
	 * @param openId
	 * @return
	 */
	@Override
	public UsersAlipayMiniprogram getByAppidAndOpenId(String appid, String openId) {
		QueryWrapper<UsersAlipayMiniprogram> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("appid", appid);
		queryWrapper.eq("open_id", openId);
		return getOne(queryWrapper);
	}

	/**
	 * @param appid
	 * @param userId
	 * @return
	 */
	@Override
	public UsersAlipayMiniprogram getByAppidAndUserId(String appid, String userId) {
		QueryWrapper<UsersAlipayMiniprogram> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("appid", appid);
		queryWrapper.eq("user_id", userId);
		return getOne(queryWrapper);
	}

	/**
	 * @param appid
	 * @param unionId
	 * @return
	 */
	@Override
	public UsersAlipayMiniprogram getByAppidAndUnionId(String appid, String unionId) {
		QueryWrapper<UsersAlipayMiniprogram> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("appid", appid);
		queryWrapper.eq("union_id", unionId);
		return getOne(queryWrapper);
	}

}
