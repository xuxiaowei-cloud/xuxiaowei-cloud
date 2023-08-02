package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.bo.*;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.mapper.UsersMapper;
import cloud.xuxiaowei.system.service.IAuthorityService;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.service.SessionService;
import cloud.xuxiaowei.system.vo.AuthorityVo;
import cloud.xuxiaowei.system.vo.UsersVo;
import cloud.xuxiaowei.utils.Constants;
import cloud.xuxiaowei.utils.SecurityUtils;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.validation.utils.ValidationUtils;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表。
 * 原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl
 * 原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
@Slf4j
@Service
public class UsersServiceImpl extends SuperServiceImpl<UsersMapper, Users> implements IUsersService {

	private IAuthorityService authorityService;

	private SessionService sessionService;

	private CloudSecurityProperties cloudSecurityProperties;

	@Autowired
	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	@Autowired
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	/**
	 * 按用户名加载用户及权限（包含用户组权限）
	 * <p>
	 * 权限为空已剔除
	 * @param username 用户名
	 * @return 返回 用户信息及权限（包含用户组权限）
	 */
	@Override
	public Users loadUserByUsername(String username) {
		Users users = baseMapper.loadUserByUsername(username);
		authorities(users);
		return users;
	}

	private void authorities(Users users) {
		if (users != null) {
			// 去除 authority 为空的情况
			List<Authorities> list = new ArrayList<>();
			List<Authorities> authoritiesList = users.getAuthoritiesList();
			for (Authorities authorities : authoritiesList) {
				String authority = authorities.getAuthority();
				if (StringUtils.hasText(authority)) {
					list.add(authorities);
				}
			}
			users.setAuthoritiesList(list);
		}
	}

	/**
	 * 根据 用户名 查询用户信息、性别、区域地址及权限
	 * <p>
	 * 待加入Redis注解进行数据缓存
	 * <p>
	 * 与 {@link IUsersService#getUsersVoByUsersId(Long)} 可以考虑合并成一个接口
	 * @param username 用户名
	 * @return 返回 用户名 查询用户信息、性别、区域地址及权限
	 */
	@Override
	public Users getByUsername(String username) {
		return baseMapper.getByUsername(username);
	}

	/**
	 * 根据 用户名 查询用户信息
	 * <p>
	 * 条件无逻辑删除的判断
	 * @param username 用户名
	 * @return 返回 用户信息
	 */
	@Override
	public Users getLogicByUsername(String username) {
		return baseMapper.getLogicByUsername(username);
	}

	/**
	 * 根据 昵称 查询用户信息
	 * @param nickname 昵称
	 * @return 返回 用户信息
	 */
	@Override
	public Users getByNickname(String nickname) {
		QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("nickname", nickname);
		return getOne(queryWrapper);
	}

	/**
	 * 根据 手机号 查询用户信息
	 * @param phone 手机号
	 * @return 返回 用户信息
	 */
	@Override
	public Users getByPhone(String phone) {
		QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", phone);
		return getOne(queryWrapper);
	}

	/**
	 * 根据 昵称 查询用户信息
	 * <p>
	 * 条件无逻辑删除的判断
	 * @param nickname 昵称
	 * @return 返回 用户信息
	 */
	@Override
	public Users getLogicByNickname(String nickname) {
		return baseMapper.getLogicByNickname(nickname);
	}

	/**
	 * 根据 用户ID 查询用户信息、性别、区域地址及权限
	 * <p>
	 * 由于本租户下的用户能看到所有租户的数据，并且不同租户下可以存在相同的用户名，
	 * <p>
	 * 所以接口不能使用用户名查询数据，应该使用用户主键查询数据，以避免报错
	 * @param usersId 用户ID
	 * @return 返回 用户信息、性别、区域地址及权限
	 */
	@Override
	public UsersVo getUsersVoByUsersId(Long usersId) {
		Users users = baseMapper.getByUsersId(usersId);
		return usersToUsersVo(users);
	}

