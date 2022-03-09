package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.stereotype.Component;

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
     *
     * @see FormLoginConfigurer#failureUrl(String)
     */
    private String failureUrl = "/login/failure";

    /**
     * 登录成功地址
     *
     * @see FormLoginConfigurer#successForwardUrl(String)
     */
    private String successForwardUrl = "/login/success";

}
