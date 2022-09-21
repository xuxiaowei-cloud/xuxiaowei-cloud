package cloud.xuxiaowei.passport.service;

import cloud.xuxiaowei.passport.entity.ResetPassword;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 重置密码表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-09-21
 */
public interface IResetPasswordService extends IService<ResetPassword> {

	/**
	 * 保存重置密码的日志
	 * @param request 请求
	 * @param resetPasswordType 重置密码的类型
	 * @param usersId 用户主键
	 * @param beforePassword 重置前的密码
	 * @return 返回重置结果
	 */
	boolean saveLog(HttpServletRequest request, String resetPasswordType, Long usersId, String beforePassword);

}
