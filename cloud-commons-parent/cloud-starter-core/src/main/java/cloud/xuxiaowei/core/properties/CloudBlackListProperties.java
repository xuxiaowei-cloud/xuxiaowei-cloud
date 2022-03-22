package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 微服务 黑名单 配置
 * <p>
 * 运行时刷新
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties("cloud.black-list")
public class CloudBlackListProperties {

    /**
     * IP 黑名单
     */
    private List<String> ips;

    /**
     * URL 黑名单
     * <p>
     * Key：服务名
     * Value：路径
     * <p>
     * 支持通配符
     */
    private LinkedHashMap<String, List<String>> urls;

}
