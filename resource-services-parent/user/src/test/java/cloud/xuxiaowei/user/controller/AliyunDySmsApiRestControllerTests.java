package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.utils.DateUtils;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static cloud.xuxiaowei.utils.DateUtils.SHORT_DATE_FORMAT;

/**
 * 阿里云 升级版 短信服务 测试类
 *
 * @see <a href=
 * "https://next.api.aliyun.com/api-tools/demo/Dysmsapi/db7e1211-14e0-4b7b-9011-037dfb85d42e">短信发送并查询示例</a>
 * @author xuxiaowei
 * @since 0.0..1
 */
@Slf4j
class AliyunDySmsApiRestControllerTests {

	@Test
	void send_query() throws Exception {

		Config config = new Config();
		// AccessKey ID
		config.accessKeyId = "";
		// AccessKey Secret
		config.accessKeySecret = "";
		Client client = new Client(config);

		// 手机号
		String phoneNumbers = "";
		// 签名名称
		String signName = "";
		// 模板CODE
		String templateCode = "";

		Map<String, String> templateParam = new HashMap<>();
		templateParam.put("code", "123456");

		ObjectMapper objectMapper = new ObjectMapper();

		// 1.发送短信
		// @formatter:off
		SendSmsRequest sendReq = new SendSmsRequest()
				.setPhoneNumbers(phoneNumbers)
				.setSignName(signName)
				.setTemplateCode(templateCode)
				.setTemplateParam(objectMapper.writeValueAsString(templateParam));
		// @formatter:on

		SendSmsResponse sendResp;
		try {
			sendResp = client.sendSms(sendReq);
			log.info(objectMapper.writeValueAsString(sendResp));
		}
		catch (TeaException e) {
			String code = e.getCode();
			String message = e.getMessage();
			Map<String, Object> data = e.getData();
			log.error("错误信息: {}，{}，{}", code, message, data);
			return;
		}

		String code = sendResp.body.code;
		if (!Common.equalString(code, "OK")) {
			log.error("错误信息: " + sendResp.body.message + "");
			return;
		}

		String bizId = sendResp.body.bizId;
		// 2. 等待 10 秒后查询结果
		Common.sleep(10000);
		// 3.查询结果
		QuerySendDetailsRequest queryReq = new QuerySendDetailsRequest().setPhoneNumber(phoneNumbers).setBizId(bizId)
				.setSendDate(DateUtils.format(LocalDate.now(), SHORT_DATE_FORMAT)).setPageSize(10L).setCurrentPage(1L);
		QuerySendDetailsResponse queryResp = client.querySendDetails(queryReq);
		java.util.List<QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO> dtos = queryResp.body.smsSendDetailDTOs.smsSendDetailDTO;
		// 打印结果
		for (QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO dto : dtos) {
			if (Common.equalString(dto.sendStatus + "", "3")) {
				log.info("" + dto.phoneNum + " 发送成功，接收时间: " + dto.receiveDate + "");
			}
			else if (Common.equalString(dto.sendStatus + "", "2")) {
				log.error(dto.phoneNum + " 发送失败");
			}
			else {
				log.warn(dto.phoneNum + " 正在发送中...");
			}
		}
	}

}
