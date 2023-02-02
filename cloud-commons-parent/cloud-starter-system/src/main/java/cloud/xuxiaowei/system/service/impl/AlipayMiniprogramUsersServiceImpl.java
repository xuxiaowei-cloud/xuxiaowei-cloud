package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.AlipayMiniprogramUsers;
import cloud.xuxiaowei.system.mapper.AlipayMiniprogramUsersMapper;
import cloud.xuxiaowei.system.service.IAlipayMiniprogramUsersService;
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
public class AlipayMiniprogramUsersServiceImpl extends ServiceImpl<AlipayMiniprogramUsersMapper, AlipayMiniprogramUsers>
		implements IAlipayMiniprogramUsersService {

	/**
	 * @param appid
	 * @param openId
	 * @return
	 */
	@Override
	public AlipayMiniprogramUsers getByAppidAndOpenId(String appid, String openId) {
		QueryWrapper<AlipayMiniprogramUsers> queryWrapper = new QueryWrapper<>();
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
	public AlipayMiniprogramUsers getByAppidAndUserId(String appid, String userId) {
		QueryWrapper<AlipayMiniprogramUsers> queryWrapper = new QueryWrapper<>();
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
	public AlipayMiniprogramUsers getByAppidAndUnionId(String appid, String unionId) {
		QueryWrapper<AlipayMiniprogramUsers> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("appid", appid);
		queryWrapper.eq("union_id", unionId);
		return getOne(queryWrapper);
	}

}
