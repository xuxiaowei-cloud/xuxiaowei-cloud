package cloud.xuxiaowei.user.service;

import cloud.xuxiaowei.system.service.AliyunDySmsApiService;
import com.aliyun.dysmsapi20170525.models.QuerySendDetailsResponse;
import com.aliyun.dysmsapi20170525.models.QuerySendDetailsResponseBody;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 阿里云 短信服务 升级版 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@SpringBootTest
class AliyunDySmsApiServiceTests {

	@Autowired
	private AliyunDySmsApiService aliyunDySmsApiService;

	@Test
	void sendSms() {
		Map<String, String> map = new HashMap<>();
		map.put("code", "123456");
		SendSmsResponse sendSmsResponse = aliyunDySmsApiService.sendSms("", map);
		log.info(String.valueOf(sendSmsResponse));
	}

	@Test
	void querySendDetails() {
		QuerySendDetailsResponse querySendDetailsResponse = aliyunDySmsApiService.querySendDetails(LocalDate.now(), "",
				"", 1L, 10L);
		List<QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO> dtos = querySendDetailsResponse.body.smsSendDetailDTOs.smsSendDetailDTO;
		for (QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO dto : dtos) {
			if (dto.sendStatus == 3) {
				log.info(dto.phoneNum + " 发送成功，接收时间: " + dto.receiveDate);
			}
			else if (dto.sendStatus == 2) {
				log.error(dto.phoneNum + " 发送失败");
			}
			else {
				log.warn(dto.phoneNum + " 正在发送中...");
			}
		}
	}

}
