package cloud.xuxiaowei.oauth2.matcher;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * 权限使用 {@link AntPathMatcher} 匹配
 *
 * @see SecurityExpressionRoot
 * @author xuxiaowei
 * @since 0.0.1
 */
@Component("ant")
public class AuthorizeAntPathMatcher {

	private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

	/**
	 * 权限使用 {@link AntPathMatcher} 匹配
	 * @param authority 权限
	 * @return 返回匹配的结果
	 */
	public boolean hasAuthority(String authority) {
		Collection<? extends GrantedAuthority> grantedAuthorities = grantedAuthorities();
		if (grantedAuthorities == null) {
			return false;
		}
		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			String auth = grantedAuthority.getAuthority();
			boolean match = ANT_PATH_MATCHER.match(auth, authority);
			if (match) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 权限使用 {@link AntPathMatcher} 匹配
	 * @param authorities 权限
	 * @return 返回匹配的结果
	 */
	public boolean hasAnyAuthority(String... authorities) {
		Collection<? extends GrantedAuthority> grantedAuthorities = grantedAuthorities();
		if (grantedAuthorities == null) {
			return false;
		}
		for (String authority : authorities) {
			for (GrantedAuthority grantedAuthority : grantedAuthorities) {
				String auth = grantedAuthority.getAuthority();
				boolean match = ANT_PATH_MATCHER.match(auth, authority);
				if (match) {
					return true;
				}
			}
		}
		return false;
	}

	private Collection<? extends GrantedAuthority> grantedAuthorities() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null) {
			return null;
		}
		return authentication.getAuthorities();
	}

}
