package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.system.annotation.EncryptAnnotation;
import cloud.xuxiaowei.system.bo.*;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.service.SessionService;
import cloud.xuxiaowei.system.vo.UsersVo;
import cloud.xuxiaowei.utils.*;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.utils.map.ResponseMap;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static cloud.xuxiaowei.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * 用户
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
public class UserRestController {

	private SessionService sessionService;

	private IUsersService usersService;

	private JavaMailSender javaMailSender;

	private MailProperties mailProperties;

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

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

	/**
	 * 用户信息
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "用户信息")
	@PreAuthorize("hasAuthority('user_info')")
	@RequestMapping("/info")
	public Response<?> info(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		String name = authentication.getName();
		UsersVo usersVo = usersService.getUsersVoByUsername(name);
		if (usersVo == null) {
			return Response.error();
		}

		return Response.ok(usersVo);
	}

	/**
	 * 根据当前操作人更新用户
	 * @param request 请求
	 * @param response 响应
	 * @param usersUpdateBo 用户
	 * @return 返回 更新结果
	 */
	@ControllerAnnotation(description = "根据当前操作人更新用户")
	@PreAuthorize("hasAuthority('user_info')")
	@RequestMapping("/update")
	public Response<?> update(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody UsersUpdateBo usersUpdateBo) {

		boolean update = usersService.updateByUsersUpdateBo(usersUpdateBo);

		return Response.ok(update);
	}

	/**
	 * 用户权限
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "用户权限")
	@PreAuthorize("hasAuthority('user_authorities')")
	@RequestMapping("/authorities")
	public Response<?> authorities(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		return Response.ok(authorities);
	}

	/**
	 * 用户详情
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "用户详情")
	@PreAuthorize("hasAuthority('user_details')")
	@RequestMapping("/details")
	public Response<?> details(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {

		Object details = authentication.getDetails();

		return Response.ok(details);
	}

	/**
	 * 分页查询用户
	 * @param request 请求
	 * @param response 响应
	 * @param manageUsersPageBo 用户分页参数
	 * @return 返回 分页查询结果
	 */
	@EncryptAnnotation(Encrypt.AesVersion.V0)
	@ControllerAnnotation(description = "分页查询用户")
	@PreAuthorize("hasAuthority('manage_user_read')")
	@RequestMapping("/page")
	public Response<?> page(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ManageUsersPageBo manageUsersPageBo) {

		IPage<UsersVo> page = usersService.pageByManageUsers(manageUsersPageBo);

		return Response.ok(page);
	}

	/**
	 * 根据 用户主键 删除 用户
	 * @param request 请求
	 * @param response 响应
	 * @param usersId 用户主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 用户主键 删除 用户")
	@PreAuthorize("hasAuthority('manage_user_delete')")
	@RequestMapping("/removeById/{usersId}")
	public Response<?> removeById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("usersId") Long usersId) {

		boolean removeById = usersService.removeById(usersId);

		return Response.ok(removeById);
	}

	/**
	 * 根据 用户主键 批量删除 用户
	 * @param request 请求
	 * @param response 响应
	 * @param usersIds 用户主键
	 * @return 返回 删除结果
	 */
	@ControllerAnnotation(description = "根据 用户主键 批量删除 用户")
	@PreAuthorize("hasAuthority('manage_user_delete')")
	@RequestMapping("/removeByIds")
	public Response<?> removeByIds(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<Long> usersIds) {

		AssertUtils.sizeNonNull(usersIds, 1, 50, "非法数据长度");

		boolean removeByIds = usersService.removeByIds(usersIds);

		return Response.ok(removeByIds);
	}

