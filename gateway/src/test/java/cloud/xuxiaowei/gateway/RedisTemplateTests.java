package cloud.xuxiaowei.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@SpringBootTest
class RedisTemplateTests {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	void put() {
		redisTemplate.opsForHash().put("map", "name", "徐晓伟");
	}

}