	/**
	 * 分页查询用户
	 * @param manageUsersPageBo 管理用户分页参数
	 * @return 返回 分页查询结果
	 */
	@Override
	public IPage<UsersVo> pageByManageUsers(ManageUsersPageBo manageUsersPageBo) {
		QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
		Long current = manageUsersPageBo.getCurrent();
		Long size = manageUsersPageBo.getSize();

		Long usersId = manageUsersPageBo.getUsersId();
		String username = manageUsersPageBo.getUsername();
		String email = manageUsersPageBo.getEmail();
		Boolean emailValid = manageUsersPageBo.getEmailValid();
		String nickname = manageUsersPageBo.getNickname();

		if (usersId != null) {
			queryWrapper.eq("users_id", usersId);
		}
		if (StringUtils.hasText(username)) {
			queryWrapper.eq("username", username);
		}
		if (StringUtils.hasText(email)) {
			queryWrapper.eq("email", email);
		}
		if (emailValid != null) {
			queryWrapper.eq("email_valid", emailValid);
		}
		if (StringUtils.hasText(nickname)) {
			queryWrapper.eq("nickname", nickname);
		}

		IPage<Users> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);
		page(page, queryWrapper);

		Page<UsersVo> usersVoPage = new Page<>();
		BeanUtils.copyProperties(page, usersVoPage);

		List<UsersVo> usersVoList = new ArrayList<>();
		usersVoPage.setRecords(usersVoList);

		List<Users> records = page.getRecords();
		for (Users users : records) {
			UsersVo usersVo = new UsersVo();
			BeanUtils.copyProperties(users, usersVo);

			Set<AuthorityVo> authorityList = authorityService.listByUsersId(usersVo.getUsersId());
			usersVo.setAuthorityList(authorityList);

			usersVoList.add(usersVo);
		}

