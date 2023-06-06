package cloud.xuxiaowei.masterdata.configuration;

import cloud.xuxiaowei.core.properties.CloudJwkKeyProperties;
import org.springdoc.core.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.security.interfaces.RSAPublicKey;

import static cloud.xuxiaowei.oauth2.matcher.CsrfRequestMatcher.CSRF_REQUEST_MATCHER_BEAN_NAME;

/**
 * 资源服务配置
 *
 * @author xuxiaowei
 * @see <a href="http://127.0.0.1:1701/sns/userinfo?access_token=">访问资源</a>
 * @see <a href=
 * "https://docs.spring.io/spring-security/reference/6.0.0-M3/servlet/oauth2/resource-server/jwt.html#oauth2resourceserver-jwt-authorization-extraction">手动提取权限-6.0.0-M3</a>
 * @since 0.0.1
 */
@Configuration
public class ResourceServerConfiguration {

	private AccessDeniedHandler accessDeniedHandler;

	private AuthenticationEntryPoint authenticationEntryPoint;

	private CloudJwkKeyProperties cloudJwkKeyProperties;

	private RequestMatcher csrfRequestMatcher;

	@Autowired
	public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
		this.accessDeniedHandler = accessDeniedHandler;
	}

	@Autowired
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Autowired
	public void setCloudJwkKeyProperties(CloudJwkKeyProperties cloudJwkKeyProperties) {
		this.cloudJwkKeyProperties = cloudJwkKeyProperties;
	}

	@Autowired
	@Qualifier(CSRF_REQUEST_MATCHER_BEAN_NAME)
	public void setCsrfRequestMatcher(RequestMatcher csrfRequestMatcher) {
		this.csrfRequestMatcher = csrfRequestMatcher;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> {
			web.ignoring().antMatchers("/favicon.ico");
			web.ignoring().antMatchers(Constants.SWAGGER_UI_PREFIX + "/**");
			web.ignoring().antMatchers(Constants.DEFAULT_API_DOCS_URL + "/**");
		};
	}

	/**
	 * @see <a href=
	 * "https://docs.spring.io/spring-security/reference/servlet/authentication/index.html">用于身份验证</a>
	 * 的 Spring Security 过滤器链。
	 */
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		// 路径权限控制
		// @formatter:off
		http.authorizeHttpRequests((authorize) -> {
			authorize
				// 放行端点
				.antMatchers("/actuator/**").permitAll()
				// # 健康检查路径（此处为冗余配置，用于k8s探针/健康检查、滚动发布）
				.antMatchers("/actuator/health", "/actuator/health/liveness", "/actuator/health/readiness").permitAll()
				// 放行错误地址
				.antMatchers("/error").permitAll()
				// 其他路径均需要授权
				.anyRequest()
				.authenticated();
		});
		// @formatter:on

		// 禁用 form 登录
		http.formLogin().disable();

		// 资源服务配置秘钥
		http.oauth2ResourceServer().jwt(oauth2ResourceServer -> {
			RSAPublicKey rsaPublicKey = cloudJwkKeyProperties.rsaPublicKey();
			NimbusJwtDecoder.PublicKeyJwtDecoderBuilder publicKeyJwtDecoderBuilder = NimbusJwtDecoder
				.withPublicKey(rsaPublicKey);
			NimbusJwtDecoder nimbusJwtDecoder = publicKeyJwtDecoderBuilder.build();
			oauth2ResourceServer.decoder(nimbusJwtDecoder);
		});

		// 异常处理
		http.exceptionHandling(exceptionHandlingCustomizer -> {
			exceptionHandlingCustomizer
				// 访问被拒绝处理程序
				.accessDeniedHandler(accessDeniedHandler)
				// 身份验证入口点
				.authenticationEntryPoint(authenticationEntryPoint);
		});

		// CSRF 配置
		http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher);

		return http.build();
	}

}
