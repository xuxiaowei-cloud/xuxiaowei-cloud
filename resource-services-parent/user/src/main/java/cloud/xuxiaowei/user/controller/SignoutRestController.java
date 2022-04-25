package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.core.properties.CloudCookieProperties;
import cloud.xuxiaowei.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 退出
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/signout")
public class SignoutRestController {

    private TokenStore tokenStore;

    private CloudCookieProperties cloudCookieProperties;

    @Autowired
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Autowired
    public void setCloudCookieProperties(CloudCookieProperties cloudCookieProperties) {
        this.cloudCookieProperties = cloudCookieProperties;
    }

    /**
     * 退出
     *
     * @param request  请求
     * @param response 响应
     * @param session  Session，不存在时自动创建
     * @return 返回 退出提示语
     */
    @RequestMapping
    public Response<?> index(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                             OAuth2Authentication oauth2Authentication) {

        // 此处可记录用户退出登录的时间及IP等信息

        Object details = oauth2Authentication.getDetails();
        if (details instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails oauth2AuthenticationDetails = (OAuth2AuthenticationDetails) details;
            String tokenValue = oauth2AuthenticationDetails.getTokenValue();

            OAuth2AccessToken oauth2AccessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(oauth2AccessToken);

            OAuth2RefreshToken oauth2RefreshToken = oauth2AccessToken.getRefreshToken();
            tokenStore.removeRefreshToken(oauth2RefreshToken);
        }

        String cookieDomain = cloudCookieProperties.getCookieDomain();
        String cookieName = cloudCookieProperties.getCookieName();
        String cookiePath = cloudCookieProperties.getCookiePath();

        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath(cookiePath == null ? "/" : cookieDomain);
        cookie.setDomain(cookieDomain);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return Response.ok();
    }

}
