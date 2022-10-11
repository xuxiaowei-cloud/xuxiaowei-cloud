package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.entity.ResetPassword;
import cloud.xuxiaowei.passport.mapper.ResetPasswordMapper;
import cloud.xuxiaowei.passport.service.IResetPasswordService;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.RequestUtils;
import cloud.xuxiaowei.utils.SecurityUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 重置密码表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-09-21
 */
@Service
public class ResetPasswordServiceImpl extends ServiceImpl<ResetPasswordMapper, ResetPassword>
		implements IResetPasswordService {

	/**
	 * 保存重置密码的日志
	 * @param request 请求
	 * @param resetPasswordType 重置密码的类型
	 * @param usersId 用户主键
	 * @param beforePassword 重置前的密码
	 * @return 返回重置结果
	 */
	@Override
	public boolean saveLog(HttpServletRequest request, String resetPasswordType, Long usersId, String beforePassword) {

		HttpSession session = request.getSession();

		String sessionId = session.getId();
		String headersMap = RequestUtils.getHeadersJson(request);
		String authorization = RequestUtils.getAuthorization(request);
		String payload = SecurityUtils.getPayload(authorization);
		String userAgent = RequestUtils.getUserAgent(request);
		String requestId = MDC.get(Constant.REQUEST_ID);

		ResetPassword resetPassword = new ResetPassword();
		resetPassword.setUsersId(usersId);
		resetPassword.setBeforePassword(beforePassword);
		resetPassword.setResetPasswordType(resetPasswordType);
		resetPassword.setHeadersMap(headersMap);
		resetPassword.setAuthorization(authorization);
		resetPassword.setPayload(payload);
		resetPassword.setUserAgent(userAgent);
		resetPassword.setRequestId(requestId);
		resetPassword.setSessionId(sessionId);

		return save(resetPassword);
	}

}
