package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import cloud.xuxiaowei.passport.controller.IndexController;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordNonExistException;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordValidException;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * 密码编辑器
 *
 * @author xuxiaowei
 * @see PasswordEncoderFactories#createDelegatingPasswordEncoder()
 * @since 0.0.1
 */
@Slf4j
@Component
public class PasswordEncoderImpl implements PasswordEncoder {

	// @formatter:off
	private static final List<String> THIRD_PARTY_GRANTTYPE = Arrays.asList(
			OAuth2AlipayMiniProgramAuthenticationToken.ALIPAY_MINIPROGRAM.getValue(),
			OAuth2AlipayOplatformWebsiteAuthenticationToken.ALIPAY_OPLATFORM_WEBSITE.getValue(),
			OAuth2DingtalkAuthenticationToken.DINGTALK.getValue(),
			OAuth2GiteeAuthenticationToken.GITEE.getValue(),
			OAuth2GitHubAuthenticationToken.GITHUB.getValue(),
			OAuth2GitLabAuthenticationToken.GITLAB.getValue(),
			OAuth2QQMiniProgramAuthenticationToken.QQ_MINIPROGRAM.getValue(),
			OAuth2QQWebsiteAuthenticationToken.QQ_WEBSITE.getValue(),
			OAuth2WeChatMiniProgramAuthenticationToken.WECHAT_MINIPROGRAM.getValue(),
			OAuth2WeChatOffiaccountAuthenticationToken.WECHAT_OFFIACCOUNT.getValue(),
			OAuth2WeChatOplatformWebsiteAuthenticationToken.WECHAT_OPLATFORM_WEBSITE.getValue(),
			OAuth2WeChatWorkWebsiteAuthenticationToken.WECHAT_WORK_WEBSITE.getValue(),
			OAuth2WeiBoWebsiteAuthenticationToken.WEIBO_WEBSITE.getValue());
	// @formatter:on

	private HttpServletRequest request;

	private CloudSecurityProperties cloudSecurityProperties;

	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Autowired
	public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
		this.cloudSecurityProperties = cloudSecurityProperties;
	}

	@Override
	public String encode(CharSequence rawPassword) {
		if (StringUtils.hasText(rawPassword)) {
			return rawPassword.toString();
		}
		else {
			LoginParamPasswordNonExistException loginException = new LoginParamPasswordNonExistException("登录参数不存在密码");
			loginException.setUsername(request.getParameter(Constant.USERNAME));
			throw loginException;
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		HttpSession session = request.getSession();
		String rsaPrivateKeyBase64 = session.getAttribute(IndexController.RSA_PRIVATE_KEY_BASE64) + "";

		boolean matches;

		String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
		String requestUri = request.getRequestURI();
		if ("/oauth2/token".equals(requestUri) && THIRD_PARTY_GRANTTYPE.contains(grantType)) {
			log.info("客户密码认证");
			matches = passwordEncoder.matches(rawPassword, encodedPassword);
		}
		else {
			try {
				if (cloudSecurityProperties.isEnabledRsa()) {
					log.info("比较密码时启用了RSA对密码进行解密");
					RSA rsa = new RSA(rsaPrivateKeyBase64, null);
					String rawPasswordDecryptStr = rsa.decryptStr(rawPassword.toString(), KeyType.PrivateKey);

					matches = passwordEncoder.matches(rawPasswordDecryptStr, encodedPassword);
				}
				else {
					log.info("比较密码时未启用RSA对密码进行解密");
					matches = passwordEncoder.matches(rawPassword, encodedPassword);
				}
			}
			catch (Exception e) {
				// 可能根据用户名没有找到用户信息（即：密码），导致比较失败
				LoginParamPasswordValidException loginException = new LoginParamPasswordValidException("比较密码时异常", e);
				loginException.setUsername(request.getParameter(Constant.USERNAME));
				throw loginException;
			}
			if (!matches) {
				LoginParamPasswordValidException loginException = new LoginParamPasswordValidException("密码不匹配");
				loginException.setUsername(request.getParameter(Constant.USERNAME));
				throw loginException;
			}
		}

		return matches;
	}

}
