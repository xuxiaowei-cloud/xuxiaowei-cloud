package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.system.annotation.EncryptAnnotation;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.AliyunDySmsApiService;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.service.SessionService;
import cloud.xuxiaowei.user.bo.SecurityEmailBo;
import cloud.xuxiaowei.user.bo.SecurityEmailUpdateBo;
import cloud.xuxiaowei.user.bo.SecurityPhoneBo;
import cloud.xuxiaowei.user.bo.SecurityPhoneUpdateBo;
import cloud.xuxiaowei.utils.*;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.utils.map.ResponseMap;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

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

	private static final String SECURITY_EMAIL_CAPTCHA = "security_email_captcha:";

	private static final Random RANDOM = new Random();

	private JavaMailSender javaMailSender;

	private MailProperties mailProperties;

	private IUsersService usersService;

	private AliyunDySmsApiService aliyunDySmsApiService;

	private CloudSecurityProperties cloudSecurityProperties;

	private SessionService sessionService;

	/**
	 * 注意： 当未成功配置邮箱时，{@link Autowired} 直接注入将会失败，导致程序无法启动
	 * <p>
	 * 故将 {@link Autowired} 的 required 设置为 false，避免程序启动失败。使用时请判断该值是否为 null
	 */
	@Autowired(required = false)
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Autowired
	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

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
	@PreAuthorize("@ant.hasAuthority('user:info')")
	@PostMapping
	public Response<?> index(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		Long usersId = SecurityUtils.getUsersId(authentication);
		Users users = usersService.getById(usersId);
		if (users == null) {
			return Response.error("未找到用户信息");
		}

		return ResponseMap.ok().put("phone", users.getPhone()).put("email", users.getEmail());
	}

	/**
	 * 安全设置：修改新手机号的短信验证码
	 * @param request 请求
	 * @param response 响应
	 * @param securityPhoneBo 新手机号
	 * @return 返回 发送结果
	 */
	@EncryptAnnotation(Encrypt.AesVersion.V1)
	@ControllerAnnotation(description = "安全设置：修改新手机号的短信验证码")
	@PreAuthorize("@ant.hasAuthority('user:info')")
	@PostMapping("/phone")
	public Response<?> phone(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			@Valid @RequestBody SecurityPhoneBo securityPhoneBo) {

		Long usersId = SecurityUtils.getUsersId(authentication);
		String phone = securityPhoneBo.getPhone();

		// 识别码
		String identification = RandomStringUtils.random(4, Constants.UPPER_CASE_LETTERS);

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
			log.info("安全设置：修改新手机号的短信验证码时，手机号：{}，识别码：{}，验证码：{}，有效时间：{} 分钟，发送结果：{}", phone, identification, code,
					phoneCaptchaMinutes, success);
		}

		return ResponseMap.ok().put("identification", identification);
	}

	/**
	 * 安全设置：修改新手机号
	 * @param request 请求
	 * @param response 响应
	 * @param securityPhoneUpdateBo 新手机号
	 * @return 返回 发送结果
	 */
	@EncryptAnnotation(Encrypt.AesVersion.V1)
	@ControllerAnnotation(description = "安全设置：修改新手机号")
	@PreAuthorize("@ant.hasAuthority('user:info')")
	@PostMapping("/phone/update")
	public Response<?> phoneUpdate(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication, @Valid @RequestBody SecurityPhoneUpdateBo securityPhoneUpdateBo) {

		Long usersId = SecurityUtils.getUsersId(authentication);

		String phone = securityPhoneUpdateBo.getPhone();
		String identification = securityPhoneUpdateBo.getIdentification();

		String code = sessionService.get(SECURITY_PHONE_SMS_CAPTCHA + usersId + ":" + phone + ":" + identification);
		if (securityPhoneUpdateBo.getCode().equals(code)) {
			sessionService.remove(SECURITY_PHONE_SMS_CAPTCHA + usersId + ":" + phone + ":" + identification);
			boolean update = usersService.updatePhoneById(usersId, phone);
			return Response.ok();
		}

		return Response.error("验证码无效");
	}

	/**
	 * 安全设置：修改新邮箱的验证码
	 * @param request 请求
	 * @param response 响应
	 * @param securityEmailBo 新邮箱
	 * @return 返回 发送结果
	 */
	@EncryptAnnotation(Encrypt.AesVersion.V1)
	@ControllerAnnotation(description = "安全设置：修改新邮箱的验证码")
	@PreAuthorize("@ant.hasAuthority('user:info')")
	@PostMapping("/email")
	public Response<?> email(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			@Valid @RequestBody SecurityEmailBo securityEmailBo) {

		Long usersId = SecurityUtils.getUsersId(authentication);
		String email = securityEmailBo.getEmail();

		// 识别码
		String identification = RandomStringUtils.random(4, Constants.UPPER_CASE_LETTERS);

		// 100000 到 999999 之间的随机数
		String code = RANDOM.nextInt(900000) + 100000 + "";

		int emailCaptchaMinutes = cloudSecurityProperties.getEmailCaptchaMinutes();

		sessionService.set(SECURITY_EMAIL_CAPTCHA + usersId + ":" + email + ":" + identification, code,
				emailCaptchaMinutes, TimeUnit.MINUTES);

		boolean success = false;

		if (javaMailSender == null) {
			throw new CloudRuntimeException(String.format("错误：邮箱：%s 未登录，不可发送邮件！！！", mailProperties.getUsername()));
		}

		Users users = usersService.getById(usersId);
		if (users == null) {
			throw new CloudRuntimeException("未找到用户");
		}

		String username = users.getUsername();
		String nickname = users.getNickname();

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expire = now.plusMinutes(emailCaptchaMinutes);

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(mailProperties.getUsername());
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("邮箱验证");

		// @formatter:off
		simpleMailMessage.setText(String.format("您好 %s (%s)！ \n\n" +
						"您已经请求了邮箱验证，请在页面中输入下方验证码。 \n\n" +
						"验证码：%s \n\n" +
						"标识码：%s \n\n" +
						"如果您没有请求邮箱验证，请忽略这封邮件。 \n\n" +
						"验证码有效期 %s 分钟(%s 过期)",
				username, nickname, code,identification, emailCaptchaMinutes, DateUtils.format(expire, NORM_DATETIME_PATTERN)));
		// @formatter:on

		try {
			javaMailSender.send(simpleMailMessage);
		}
		catch (Exception e) {
			throw new CloudRuntimeException("修改邮箱时，发送邮件异常");
		}
		finally {
			log.info("安全设置：修改新邮箱的验证码时，邮箱：{}，识别码：{}，验证码：{}，有效时间：{} 分钟，发送结果：{}", email, identification, code,
					emailCaptchaMinutes, success);
		}

		return ResponseMap.ok().put("identification", identification);
	}

	/**
	 * 安全设置：修改新邮箱
	 * @param request 请求
	 * @param response 响应
	 * @param securityEmailUpdateBo 新邮箱
	 * @return 返回 发送结果
	 */
	@EncryptAnnotation(Encrypt.AesVersion.V1)
	@ControllerAnnotation(description = "安全设置：修改新邮箱")
	@PreAuthorize("@ant.hasAuthority('user:info')")
	@PostMapping("/email/update")
	public Response<?> emailUpdate(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication, @Valid @RequestBody SecurityEmailUpdateBo securityEmailUpdateBo) {

		Long usersId = SecurityUtils.getUsersId(authentication);

		String email = securityEmailUpdateBo.getEmail();
		String identification = securityEmailUpdateBo.getIdentification();

		String code = sessionService.get(SECURITY_EMAIL_CAPTCHA + usersId + ":" + email + ":" + identification);
		if (securityEmailUpdateBo.getCode().equals(code)) {
			sessionService.remove(SECURITY_EMAIL_CAPTCHA + usersId + ":" + email + ":" + identification);
			boolean update = usersService.updateEmailById(usersId, email);
			return Response.ok();
		}

		return Response.error("验证码无效");
	}

}
