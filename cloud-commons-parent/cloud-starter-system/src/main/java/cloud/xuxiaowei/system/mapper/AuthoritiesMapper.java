package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.Authorities;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 权限表。	原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
public interface AuthoritiesMapper extends BaseMapper<Authorities> {

    /**
     * 根据 用户名、权限 删除
     *
     * @param username      用户名
     * @param authorityList 权限
     * @return 返回 删除结果
     */
    int deleteByUsernameAndAuthoritiesList(@Param("username") String username, @Param("authorityList") Set<String> authorityList);

}
