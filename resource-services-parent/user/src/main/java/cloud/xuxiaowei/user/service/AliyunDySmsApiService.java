package cloud.xuxiaowei.user.service;

import com.aliyun.dysmsapi20170525.models.QuerySendDetailsResponse;
import com.aliyun.dysmsapi20170525.models.QuerySendDetailsResponseBody;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.Map;

/**
 * 阿里云 短信服务 升级版
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface AliyunDySmsApiService {

	/**
	 * 发送短信
	 * @param phoneNumbers 手机号
	 * @param templateParam 模板参数
	 * @return 返回 发送短信结果
	 */
	SendSmsResponse sendSms(@NonNull String phoneNumbers, @NonNull Map<String, String> templateParam);

	/**
	 * 发送短信
	 * @param phoneNumber 手机号
	 * @param templateParam 模板参数
	 * @return 返回 发送短信结果
	 */
	boolean send(@NonNull String phoneNumber, @NonNull Map<String, String> templateParam);

	/**
	 * 查询发送结果
	 * @param sendDate 发送日期
	 * @param phoneNumber 手机号
	 * @param bizId 业务ID
	 * @param currentPage 当前页
	 * @param pageSize 每页大小
	 * @return 返回 查询发送结果，判断条件：{@link QuerySendDetailsResponse#body} ->
	 * {@link QuerySendDetailsResponseBody#smsSendDetailDTOs} ->
	 * {@link QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOs#smsSendDetailDTO}
	 * ->
	 * {@link QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO#sendStatus}
	 * 的值，3：成功，2：失败，1：正在发送中
	 */
	QuerySendDetailsResponse querySendDetails(@NonNull LocalDate sendDate, @NonNull String phoneNumber,
			@NonNull String bizId, Long currentPage, Long pageSize);

}
