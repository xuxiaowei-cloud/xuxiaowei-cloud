package cloud.xuxiaowei.system.service;

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
     * 计算令牌的MD5值
     *
     * @param value 令牌
     * @return 返回 令牌的MD5值
     */
    String extractTokenKey(String value);

}
