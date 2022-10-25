package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.system.annotation.EncryptAnnotation;
import cloud.xuxiaowei.utils.Encrypt;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * OAuth 2
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/oauth2")
@Tag(name = "OAuth2RestController", description = "OAuth 2")
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2RestController {

	/**
	 * 检查 Token
	 * @param request 请求
	 * @param response 响应
	 * @param authentication 授权信息
	 * @return 返回 检查 Token 结果
	 */
	@EncryptAnnotation(value = Encrypt.AesVersion.V0,
			client = { @EncryptAnnotation.ClientId(clientId = "xuxiaowei_client_wechat_miniprogram_id",
					value = Encrypt.AesVersion.V1) })
	@ControllerAnnotation(description = "检查 Token")
	@PostMapping("/check_token")
	public Map<String, Object> checkToken(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		Map<String, Object> map = new HashMap<>(4);
		if (authentication != null) {
			Set<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toSet());
			map.put("authorities", authorities);
			map.put("active", true);
		}
		return map;
	}

}
