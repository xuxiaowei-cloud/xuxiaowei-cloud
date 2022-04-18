package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.vo.UsersVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表。	原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
public interface IUsersService extends IService<Users> {

    /**
     * 根据 用户名 查询用户信息及权限
     *
     * @param username 用户名
     * @return 返回 用户信息及权限
     */
    Users getByUsername(String username);

    /**
     * 根据 用户名 查询用户信息及权限
     *
     * @param username 用户名
     * @return 返回 用户信息及权限
     */
    UsersVo getUsersVoByUsername(String username);

}