	/**
	 * 根据 用户主键 查询用户
	 * @param request 请求
	 * @param response 响应
	 * @param usersId 用户主键
	 * @return 返回 查询结果
	 */
	@ControllerAnnotation(description = "根据 用户主键 查询用户")
	@PreAuthorize("hasAuthority('manage_user_read')")
	@RequestMapping("/getById/{usersId}")
	public Response<?> getById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("usersId") Long usersId) {

		UsersVo usersVo = usersService.getUsersVoById(usersId);

		return Response.ok(usersVo);
	}

	/**
	 * 保存用户
	 * @param request 请求
	 * @param response 响应
	 * @param usersSaveBo 用户
	 * @return 返回 保存结果
	 */
	@ControllerAnnotation(description = "保存用户")
	@PreAuthorize("hasAuthority('manage_user_add')")
	@RequestMapping("/save")
	public Response<?> save(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody UsersSaveBo usersSaveBo) {

		boolean save = usersService.saveUsersSaveBo(usersSaveBo);

		return Response.ok(save);
	}

	/**
	 * 根据 用户主键 更新用户
	 * @param request 请求
	 * @param response 响应
	 * @param usersUpdateByIdBo 用户
	 * @return 返回 更新结果
	 */
	@ControllerAnnotation(description = "根据 用户主键 更新用户")
	@PreAuthorize("hasAuthority('manage_user_edit')")
	@RequestMapping("/updateById")
	public Response<?> updateById(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody UsersUpdateByIdBo usersUpdateByIdBo) {

		boolean update = usersService.updateByUsersUpdateByIdBo(usersUpdateByIdBo);

		return Response.ok(update);
	}

	/**
	 * 获取用户识别码
	 * <p>
	 * 生成RSA密钥对
	 * <p>
	 * 返回识别码
	 * <p>
	 * 返回识别码RSA公钥
	 * <p>
	 * RSA私钥保存到Redis中
	 * <p>
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 更新结果
	 */
	@ControllerAnnotation(description = "获取用户识别码")
	@PreAuthorize("hasAuthority('user_info')")
	@RequestMapping("/code/rsa")
	public Response<?> code(HttpServletRequest request, HttpServletResponse response) {

		RSA generate = new RSA();

		// 获取私钥
		String privateKey = generate.getPrivateKeyBase64();
		// 获取公钥
		String publicKey = generate.getPublicKeyBase64();
		// 识别码
		String code = RandomStringUtils.random(6, Joiner.on("").join(Constant.UPPER_CASE_LIST));

		sessionService.setAttr(Constant.PRIVATE_KEY + ":" + code, privateKey, 1, TimeUnit.HOURS);

		return ResponseMap.ok().put("code", code).put("publicKey", publicKey);
	}

	/**
	 * 忘记密码
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 更新结果
	 */
	@ControllerAnnotation(description = "忘记密码")
	@RequestMapping("/forget")
	public Response<?> forget(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody ForgetBo forgetBo) {

		String username = forgetBo.getUsername();

		Users byUsername = usersService.getByUsername(username);
		if (byUsername != null) {
			Long usersId = byUsername.getUsersId();
			String nickname = byUsername.getNickname();
			String email = byUsername.getEmail();
			String phone = byUsername.getPhone();
			if (StringUtils.hasText(email)) {

				email(usersId, email, username, nickname, 6, UUID.randomUUID().toString());

				return ResponseMap.ok(String.format("我们向邮箱 %s 发送了一封含有重置密码链接的邮件。请登录邮箱查看，如长时间没有收到邮件，请检查你的垃圾邮件文件夹。",
						DesensitizedUtil.email(email))).put("type", "email");
			}
			else if (StringUtils.hasText(phone)) {
				return ResponseMap
						.ok(String.format("一条包含验证码的信息已发送至你的 手机 %s，请输入验证码以继续", DesensitizedUtil.mobilePhone(phone)))
						.put("usersId", byUsername.getUsersId()).put("type", "phone");
			}
			else {
				return ResponseMap.error("账户未绑定手机号/邮箱");
			}
		}

		Users byEmail = usersService.getByEmail(username);
		if (byEmail != null) {
			Long usersId = byEmail.getUsersId();
			String nickname = byEmail.getNickname();
			String email = byEmail.getEmail();

			email(usersId, email, username, nickname, 6, UUID.randomUUID().toString());

			return ResponseMap.ok(String.format("我们向邮箱 %s 发送了一封含有重置密码链接的邮件。请登录邮箱查看，如长时间没有收到邮件，请检查你的垃圾邮件文件夹。",
					DesensitizedUtil.email(email))).put("type", "email");
		}

		Users byPhone = usersService.getByPhone(username);
		if (byPhone != null) {
			String phone = byPhone.getPhone();
			return ResponseMap
					.ok(String.format("一条包含验证码的信息已发送至你的 手机 %s，请输入验证码以继续", DesensitizedUtil.mobilePhone(phone)))
					.put("usersId", byPhone.getUsersId()).put("type", "phone");
		}

		return Response.error("未找到用户");
	}

	private void email(Long usersId, String email, String username, String nickname, int hours, String token) {
		if (javaMailSender == null) {
			throw new CloudRuntimeException(String.format("错误：邮箱：%s 未登录，不可发送邮件！！！", mailProperties.getUsername()));
		}

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expire = now.plusHours(hours);

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(mailProperties.getUsername());
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("重置密码");

		// @formatter:off
		simpleMailMessage.setText(String.format("您好 %s (%s)！ \n\n" +
				"您已经请求了重置密码，可以点击下面的链接来重置密码。 \n\n" +
				"http://passport.example.xuxiaowei.cloud:1411/#/password?usersId=%s&reset_password_token=%s \n\n" +
				"如果您没有请求重置密码，请忽略这封邮件。 \n\n" +
				"在您点击上面链接修改密码之前，您的密码将会保持不变。 \n\n" +
				"链接有效期 %s 小时(%s 过期)",
				username, nickname, usersId, token, hours, DateUtils.format(expire, DEFAULT_DATE_TIME_FORMAT)));
		// @formatter:on

		javaMailSender.send(simpleMailMessage);
	}

}
