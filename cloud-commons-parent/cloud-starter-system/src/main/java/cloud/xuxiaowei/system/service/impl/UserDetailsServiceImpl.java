package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户详情服务 接口实现
 *
 * @author xuxiaowei
 * @see JdbcDaoImpl
 * @since 0.0.1
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private IUsersService usersService;

    @Autowired
    public void setUsersService(IUsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * 根据 用户 查询用户信息与权限
     *
     * @param username 用户名
     * @return 返回 用户信息与权限
     * @throws UsernameNotFoundException 用户名没有找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = usersService.getByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        String password = users.getPassword();
        boolean enabled = users.getEnabled();
        boolean accountNonExpired = users.getAccountNonExpired();
        boolean credentialsNonExpired = users.getCredentialsNonExpired();
        boolean accountNonLocked = users.getAccountNonLocked();
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Authorities auth : users.getAuthoritiesList()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth.getAuthority());
            authorities.add(authority);
        }

        return new User(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked,
                authorities);
    }

}
