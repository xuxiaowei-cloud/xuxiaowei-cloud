package cloud.xuxiaowei.user.service;

import cloud.xuxiaowei.system.vo.SocialVo;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 社交 服务
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface SocialService {

	/**
	 * 根据用户主键获取社交绑定
	 * @param usersId 用户主键
	 * @return 返回 社交绑定
	 */
	List<SocialVo> listByUsersId(Long usersId);

	/**
	 * 社交解绑
	 * @param usersId 用户主键
	 * @param socialCode 社交类型，1：微信扫码，2：码云Gitee
	 * @return 返回 解绑结果
	 */
	boolean unbinding(@NonNull Long usersId, @NonNull String socialCode);

}
