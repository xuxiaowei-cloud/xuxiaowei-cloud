package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表。	原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * 根据 用户名 查询用户信息及权限
     *
     * @param username 用户名
     * @return 返回 用户信息及权限
     */
    Users getByUsername(@Param("username") String username);

    /**
     * 根据 用户名 查询用户信息
     * <p>
     * 条件无逻辑删除的判断
     *
     * @param username 用户名
     * @return 返回 用户信息
     */
    Users getLogicByUsername(@Param("username") String username);

}
