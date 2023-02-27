package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.core.properties.CloudAliyunSmsProperties;
import cloud.xuxiaowei.system.entity.Sms;
import cloud.xuxiaowei.system.service.AliyunDySmsApiService;
import cloud.xuxiaowei.system.service.ISmsService;
import cloud.xuxiaowei.utils.DateUtils;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.utils.exception.ExceptionUtils;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;

/**
 * 阿里云 短信服务 升级版
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class AliyunDySmsApiServiceImpl implements AliyunDySmsApiService {

	private CloudAliyunSmsProperties cloudAliyunSmsProperties;

	private ISmsService smsService;

	@Autowired
	public void setCloudAliyunSmsProperties(CloudAliyunSmsProperties cloudAliyunSmsProperties) {
		this.cloudAliyunSmsProperties = cloudAliyunSmsProperties;
	}

	@Autowired
	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}

	/**
	 * 发送短信验证码
	 * @param phoneNumbers 手机号
	 * @param code 验证码
	 * @return 返回 发送短信结果
	 */
	@Override
	public SendSmsResponse sendSmsCode(@NonNull String phoneNumbers, @NonNull String code) {
		Map<String, String> templateParam = new HashMap<>(4);
		templateParam.put("code", code);
		return sendSms(phoneNumbers, templateParam);
	}

	/**
	 * 发送短信
	 * @param phoneNumbers 手机号
	 * @param templateParam 模板参数
	 * @return 返回 发送短信结果
	 */
	@Override
	public SendSmsResponse sendSms(@NonNull String phoneNumbers, @NonNull Map<String, String> templateParam) {
		String templateCode = cloudAliyunSmsProperties.getTemplateCode();
		return sendSmsTemplateCode(phoneNumbers, templateCode, templateParam);
	}

	/**
	 * 发送短信
	 * @param phoneNumbers 手机号
	 * @param templateCode 模板代码
	 * @param templateParam 模板参数
	 * @return 返回 发送短信结果
	 */
	@Override
	public SendSmsResponse sendSmsTemplateCode(@NonNull String phoneNumbers, @NonNull String templateCode,
			@NonNull Map<String, String> templateParam) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String json;
		try {
			json = objectMapper.writeValueAsString(templateParam);
		}
		catch (JsonProcessingException e) {
			throw new CloudRuntimeException("发送短信时，模板传参数处理异常", e);
		}

		SendSmsRequest sendSmsRequest = new SendSmsRequest().setPhoneNumbers(phoneNumbers)
			.setSignName(cloudAliyunSmsProperties.getSignName())
			.setTemplateCode(templateCode)
			.setTemplateParam(json);

		Sms sms = new Sms();
		sms.setSmsPlatform("1");
		sms.setAccessKeyId(cloudAliyunSmsProperties.getAccessKeyId());
		BeanUtils.copyProperties(sendSmsRequest, sms);

		Client client;
		try {
			client = client();
		}
		catch (Exception e) {
			sms.setException(ExceptionUtils.getStackTrace(e));
			smsService.save(sms);
			throw e;
		}

		try {
			SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);

			BeanUtils.copyProperties(sendSmsResponse, sms);
			BeanUtils.copyProperties(sendSmsResponse.getBody(), sms);
			sms.setHeaders(objectMapper.writeValueAsString(sendSmsResponse.getHeaders()));

			return sendSmsResponse;
		}
		catch (Exception e) {
			sms.setException(ExceptionUtils.getStackTrace(e));
			if (e instanceof TeaException) {
				TeaException teaException = (TeaException) e;
				Map<String, Object> data = teaException.getData();
				try {
					String value = objectMapper.writeValueAsString(data);
					sms.setExceptionData(value);
				}
				catch (JsonProcessingException ex) {
					throw new CloudRuntimeException(ex);
				}
			}
			throw new CloudRuntimeException("发送短信时异常", e);
		}
		finally {
			smsService.save(sms);
		}
	}

	/**
	 * 发送短信
	 * @param phoneNumber 手机号
	 * @param templateParam 模板参数
	 * @return 返回 发送短信结果
	 */
	@Override
	public boolean send(@NonNull String phoneNumber, @NonNull Map<String, String> templateParam) {
		SendSmsResponse sendSmsResponse = sendSms(phoneNumber, templateParam);
		return "OK".equals(sendSmsResponse.body.code);
	}

	/**
	 * 查询发送结果
	 * @param sendDate 发送日期
	 * @param phoneNumber 手机号
	 * @param bizId 业务ID
	 * @param currentPage 当前页
	 * @param pageSize 每页大小
	 * @return 返回 查询发送结果，判断条件：{@link QuerySendDetailsResponse#body} -&lt;
	 * {@link QuerySendDetailsResponseBody#smsSendDetailDTOs} -&lt;
	 * {@link QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOs#smsSendDetailDTO}
	 * -&lt;
	 * {@link QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO#sendStatus}
	 * 的值，3：成功，2：失败，1：正在发送中
	 */
	@Override
	public QuerySendDetailsResponse querySendDetails(@NonNull LocalDate sendDate, @NonNull String phoneNumber,
			@NonNull String bizId, Long currentPage, Long pageSize) {

		QuerySendDetailsRequest querySendDetailsRequest = new QuerySendDetailsRequest().setPhoneNumber(phoneNumber)
			.setBizId(bizId)
			.setSendDate(DateUtils.format(sendDate, PURE_DATE_PATTERN))
			.setCurrentPage(currentPage)
			.setPageSize(pageSize);

		Client client = client();

		try {
			return client.querySendDetails(querySendDetailsRequest);
		}
		catch (Exception e) {
			throw new CloudRuntimeException("查询发送详细信息异常", e);
		}
	}

	private Client client() {
		Config config = new Config();
		// AccessKey ID
		config.accessKeyId = cloudAliyunSmsProperties.getAccessKeyId();
		// AccessKey Secret
		config.accessKeySecret = cloudAliyunSmsProperties.getAccessKeySecret();
		try {
			return new Client(config);
		}
		catch (Exception e) {
			throw new CloudRuntimeException("短信服务，创建客户异常", e);
		}
	}

}
