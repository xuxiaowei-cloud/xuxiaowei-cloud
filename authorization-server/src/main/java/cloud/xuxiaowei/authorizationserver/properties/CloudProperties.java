package cloud.xuxiaowei.authorizationserver.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微服务 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("cloud")
public class CloudProperties {

    /**
     * 登录页面地址
     */
    private String loginPageUrl;

}
