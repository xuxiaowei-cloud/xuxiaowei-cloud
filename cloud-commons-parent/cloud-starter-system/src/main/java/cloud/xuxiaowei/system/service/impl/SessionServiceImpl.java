package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.service.SessionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
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

}
