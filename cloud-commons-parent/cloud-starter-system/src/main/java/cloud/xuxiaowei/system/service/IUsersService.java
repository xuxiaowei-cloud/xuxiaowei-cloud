package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.bo.ManageUsersPageBo;
import cloud.xuxiaowei.system.bo.UsersSaveBo;
import cloud.xuxiaowei.system.bo.UsersUpdateBo;
import cloud.xuxiaowei.system.bo.UsersUpdateByIdBo;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.vo.UsersVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表。
 * 原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl
 * 原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
public interface IUsersService extends ISuperService<Users> {

	/**
	 * 按用户名加载用户及权限（包含用户组权限）
	 * <p>
	 * 权限为空已剔除
	 * @param username 用户名
	 * @return 返回 用户信息及权限（包含用户组权限）
	 */
	Users loadUserByUsername(String username);

	/**
	 * 根据 用户名 查询用户信息、性别、区域地址及权限
	 * <p>
	 * 待加入Redis注解进行数据缓存
	 * <p>
	 * 与 {@link IUsersService#getUsersVoByUsersId(Long)} 可以考虑合并成一个接口
	 * @param username 用户名
	 * @return 返回 用户名 查询用户信息、性别、区域地址及权限
	 */
	Users getByUsername(String username);

	/**
	 * 根据 用户名 查询用户信息
	 * <p>
	 * 条件无逻辑删除的判断
	 * @param username 用户名
	 * @return 返回 用户信息
	 */
	Users getLogicByUsername(String username);

	/**
	 * 根据 昵称 查询用户信息
	 * @param nickname 昵称
	 * @return 返回 用户信息
	 */
	Users getByNickname(String nickname);

	/**
	 * 根据 手机号 查询用户信息
	 * @param phone 手机号
	 * @return 返回 用户信息
	 */
	Users getByPhone(String phone);

	/**
	 * 根据 昵称 查询用户信息
	 * <p>
	 * 条件无逻辑删除的判断
	 * @param nickname 昵称
	 * @return 返回 用户信息
	 */
	Users getLogicByNickname(String nickname);

	/**
	 * 根据 用户ID 查询用户信息、性别、区域地址及权限
	 * <p>
	 * 由于本租户下的用户能看到所有租户的数据，并且不同租户下可以存在相同的用户名，
	 * <p>
	 * 所以接口不能使用用户名查询数据，应该使用用户主键查询数据，以避免报错
	 * @param usersId 用户ID
	 * @return 返回 用户信息、性别、区域地址及权限
	 */
	UsersVo getUsersVoByUsersId(Long usersId);

	/**
	 * 分页查询用户
	 * @param manageUsersPageBo 管理用户分页参数
	 * @return 返回 分页查询结果
	 */
	IPage<UsersVo> pageByManageUsers(ManageUsersPageBo manageUsersPageBo);

	/**
	 * 根据 用户主键 查询
	 * @param usersId 用户主键
	 * @return 返回 查询结果
	 */
	UsersVo getUsersVoById(Long usersId);

	/**
	 * 保存用户
	 * @param usersSaveBo 用户
	 * @return 返回 保存结果
	 */
	boolean saveUsersSaveBo(UsersSaveBo usersSaveBo);

	/**
	 * 更新用户
	 * @param usersUpdateByIdBo 用户
	 * @return 返回 更新结果
	 */
	boolean updateByUsersUpdateByIdBo(UsersUpdateByIdBo usersUpdateByIdBo);

	/**
	 * 根据当前操作人更新用户
	 * @param usersUpdateBo 用户表
	 * @return 返回 更新结果
	 */
	boolean updateByUsersUpdateBo(UsersUpdateBo usersUpdateBo);

	/**
	 * 获取不是某个用户是否存在指定邮箱的用户
	 * @param usersId 用户ID
	 * @param email 邮箱
	 * @param deleted 是否逻辑删除
	 * @return 返回 用户信息
	 */
	List<Users> listByIdNotUsersIdAndEmail(Long usersId, String email, Boolean deleted);

	/**
	 * 获取不是某个用户是否存在指定昵称的用户
	 * @param usersId 用户ID
	 * @param nickname 昵称
	 * @param deleted 是否逻辑删除
	 * @return 返回 用户信息
	 */
	List<Users> listByIdNotUsersIdAndNickname(Long usersId, String nickname, Boolean deleted);

	/**
	 * 根据 邮箱 查询用户
	 * @param email 邮箱
	 * @return 返回 查询结果
	 */
	Users getByEmail(String email);

	/**
	 * 根据 邮箱 查询用户信息
	 * <p>
	 * 条件无逻辑删除的判断
	 * @param email 邮箱
	 * @return 返回 用户信息
	 */
	Users getLogicByEmail(String email);

	/**
	 * 根据主键更新密码
	 * @param usersId 主键
	 * @param password 密码
	 * @param rsaPrivateKeyBase64 RSA 私钥
	 * @return 返回 更新结果
	 */
	boolean updatePasswordById(Long usersId, String password, String rsaPrivateKeyBase64);

	/**
	 * 根据主键更新手机号
	 * @param usersId 主键
	 * @param phone 手机号
	 * @return 返回 更新结果
	 */
	boolean updatePhoneById(Long usersId, String phone);

	/**
	 * 根据主键更新邮箱
	 * @param usersId 主键
	 * @param email 邮箱
	 * @return 返回 更新结果
	 */
	boolean updateEmailById(Long usersId, String email);

}
