package cloud.xuxiaowei.log.service.impl;

import cloud.xuxiaowei.log.entity.Log;
import cloud.xuxiaowei.log.mapper.LogMapper;
import cloud.xuxiaowei.log.service.ILogService;
import cloud.xuxiaowei.utils.Constants;
import cloud.xuxiaowei.utils.SecurityUtils;
import cloud.xuxiaowei.utils.exception.ExceptionUtils;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-01
 */
@Service
@DS("log")
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

	private ConfigurableEnvironment environment;

	private NacosDiscoveryProperties nacosDiscoveryProperties;

	private ServerProperties serverProperties;

	@Autowired
	public void setEnvironment(ConfigurableEnvironment environment) {
		this.environment = environment;
	}

	@Autowired
	public void setNacosDiscoveryProperties(NacosDiscoveryProperties nacosDiscoveryProperties) {
		this.nacosDiscoveryProperties = nacosDiscoveryProperties;
	}

	@Autowired
	public void setServerProperties(ServerProperties serverProperties) {
		this.serverProperties = serverProperties;
	}

	/**
	 * 根据请求保存数据
	 * @param hostAddress IP
	 * @param requestId 请求ID
	 * @param sessionId SessionID
	 * @param method 请求方法
	 * @param requestUri 请求地址
	 * @param queryString 请求参数
	 * @param headersMap 请求头
	 * @param authorization 权限标识
	 * @param userAgent 浏览器标识
	 * @param ex 异常
	 * @return 返回保存结果
	 */
	@Override
	public boolean saveLog(String hostAddress, String requestId, String sessionId, String method, String requestUri,
			String queryString, String headersMap, String authorization, String userAgent, Throwable ex) {

		String module = environment.getProperty("spring.application.name");

		String ip = nacosDiscoveryProperties.getIp();

		Integer port = serverProperties.getPort();

		String payload = SecurityUtils.getPayload(authorization);
		payload = SecurityUtils.inspectPayload(payload);
		Map<String, String> payloadMap = SecurityUtils.getPayloadStringMap(authorization);

		String createUsersId;
		String usersId = payloadMap.get(Constants.USERS_ID);
		String username = payloadMap.get(Constants.USERNAME);
		String sub = payloadMap.get("sub");
		if (StringUtils.hasText(usersId)) {
			createUsersId = usersId;
		}
		else if (StringUtils.hasText(username)) {
			createUsersId = username;
		}
		else if (StringUtils.hasText(sub)) {
			createUsersId = sub;
		}
		else {
			createUsersId = "";
		}

		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();

		LocalTime localTime = LocalTime.now();
		int hour = localTime.getHour();

		Log log = new Log();
		log.setModule(module);
		log.setDate(localDate);
		log.setYear(year);
		log.setMonth(month);
		log.setDay(day);
		log.setHour(hour);
		log.setMethod(method);
		log.setRequestUri(requestUri);
		log.setQueryString(queryString);
		log.setHeadersMap(headersMap);
		log.setAuthorization(authorization);
		log.setPayload(payload);
		log.setUserAgent(userAgent);
		log.setRequestId(requestId);
		log.setSessionId(sessionId);
		log.setException(ex == null ? null : ExceptionUtils.getStackTrace(ex));
		log.setCreateUsersId(createUsersId);
		log.setCreateIp(hostAddress);
		log.setHostname(null);
		log.setIpAddress(ip);
		log.setPort(port);
		log.setCreateDate(LocalDateTime.now());

		return save(log);
	}

}
