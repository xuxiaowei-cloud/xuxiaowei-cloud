package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.UsersFeishuWebpage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 飞书用户表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-02
 */
public interface IUsersFeishuWebpageService extends IService<UsersFeishuWebpage> {

	UsersFeishuWebpage getByAppidAndOpenId(String appid, String openId);

	void binding(long usersId, String appid, String openId);

	boolean unbinding(Long usersId);

}
