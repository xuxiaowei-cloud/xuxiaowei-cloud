package cloud.xuxiaowei.adminserver;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 端点测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class ActuatorTests {

	/**
	 * 心跳检查测试
	 */
	@Test
	void health() throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();

		int success = 0;
		int failure = 0;

		for (int i = 0; i < 10 * 60 * 60; i++) {
			try {
				// 健康检查路径
				// Windows 上使用 /actuator/health
				// k8s 上使用 /actuator/health/readiness、/actuator/health/liveness
				ResponseEntity<String> entity = restTemplate
					.getForEntity("http://192.168.0.27:31201/actuator/health", String.class);
				int statusCodeValue = entity.getStatusCodeValue();
				if (statusCodeValue == 200) {
					success++;
				}
				else {
					log.error("请求失败，响应状态码不正确：{}", statusCodeValue);
					failure++;
				}
			}
			catch (Exception e) {
				failure++;
				log.error("请求失败", e);
			}

			log.info("次数：{} 失败：{} 成功：{}", i, failure, success);

			Thread.sleep(100);
		}
	}

}