		return usersVoPage;
	}

	/**
	 * 根据 用户主键 查询
	 * @param usersId 用户主键
	 * @return 返回 查询结果
	 */
	@Override
	public UsersVo getUsersVoById(Long usersId) {
		Users users = baseMapper.getByUsersId(usersId);
		return usersToUsersVo(users);
	}

	private UsersVo usersToUsersVo(Users users) {
		if (users == null) {
			return null;
		}
		UsersVo usersVo = new UsersVo();
		BeanUtils.copyProperties(users, usersVo);

		Set<AuthorityVo> authorityVoSet = new HashSet<>();
		usersVo.setAuthorityList(authorityVoSet);
		for (Authorities auth : users.getAuthoritiesList()) {
			AuthorityVo authorityVo = new AuthorityVo();
			authorityVo.setAuthority(auth.getAuthority());
			authorityVo.setExplain(auth.getExplain());
			authorityVoSet.add(authorityVo);
		}
		return usersVo;
	}

	/**
	 * 保存用户
	 * @param usersSaveBo 用户
	 * @return 返回 保存结果
	 */
	@Override
	public boolean saveUsersSaveBo(UsersSaveBo usersSaveBo) {

		String passwordDecrypt = passwordDecrypt(usersSaveBo.getCode(), usersSaveBo.getPassword());

		if (!StringUtils.hasText(passwordDecrypt)) {
			throw new CloudRuntimeException("密码 不能为空");
		}

		Users users = new Users();
		BeanUtils.copyProperties(usersSaveBo, users);

		users.setPassword(passwordDecrypt);

		// 用户密码加密
		encode(users);

		return save(users);
	}

	private String passwordDecrypt(String code, String password) {
		if (password == null || Boolean.FALSE.toString().equals(password)) {
			return null;
		}
		String privateKey = sessionService.getAttr(Constants.PRIVATE_KEY + ":" + code);

		String passwordDecrypt;
		if (StringUtils.hasText(privateKey)) {
			RSA rsa = new RSA(privateKey, null);

			passwordDecrypt = rsa.decryptStr(password, KeyType.PrivateKey);
			ValidationUtils.validate(new PasswordBo(passwordDecrypt));
		}
		else {
			throw new CloudRuntimeException("未找到RSA私钥，请刷新页面后重试");
		}
		return passwordDecrypt;
	}

	/**
	 * 更新用户
	 * @param usersUpdateByIdBo 用户
	 * @return 返回 更新结果
	 */
	@Override
	public boolean updateByUsersUpdateByIdBo(UsersUpdateByIdBo usersUpdateByIdBo) {

		String passwordDecrypt = passwordDecrypt(usersUpdateByIdBo.getCode(), usersUpdateByIdBo.getPassword());

		Users users = new Users();
		BeanUtils.copyProperties(usersUpdateByIdBo, users);

		Long usersId = usersUpdateByIdBo.getUsersId();

		String email = usersUpdateByIdBo.getEmail();
		// 邮箱，唯一键：uk__users__email
		List<Users> usersEmailList = listByIdNotUsersIdAndEmail(usersId, email, null);
		if (usersEmailList.size() > 0) {
			throw new CloudRuntimeException("已存在此邮箱：" + email);
		}

		// 昵称，不能为空，唯一键：uk__users__nickname
		String nickname = usersUpdateByIdBo.getNickname();
		List<Users> usersNicknameList = listByIdNotUsersIdAndNickname(usersId, nickname, null);
		if (usersNicknameList.size() > 0) {
			throw new CloudRuntimeException("已存在此昵称：" + nickname);
		}

		users.setPassword(passwordDecrypt);

		// 用户密码加密
		encode(users);

		return updateById(users);
	}

	/**
	 * 根据当前操作人更新用户
	 * @param usersUpdateBo 用户表
	 * @return 返回 更新结果
	 */
	@Override
	public boolean updateByUsersUpdateBo(UsersUpdateBo usersUpdateBo) {
		Long usersId = SecurityUtils.getUsersId();
		Users users = new Users();
		BeanUtils.copyProperties(usersUpdateBo, users);
		users.setUsersId(usersId);
		return updateById(users);
	}

	/**
	 * 获取不是某个用户是否存在指定邮箱的用户
	 * @param usersId 用户ID
	 * @param email 邮箱
	 * @param deleted 是否逻辑删除
	 * @return 返回 用户信息
	 */
	@Override
	public List<Users> listByIdNotUsersIdAndEmail(Long usersId, String email, Boolean deleted) {
		return baseMapper.listByIdNotUsersIdAndEmail(usersId, email, deleted);
	}

	/**
	 * 获取不是某个用户是否存在指定昵称的用户
	 * @param usersId 用户ID
	 * @param nickname 昵称
	 * @param deleted 是否逻辑删除
	 * @return 返回 用户信息
	 */
	public List<Users> listByIdNotUsersIdAndNickname(Long usersId, String nickname, Boolean deleted) {
		return baseMapper.listByIdNotUsersIdAndNickname(usersId, nickname, deleted);
	}

	/**
	 * 根据 邮箱 查询用户
	 * @param email 邮箱
	 * @return 返回 查询结果
	 */
	@Override
	public Users getByEmail(String email) {
		QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("email", email);
		return getOne(queryWrapper);
	}

	/**
	 * 根据 邮箱 查询用户信息
	 * <p>
	 * 条件无逻辑删除的判断
	 * @param email 邮箱
	 * @return 返回 用户信息
	 */
	@Override
	public Users getLogicByEmail(String email) {
		return baseMapper.getLogicByEmail(email);
	}

	/**
	 * 根据主键更新密码
	 * @param usersId 主键
	 * @param password 密码
	 * @param rsaPrivateKeyBase64 RSA 私钥
	 * @return 返回 更新结果
	 */
	@Override
	public boolean updatePasswordById(Long usersId, String password, String rsaPrivateKeyBase64) {

		String passwordDecrypt;

		if (cloudSecurityProperties.isEnabledRsa()) {
			log.info("更新密码时启用了RSA对密码进行解密");
			if (StringUtils.hasText(rsaPrivateKeyBase64)) {
				RSA rsa = new RSA(rsaPrivateKeyBase64, null);

				passwordDecrypt = rsa.decryptStr(password, KeyType.PrivateKey);
			}
			else {
				throw new CloudRuntimeException("未找到RSA私钥，请刷新页面后重试");
			}
		}
		else {
			log.info("更新密码时未启用RSA对密码进行解密");
			passwordDecrypt = password;
		}

		PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("password", delegatingPasswordEncoder.encode(passwordDecrypt));
		return update(updateWrapper);
	}

	/**
	 * 根据主键更新手机号
	 * @param usersId 主键
	 * @param phone 手机号
	 * @return 返回 更新结果
	 */
	@Override
	public boolean updatePhoneById(Long usersId, String phone) {
		UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("phone", phone);
		return update(updateWrapper);
	}

	/**
	 * 根据主键更新邮箱
	 * @param usersId 主键
	 * @param email 邮箱
	 * @return 返回 更新结果
	 */
	@Override
	public boolean updateEmailById(Long usersId, String email) {
		UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("users_id", usersId);
		updateWrapper.set("email", email);
		return update(updateWrapper);
	}

	/**
	 * 用户密码加密
	 * @param users 用户
	 */
	private void encode(Users users) {
		// 密码加密后储存
		String password = users.getPassword();
		if (StringUtils.hasText(password)) {
			PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String encode = delegatingPasswordEncoder.encode(password);
			users.setPassword(encode);
		}
		else {
			users.setPassword(null);
		}
	}

}
