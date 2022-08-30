package cloud.xuxiaowei.system.configuration;

import cloud.xuxiaowei.redis.configuration.RedisCacheManagerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 系统组件配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Import(RedisCacheManagerConfiguration.class)
@Configuration
public class SystemConfiguration {

}
