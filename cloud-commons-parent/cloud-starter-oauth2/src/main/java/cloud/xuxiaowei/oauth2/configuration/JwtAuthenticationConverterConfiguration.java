package cloud.xuxiaowei.oauth2.configuration;

import cloud.xuxiaowei.utils.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import javax.annotation.security.RolesAllowed;

/**
 * 支持 {@link EnableGlobalMethodSecurity}、{@link PreAuthorize}
 * <p>
 * {@link RolesAllowed}、{@link Secured}：使用 {@link GrantedAuthorityDefaults} 的 {@link Bean}
 * 来设置注解的角色前缀（分别需要开启
 * {@link EnableGlobalMethodSecurity#jsr250Enabled()}、{@link EnableGlobalMethodSecurity#securedEnabled()}）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtAuthenticationConverterConfiguration {

	/**
	 * 支持 {@link EnableGlobalMethodSecurity}、{@link PreAuthorize} 的 {@link Bean}
	 * @return 支持 {@link EnableGlobalMethodSecurity}、{@link PreAuthorize}
	 * @see <a href=
	 * "https://docs.spring.io/spring-security/reference/6.0.0-M3/servlet/oauth2/resource-server/jwt.html#oauth2resourceserver-jwt-authorization-extraction">手动提取权限-6.0.0-M3</a>
	 */
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		// 设置Token中获取权限数据的声明名称
		grantedAuthoritiesConverter.setAuthoritiesClaimName(Constant.AUTHORITIES);
		// 设置成无前缀
		grantedAuthoritiesConverter.setAuthorityPrefix("");
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

}
