package cloud.xuxiaowei.session.configuration;

import cloud.xuxiaowei.redis.configuration.RedisCacheManagerConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.web.jackson2.WebJackson2Module;
import org.springframework.security.web.jackson2.WebServletJackson2Module;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

import javax.servlet.http.HttpSession;

/**
 * Session Redis 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@Import(RedisCacheManagerConfiguration.class)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 12)
public class SessionRedisSerializerConfiguration {

	/**
	 * Spring {@link HttpSession} 默认 Redis 序列化程序
	 * <p>
	 * 名称必须为：springSessionDefaultRedisSerializer
	 * @return 返回 Spring {@link HttpSession} 默认 Redis 序列化程序
	 * @see RedisHttpSessionConfiguration#setDefaultRedisSerializer(RedisSerializer) 自定义
	 * Spring {@link HttpSession} 默认 Redis 序列化程序
	 */
	@Bean
	public RedisSerializer<?> springSessionDefaultRedisSerializer() {

		// 可以使用读写JSON
		Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

		ObjectMapper objectMapper = RedisCacheManagerConfiguration.objectMapper();

		// Web、Security 序列化与反序列化
		// https://github.com/spring-projects/spring-security/issues/4370
		objectMapper.registerModules(new WebServletJackson2Module(), new WebJackson2Module(), new CoreJackson2Module());

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		return jackson2JsonRedisSerializer;
	}

}
