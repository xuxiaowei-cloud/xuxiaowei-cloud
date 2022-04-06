package cloud.xuxiaowei.system.service.impl;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.*;

/**
 * 授权码服务 实现类
 *
 * @author xuxiaowei
 * @see RandomValueAuthorizationCodeServices
 * @see InMemoryAuthorizationCodeServices
 * @see JdbcAuthorizationCodeServices
 * @see RedisAuthorizationCodeServices
 * @since 0.0.1
 */
public class AuthorizationCodeServicesImpl implements AuthorizationCodeServices {

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        return null;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
        return null;
    }

}
