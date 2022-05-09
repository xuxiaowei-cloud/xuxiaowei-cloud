package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * {@link HttpSession} 服务接口 实现类
 *
 * @author xuxiaowei
 * @see 0.0.1
 */
@Service
public class SessionServiceImpl implements SessionService {

    private TokenStore tokenStore;

    @Autowired
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    /**
     * 获取 Token
     *
     * @return 返回 Token
     */
    @Override
    public String getTokenValue() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object details = authentication.getDetails();

        if (details instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails oauth2AuthenticationDetails = (OAuth2AuthenticationDetails) details;
            return oauth2AuthenticationDetails.getTokenValue();
        }

        return null;
    }

    /**
     * 获取 授权Token对象
     *
     * @return 返回 授权Token对象
     */
    @Override
    public OAuth2AccessToken readAccessToken() {
        return tokenStore.readAccessToken(getTokenValue());
    }

    /**
     * 获取 默认授权Token对象
     *
     * @return 返回 默认授权Token对象
     */
    @Override
    public DefaultOAuth2AccessToken defaultOauth2AccessToken() {
        OAuth2AccessToken oauth2AccessToken = readAccessToken();
        if (oauth2AccessToken instanceof DefaultOAuth2AccessToken) {
            return (DefaultOAuth2AccessToken) oauth2AccessToken;
        }
        return null;
    }

    /**
     * 获取 刷新Token对象
     *
     * @return 返回 刷新Token对象
     */
    @Override
    public DefaultExpiringOAuth2RefreshToken defaultExpiringOauth2RefreshToken() {
        OAuth2AccessToken oauth2AccessToken = readAccessToken();
        if (oauth2AccessToken instanceof DefaultExpiringOAuth2RefreshToken) {
            return (DefaultExpiringOAuth2RefreshToken) oauth2AccessToken;
        }
        return null;
    }

    /**
     * 获取 刷新Token
     *
     * @return 返回 刷新Token
     */
    @Override
    public String getRefreshToken() {
        DefaultExpiringOAuth2RefreshToken defaultExpiringOauth2RefreshToken = defaultExpiringOauth2RefreshToken();
        if (defaultExpiringOauth2RefreshToken != null) {
            return defaultExpiringOauth2RefreshToken.getValue();
        }
        return null;
    }

    /**
     * 计算令牌的MD5值
     *
     * @param value 令牌
     * @return 返回 令牌的MD5值
     */
    @Override
    public String extractTokenKey(String value) {
        if (value == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 算法不可用。致命的（应该在 JDK 中）。");
        }

        byte[] bytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        return String.format("%032x", new BigInteger(1, bytes));
    }

}
