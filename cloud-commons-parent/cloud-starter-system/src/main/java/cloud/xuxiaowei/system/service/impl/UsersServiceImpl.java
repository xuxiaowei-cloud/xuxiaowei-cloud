package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.mapper.UsersMapper;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.vo.UsersVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>
 * 用户表。	原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    /**
     * 根据 用户名 查询用户信息及权限
     * <p>
     * 待加入Redis注解进行数据缓存
     * <p>
     * 与 {@link IUsersService#getUsersVoByUsername(String)} 可以考虑合并成一个接口
     *
     * @param username 用户名
     * @return 返回 用户信息及权限
     */
    @Override
    public Users getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

    /**
     * 根据 用户名 查询用户信息及权限
     *
     * @param username 用户名
     * @return 返回 用户信息及权限
     */
    @Override
    public UsersVo getUsersVoByUsername(String username) {
        Users users = baseMapper.getByUsername(username);
        if (users == null) {
            return null;
        }

        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(users, usersVo);
        Set<String> authorities = new TreeSet<>();
        usersVo.setAuthoritiesList(authorities);

        List<Authorities> authoritiesList = users.getAuthoritiesList();
        if (authoritiesList != null) {
            for (Authorities auth : authoritiesList) {
                String authority = auth.getAuthority();
                authorities.add(authority);
            }
        }

        return usersVo;
    }

}
