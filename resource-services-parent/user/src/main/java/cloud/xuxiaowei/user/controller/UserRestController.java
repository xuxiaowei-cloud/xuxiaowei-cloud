package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.system.annotation.EncryptAnnotation;
import cloud.xuxiaowei.system.bo.ManageUsersPageBo;
import cloud.xuxiaowei.system.bo.UsersSaveBo;
import cloud.xuxiaowei.system.bo.UsersUpdateBo;
import cloud.xuxiaowei.system.bo.UsersUpdateByIdBo;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.service.SessionService;
import cloud.xuxiaowei.system.vo.UsersVo;
import cloud.xuxiaowei.utils.AssertUtils;
import cloud.xuxiaowei.utils.Constants;
import cloud.xuxiaowei.utils.Encrypt;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.map.ResponseMap;
import cn.hutool.crypto.asymmetric.RSA;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	/**
	 * 用户信息
	 * @param request 请求
	 * @param response 响应
	 * @return 返回 结果
	 */
	@ControllerAnnotation(description = "用户信息")
	@PreAuthorize("@ant.hasAuthority('user:info')")
	@PostMapping("/info")
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
	@PreAuthorize("@ant.hasAuthority('user:info')")
	@PostMapping("/update")
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
	@PreAuthorize("@ant.hasAuthority('user:authorities')")
	@PostMapping("/authorities")
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
	@PreAuthorize("@ant.hasAuthority('user:details')")
	@PostMapping("/details")
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
	@PreAuthorize("@ant.hasAuthority('manage_user:read')")
	@PostMapping("/page")
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
	@PreAuthorize("@ant.hasAuthority('manage_user:delete')")
	@PostMapping("/removeById/{usersId}")
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
	@PreAuthorize("@ant.hasAuthority('manage_user:delete')")
	@PostMapping("/removeByIds")
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
	@PreAuthorize("@ant.hasAuthority('manage_user:read')")
	@PostMapping("/getById/{usersId}")
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
	@PreAuthorize("@ant.hasAuthority('manage_user:add')")
	@PostMapping("/save")
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
	@PreAuthorize("@ant.hasAuthority('manage_user:edit')")
	@PostMapping("/updateById")
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
	@PreAuthorize("@ant.hasAuthority('user:info')")
	@PostMapping("/code/rsa")
	public Response<?> code(HttpServletRequest request, HttpServletResponse response) {

		RSA generate = new RSA();

		// 获取私钥
		String privateKey = generate.getPrivateKeyBase64();
		// 获取公钥
		String publicKey = generate.getPublicKeyBase64();
		// 识别码
		String code = RandomStringUtils.random(6, Joiner.on("").join(Constants.UPPER_CASE_LIST));

		sessionService.setAttr(Constants.PRIVATE_KEY + ":" + code, privateKey, 1, TimeUnit.HOURS);

		return ResponseMap.ok().put("code", code).put("publicKey", publicKey);
	}

}
