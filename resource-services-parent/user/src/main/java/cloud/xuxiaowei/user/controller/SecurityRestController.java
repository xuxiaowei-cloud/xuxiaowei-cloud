package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.system.annotation.EncryptAnnotation;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.AliyunDySmsApiService;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.service.SessionService;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.Encrypt;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.SecurityUtils;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.utils.map.ResponseMap;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 安全设置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityRestController {

	private static final String SECURITY_PHONE_SMS_CAPTCHA = "security_phone_sms_captcha:";

	private static final Random RANDOM = new Random();

	private IUsersService usersService;

	private AliyunDySmsApiService aliyunDySmsApiService;

	private CloudSecurityProperties cloudSecurityProperties;

	private SessionService sessionService;

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	@Autowired
	public void setAliyunDySmsApiService(AliyunDySmsApiService aliyunDySmsApiService) {
		this.aliyunDySmsApiService = aliyunDySmsApiService;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	/**
	 * 安全设置 由于返回的数据包含手机号、邮箱等，推荐使用注解配置加密
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 安全设置
	 */
	@EncryptAnnotation(Encrypt.AesVersion.V1)
	@ControllerAnnotation(description = "安全设置")
	@PreAuthorize("hasAuthority('user_info')")
	@RequestMapping
	public Response<?> index(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		Long usersId = SecurityUtils.getUsersId(authentication);
		Users users = usersService.getById(usersId);
		if (users == null) {
			return Response.error("未找到用户信息");
		}

		// @formatter:off
		return ResponseMap.ok()
				.put("phone", users.getPhone())
				.put("email", users.getEmail());
		// @formatter:on
	}

	/**
	 * 安全设置：修改新手机号的短信验证码
	 * @param request 请求
	 * @param response 响应
	 * @param phone 新手机号
	 * @return 返回 发送结果
	 */
	@EncryptAnnotation(Encrypt.AesVersion.V1)
	@ControllerAnnotation(description = "安全设置：修改新手机号的短信验证码")
	@PreAuthorize("hasAuthority('user_info')")
	@RequestMapping("/sms")
	public Response<?> sms(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			String phone) {

		Long usersId = SecurityUtils.getUsersId(authentication);

		// 识别码
		String identification = RandomStringUtils.random(4, Joiner.on("").join(Constant.UPPER_CASE_LIST));

		// 100000 到 999999 之间的随机数
		String code = RANDOM.nextInt(900000) + 100000 + "";

		int phoneCaptchaMinutes = cloudSecurityProperties.getPhoneCaptchaMinutes();

		sessionService.set(SECURITY_PHONE_SMS_CAPTCHA + usersId + ":" + phone + ":" + identification, code,
				phoneCaptchaMinutes, TimeUnit.MINUTES);

		boolean success = false;
		try {
			aliyunDySmsApiService.sendSmsCode(phone, code);
			success = true;
		}
		catch (Exception e) {
			throw new CloudRuntimeException("安全设置：修改新手机号的短信验证码时，发送短信验证码异常", e);
		}
		finally {
			log.info("安全设置：修改新手机号的短信验证码时，手机号：{}，验证码：{}，有效时间：{} 分钟，发送结果：{}", phone, code, phoneCaptchaMinutes, success);
		}

		return ResponseMap.ok().put("identification", identification);
	}

}
