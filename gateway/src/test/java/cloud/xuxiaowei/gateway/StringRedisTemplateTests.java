package cloud.xuxiaowei.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
@SpringBootTest
class StringRedisTemplateTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	void set() {
		stringRedisTemplate.opsForValue().set("name", "徐晓伟");
	}

}
