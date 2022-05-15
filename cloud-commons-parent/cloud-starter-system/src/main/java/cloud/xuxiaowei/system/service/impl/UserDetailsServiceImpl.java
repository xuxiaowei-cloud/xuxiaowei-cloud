package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.entity.WxMaUsers;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.service.IWxMaUsersService;
import cloud.xuxiaowei.utils.ClientType;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.exception.login.LoginUsernameNotFoundException;
import cloud.xuxiaowei.utils.exception.login.LoginWechatUsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 用户详情服务 接口实现
 *
 * @author xuxiaowei
 * @see JdbcDaoImpl
 * @since 0.0.1
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private HttpServletRequest request;

    private IUsersService usersService;

    private IWxMaUsersService wxMaUsersService;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    public void setUsersService(IUsersService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    public void setWxMaUsersService(IWxMaUsersService wxMaUsersService) {
        this.wxMaUsersService = wxMaUsersService;
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

        // 在 grantType 为 password 时，对密码进行处理后才能比较，这样更安全
        String grantType = request.getParameter(OAuth2Utils.GRANT_TYPE);
        // 客户端类型
        String clientType = request.getParameter(Constant.CLIENT_TYPE);
        // 微信小程序appid
        String appid = request.getParameter(Constant.APPID);

        // 微信客户端
        if (ClientType.WECHAT_APPLET.grantType.equals(grantType) && ClientType.WECHAT_APPLET.clientType.equals(clientType)) {
            WxMaUsers wxMaUsers = wxMaUsersService.getByAppidAndOpenid(appid, username);
            if (wxMaUsers == null) {
                throw new LoginWechatUsernameNotFoundException("用户名不存在");
            }

            List<GrantedAuthority> authorities = new ArrayList<>();
            // 暂时仅授权微信权限
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("wechat");
            authorities.add(authority);

            return new User(username, UUID.randomUUID().toString(), authorities);
        }

        Users users = usersService.getByUsername(username);
        if (users == null) {
            throw new LoginUsernameNotFoundException("用户名不存在");
        }

        String password = users.getPassword();
        Boolean enabled = users.getEnabled();
        Boolean accountNonExpired = users.getAccountNonExpired();
        Boolean credentialsNonExpired = users.getCredentialsNonExpired();
        Boolean accountNonLocked = users.getAccountNonLocked();
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Authorities auth : users.getAuthoritiesList()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth.getAuthority());
            authorities.add(authority);
        }

        return new User(username, password, enabled != null && enabled, accountNonExpired != null && accountNonExpired, credentialsNonExpired != null && credentialsNonExpired, accountNonLocked != null && accountNonLocked, authorities);
    }

}
