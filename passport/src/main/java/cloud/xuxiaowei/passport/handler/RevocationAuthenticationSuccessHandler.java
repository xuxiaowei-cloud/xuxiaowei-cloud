package cloud.xuxiaowei.passport.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2TokenRevocationAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 撤销授权成功后的处理
 *
 * @author xuxiaowei
 * @see 0.0.1
 */
@Slf4j
@Component
public class RevocationAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private OAuth2AuthorizationService oauth2AuthorizationService;

	@Autowired
	public void setOauth2AuthorizationService(OAuth2AuthorizationService oauth2AuthorizationService) {
		this.oauth2AuthorizationService = oauth2AuthorizationService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (authentication instanceof OAuth2TokenRevocationAuthenticationToken) {
			OAuth2TokenRevocationAuthenticationToken authenticationToken = (OAuth2TokenRevocationAuthenticationToken) authentication;
			String token = authenticationToken.getToken();
			OAuth2Authorization authorization = oauth2AuthorizationService.findByToken(token,
					OAuth2TokenType.ACCESS_TOKEN);
			if (authorization == null) {
				log.warn("未找到Token：{}", token);
			}
			else {
				oauth2AuthorizationService.remove(authorization);
				log.info("删除Token成功：{}", token);
			}
		}
	}

}
