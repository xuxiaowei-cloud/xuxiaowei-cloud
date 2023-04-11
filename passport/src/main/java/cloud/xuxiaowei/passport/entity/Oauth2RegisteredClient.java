package cloud.xuxiaowei.passport.entity;

import cloud.xuxiaowei.core.properties.CloudClientProperties;
import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

/**
 * <p>
 * 客户表。
 * 原表结构：oauth2-authorization-server-*.*.*.jar!/org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql
 * 原表结构：https://github.com/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-authorization-server/blob/main/oauth2-authorization-server/src/main/resources/org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-18
 */
@Slf4j
@Data
@TableName("oauth2_registered_client")
public class Oauth2RegisteredClient implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	private String id;

	private String clientId;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime clientIdIssuedAt;

	private String clientSecret;

	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime clientSecretExpiresAt;

	private String clientName;

	private String clientAuthenticationMethods;

	private String authorizationGrantTypes;

	private String redirectUris;

	private String scopes;

	private String clientSettings;

	private String tokenSettings;

	private Long tenantId;

	private String clientType;

	private String config;

	public CloudClientProperties.Client loadAsConfig() {

		if (!StringUtils.hasText(this.config)) {
			return null;
		}

		Yaml yaml = new Yaml();
		@SuppressWarnings("unchecked")
		Map<String, LinkedHashMap<String, LinkedHashMap<String, Object>>> config = yaml.loadAs(this.config, Map.class);
		LinkedHashMap<String, LinkedHashMap<String, Object>> cloud = config.get("cloud");
		LinkedHashMap<String, Object> clientMap = cloud.get("client");
		Object clientListObj = clientMap.get("list");

		convert(clientMap);

		if (clientListObj instanceof List) {
			@SuppressWarnings("unchecked")
			List<LinkedHashMap<String, Object>> clientList = (List<LinkedHashMap<String, Object>>) clientListObj;
			for (LinkedHashMap<String, Object> client : clientList) {
				convert(client);
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		CloudClientProperties.Client client;

		CloudClientProperties cloudClientProperties = objectMapper.convertValue(clientMap, CloudClientProperties.class);
		if (cloudClientProperties == null) {
			client = null;
		}
		else {
			List<CloudClientProperties.Client> list = cloudClientProperties.getList();
			int size = list.size();
			if (size == 0) {
				client = null;
			}
			else if (size == 1) {
				client = list.get(0);
			}
			else {
				client = list.get(0);
				log.warn("将 config 转化为 对象后，得到多个 CloudClientProperties.Client，将从转换后的 List 对象中获取第一条");
			}
		}

		return client;
	}

	private void convert(LinkedHashMap<String, Object> map) {
		LinkedHashMap<String, Object> newMap = new LinkedHashMap<>(map);

		newMap.forEach((key, value) -> {
			String convertName = convertName(key);
			if (!convertName.equals(key)) {
				map.remove(key);
				map.put(convertName, value);
			}
		});
	}

	private String convertName(String key) {
		String[] arr = key.split("-");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];
			if (i != 0) {
				char c = s.charAt(0);
				sb.append(Character.toUpperCase(c));
				sb.append(s.substring(1));
			}
			else {
				sb.append(s);
			}
		}
		return sb.toString();
	}

}
