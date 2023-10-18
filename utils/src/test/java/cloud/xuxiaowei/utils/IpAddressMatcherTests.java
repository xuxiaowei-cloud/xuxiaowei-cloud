package cloud.xuxiaowei.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * IP 匹配
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class IpAddressMatcherTests {

	@Test
	void matches_true() {
		IpAddressMatcher ipAddressMatcher = new IpAddressMatcher("10.96.0.0/12");
		String min = "10.96.0.0";
		String max = "10.111.255.255";
		List<String> ips = Arrays.asList(min, max);
		for (String ip : ips) {
			boolean match = ipAddressMatcher.matches(ip);
			log.info("ip: {}, 是否匹配: {}", ip, match);
			assert match;
		}
	}

	@Test
	void matches_false() {
		IpAddressMatcher ipAddressMatcher = new IpAddressMatcher("10.96.0.0/12");
		String min = "10.95.255.255";
		String max = "10.112.0.0";
		List<String> ips = Arrays.asList(min, max);
		for (String ip : ips) {
			boolean match = ipAddressMatcher.matches(ip);
			log.info("ip: {}, 是否匹配: {}", ip, match);
			assert !match;
		}
	}

}
