package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 微服务 客户端 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties("cloud.tenant")
public class CloudTenantProperties {

	/**
	 * 默认租户ID
	 */
	private Long defaultTenantId;

	/**
	 * 默认客户ID
	 */
	private String defaultClientId;

}
