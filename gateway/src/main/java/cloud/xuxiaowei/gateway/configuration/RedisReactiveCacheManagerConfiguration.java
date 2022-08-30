package cloud.xuxiaowei.gateway.configuration;

import cloud.xuxiaowei.redis.configuration.RedisCacheManagerConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Redis 缓存管理器 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@EnableCaching
@Import(RedisCacheManagerConfiguration.class)
public class RedisReactiveCacheManagerConfiguration {

}
