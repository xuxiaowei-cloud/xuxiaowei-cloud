package cloud.xuxiaowei.system.service;

import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpSession;

/**
 * {@link HttpSession} 服务接口
 *
 * @author xuxiaowei
 * @see 0.0.1
 */
public interface SessionService {

    /**
     * 获取 Token
     *
     * @return 返回 Token
     */
    String getTokenValue();

    /**
     * 获取 授权Token对象
     *
     * @return 返回 授权Token对象
     */
    OAuth2AccessToken readAccessToken();

    /**
     * 获取 默认授权Token对象
     *
     * @return 返回 默认授权Token对象
     */
    DefaultOAuth2AccessToken defaultOauth2AccessToken();

    /**
     * 获取 刷新Token对象
     *
     * @return 返回 刷新Token对象
     */
    DefaultExpiringOAuth2RefreshToken defaultExpiringOauth2RefreshToken();

    /**
     * 获取 刷新Token
     *
     * @return 返回 刷新Token
     */
    String getRefreshToken();

    /**
     * 计算令牌的MD5值
     *
     * @param value 令牌
     * @return 返回 令牌的MD5值
     */
    String extractTokenKey(String value);

    /**
     * 获取 授权Token ID
     * <p>
     * 身份验证未成功时（或：未进行身份验证），将返回 {@link HttpSession#getId()}
     *
     * @return 返回 授权Token ID，身份验证未成功时（或：未进行身份验证），将返回 {@link HttpSession#getId()}
     */
    String tokenId();

}
