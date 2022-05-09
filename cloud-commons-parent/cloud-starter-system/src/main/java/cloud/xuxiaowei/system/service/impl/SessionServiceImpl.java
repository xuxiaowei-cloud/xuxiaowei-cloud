package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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

}
