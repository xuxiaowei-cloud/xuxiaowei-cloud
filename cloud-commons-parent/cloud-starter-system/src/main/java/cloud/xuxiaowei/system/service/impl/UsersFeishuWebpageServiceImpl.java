package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.UsersFeishuWebpage;
import cloud.xuxiaowei.system.mapper.UsersFeishuWebpageMapper;
import cloud.xuxiaowei.system.service.IUsersFeishuWebpageService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 飞书用户表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-02
 */
@Service
public class UsersFeishuWebpageServiceImpl extends ServiceImpl<UsersFeishuWebpageMapper, UsersFeishuWebpage>
		implements IUsersFeishuWebpageService {

	@Override
	public UsersFeishuWebpage getByAppidAndOpenId(String appid, String openId) {
		return baseMapper.getByAppidAndOpenId(appid, openId);
	}

	@Override
	public void binding(long usersId, String appid, String openId) {
		UpdateWrapper<UsersFeishuWebpage> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("appid", appid);
		updateWrapper.eq("open_id", openId);
		updateWrapper.set("users_id", usersId);
		updateWrapper.set("binding_date", LocalDateTime.now());
		update(updateWrapper);
	}

	@Override
	public boolean unbinding(Long usersId) {
		UpdateWrapper<UsersFeishuWebpage> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("users_id", null);
		return update(updateWrapper);
	}

}
