package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Constants;
import cloud.xuxiaowei.utils.MdcConstants;
import cloud.xuxiaowei.utils.exception.login.LoginException;
import cloud.xuxiaowei.utils.exception.login.LoginUsernameNotFoundException;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

	private HttpServletRequest request;

	private IUsersService usersService;

	private CloudSecurityProperties cloudSecurityProperties;

	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	/**
	 * 根据 用户 查询用户信息与权限
	 * <p>
	 * 请求参数中不存在租户ID时，使用默认值：1
	 * @param username 用户名
	 * @return 返回 用户信息与权限
	 * @throws UsernameNotFoundException 用户名没有找到异常
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String tenantId = request.getParameter(Constants.TENANT_ID);

		// 设置临时租户ID
		MDC.put(MdcConstants.TMP_TENANT_ID, tenantId);

		Users users = usersService.loadUserByUsername(username);
		if (users == null) {
			throw new LoginUsernameNotFoundException("用户名不存在");
		}

		String password = users.getPassword();
		Boolean enabled = users.getEnabled();
		Boolean accountNonExpired = users.getAccountNonExpired();
		Boolean credentialsNonExpired = users.getCredentialsNonExpired();
		Boolean accountNonLocked = users.getAccountNonLocked();
		List<GrantedAuthority> authorities = new ArrayList<>();

		List<Authorities> authoritiesList = users.getAuthoritiesList();

		boolean allowEmptyAuthorities = cloudSecurityProperties.isAllowEmptyAuthorities();
		if (!allowEmptyAuthorities && authoritiesList.size() == 0) {
			throw new LoginException(CodeEnums.A10011.code, CodeEnums.A10011.msg);
		}

		for (Authorities auth : authoritiesList) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth.getAuthority());
			authorities.add(authority);
		}

		return new User(username, password, enabled != null && enabled, accountNonExpired != null && accountNonExpired,
				credentialsNonExpired != null && credentialsNonExpired, accountNonLocked != null && accountNonLocked,
				authorities);
	}

}
