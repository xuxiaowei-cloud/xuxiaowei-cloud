package cloud.xuxiaowei.passport;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RestTemplate} 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class RestTemplateTests {

	public static final String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?openid={openid}&access_token={access_token}";

	@Test
	void userinfo() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		Map<String, String> map = new HashMap<>(8);
		map.put("openid", "oBWvW1ACPJAoSKzm7V8z78Ib88WM");
		map.put("access_token",
				"61_oP0mIvCsjGPDi3jwfQq-Z0x6hKT6CpMZ5CLFnddSEtfNdGwLOW5Z__khmtp7o6RxIkbeiMSZsob72EOkKLUtcAbCP9Ch7S_D2iUbcXXTnWc");
		String response = restTemplate.getForObject(USERINFO_URL, String.class, map);
		log.info(response);
	}

}
