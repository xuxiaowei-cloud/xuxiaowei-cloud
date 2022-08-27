package cloud.xuxiaowei.oauth2client.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link RestTemplate} 示例
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class RestTemplateTests {

	/**
	 * 刷新Token
	 */
	@Test
	void refreshToken() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		String clientId = "xuxiaowei_client_id";
		String clientSecret = "xuxiaowei_client_secret";
		String refreshToken = "3ZXgvdh9R65HPkp5vsoBFhNwV10ZoqTnvkH5gXXZlUfT7VyIxo-nRhcTja17mvH3k922VXdDJ8Sum5LE5TkWZ7wXFQhOg5Oiy0MphhY2F1PJnaNGUD4CCaKBox5396US";

		Map<String, String> map = new HashMap<>(8);
		map.put("client_id", clientId);
		map.put("client_secret", clientSecret);
		map.put("refresh_token", refreshToken);

		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token"
				+ "?client_id={client_id}&client_secret={client_secret}&grant_type=refresh_token&refresh_token={refresh_token}";

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class,
				map);

		HttpStatus statusCode = responseEntity.getStatusCode();
		log.info(String.valueOf(statusCode));
		log.info(responseEntity.getBody());
	}

	/**
	 * 客户端凭证模式：client_credentials
	 */
	@Test
	void clientCredentials() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		String clientId = "xuxiaowei_client_id";
		String clientSecret = "xuxiaowei_client_secret";
		String scope = "snsapi_base snsapi_info";

		Map<String, String> map = new HashMap<>(8);
		map.put("client_id", clientId);
		map.put("client_secret", clientSecret);
		map.put("scope", scope);

		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token"
				+ "?client_id={client_id}&client_secret={client_secret}&grant_type=client_credentials&scope={scope}";

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class,
				map);

		HttpStatus statusCode = responseEntity.getStatusCode();
		log.info(String.valueOf(statusCode));
		log.info(responseEntity.getBody());
	}

	/**
	 * 客户端凭证模式：client_credentials
	 */
	@Test
	void clientCredentials_xuxiaowei_client_wechat_miniprogram_id() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		String clientId = "xuxiaowei_client_wechat_miniprogram_id";
		String clientSecret = "xuxiaowei_client_wechat_miniprogram_secret";
		String scope = "snsapi_base snsapi_info";

		Map<String, String> map = new HashMap<>(8);
		map.put("client_id", clientId);
		map.put("client_secret", clientSecret);
		map.put("scope", scope);

		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token"
				+ "?client_id={client_id}&client_secret={client_secret}&grant_type=client_credentials&scope={scope}";

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class,
				map);

		HttpStatus statusCode = responseEntity.getStatusCode();
		log.info(String.valueOf(statusCode));
		log.info(responseEntity.getBody());
	}

}
