package cloud.xuxiaowei.gateway.configuration;

import cloud.xuxiaowei.redis.configuration.RedisCacheManagerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.session.SaveMode;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

/**
 * Redis 缓存管理器 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableCaching
@EnableRedisWebSession(saveMode = SaveMode.ALWAYS)
@Import(RedisCacheManagerConfiguration.class)
public class RedisReactiveCacheManagerConfiguration {

	@Bean
	public WebSessionIdResolver webSessionIdResolver() {
		return new CookieWebSessionIdResolver();
	}

}
