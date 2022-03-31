package cloud.xuxiaowei.system.filter;

import cloud.xuxiaowei.core.properties.CloudClientProperties;
import cloud.xuxiaowei.core.properties.CloudCookieProperties;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OAuth2登录成功主页 过滤器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Component
public class Oauth2LoginSuccessHomePageHttpFilter extends HttpFilter implements Ordered {

    public static final int ORDERED = Ordered.HIGHEST_PRECEDENCE + 10000;

    private CloudClientProperties cloudClientProperties;

    private CloudCookieProperties cloudCookieProperties;

    @Autowired
    public void setCloudClientProperties(CloudClientProperties cloudClientProperties) {
        this.cloudClientProperties = cloudClientProperties;
    }

    @Autowired
    public void setCloudCookieProperties(CloudCookieProperties cloudCookieProperties) {
        this.cloudCookieProperties = cloudCookieProperties;
    }

    @Setter
    private int order = ORDERED;

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String oauth2LoginSuccessHomePage = cloudClientProperties.getOauth2LoginSuccessHomePage();
        String cookieDomain = cloudCookieProperties.getCookieDomain();
        Cookie cookie = new Cookie("oauth2LoginSuccessHomePage", oauth2LoginSuccessHomePage);
        cookie.setDomain(cookieDomain);
        res.addCookie(cookie);

        super.doFilter(req, res, chain);
    }

}
