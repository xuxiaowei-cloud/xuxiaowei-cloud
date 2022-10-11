package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 微服务 短信服务 配置
 * <p>
 * 运行时刷新
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties("cloud.aliyun.sms")
public class CloudAliyunSmsProperties {

	/**
	 * AccessKey ID
	 *
	 * @see <a href="https://ram.console.aliyun.com">RAM 访问控制</a>
	 */
	private String accessKeyId;

	/**
	 * AccessKey Secret
	 *
	 * @see <a href="https://ram.console.aliyun.com">RAM 访问控制</a>
	 */
	private String accessKeySecret;

	/**
	 * 签名名称
	 */
	private String signName;

	/**
	 * 模板CODE
	 */
	private String templateCode;

}
