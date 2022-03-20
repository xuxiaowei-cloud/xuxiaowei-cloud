package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 微服务 Security 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("cloud.security")
public class CloudSecurityProperties {

    /**
     * 默认登录页面地址
     *
     * @see DefaultLoginPageGeneratingFilter#DEFAULT_LOGIN_PAGE_URL
     */
    private String defaultLoginPageUrl = "http://passport.xuxiaowei.cloud";

    /**
     * 登录页面地址
     *
     * @see FormLoginConfigurer#loginPage(String)
     */
    private String loginPageUrl = "/";

    /**
     * 登录请求地址
     * <p>
     * 验证用户名和密码的 URL
     *
     * @see FormLoginConfigurer#loginProcessingUrl(String)
     */
    private String loginProcessingUrl = "/login";

    /**
     * 登录失败地址
     * <p>
     * 登录服务（单服务）地址
     * <p>
     * 匿名访问
     *
     * @see FormLoginConfigurer#failureUrl(String)
     */
    private String failureUrl = "/login/failure";

    /**
     * 登录失败地址
     * <p>
     * 通过网关代理后的重定向地址
     * <p>
     * 匿名访问
     *
     * @see FormLoginConfigurer#failureUrl(String)
     */
    private String failureAllUrl = "http://gateway.xuxiaowei.cloud:1101/passport/login/failure";

    /**
     * 登录成功地址
     *
     * @see FormLoginConfigurer#successForwardUrl(String)
     */
    private String successForwardUrl = "/login/success";

    /**
     * 是否开启 CSRF
     */
    private boolean csrfEnabled = true;

    /**
     * 禁用 CSRF URL与 HTTP 方法
     * <p>
     * 1、URL 支持通配符<p>
     * 2、存在设置中的优先级（越靠前，优先级越高）
     */
    private LinkedHashMap<String, List<HttpMethod>> csrfDisableUrl;

}
