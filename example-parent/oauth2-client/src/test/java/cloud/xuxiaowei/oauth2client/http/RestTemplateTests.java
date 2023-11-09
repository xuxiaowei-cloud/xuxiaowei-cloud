package cloud.xuxiaowei.oauth2client.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
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

	@Test
	void authorization_code_with_multipart_form_data() {
		String code = "8onD2At4lWVj6r6zffarDizsXEaBWmY9xJ4C80HIowKsfYLY8pZnz1dDLr6zel5hw4MGkwe7d-f3uG4N2n3cykl9UWVWFFy01rjrigWUc0AHExrFpJk8GE0-RLRQ0s5V";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		String clientId = "xuxiaowei_client_id";
		String clientSecret = "xuxiaowei_client_secret";
		String redirectUri = "http://gateway.example.xuxiaowei.cloud:1101/passport/code/1";

		Map<String, String> map = new HashMap<>(8);
		map.put("code", code);
		map.put("redirect_uri", redirectUri);

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(8);
		multiValueMap.put("client_id", Collections.singletonList(clientId));
		multiValueMap.put("client_secret", Collections.singletonList(clientSecret));

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token"
				+ "?code={code}&redirect_uri={redirect_uri}&grant_type=authorization_code";

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class,
				map);

		HttpStatus statusCode = responseEntity.getStatusCode();
		log.info(String.valueOf(statusCode));
		log.info(responseEntity.getBody());
	}

	/**
	 * 刷新Token
	 * <p>
	 * 使用 application/json
	 * <p>
	 * 注意：在 org.springframework.security:spring-security-oauth2-authorization-server
	 * 0.4.4/1.1.3 及以上版本不能使用（不能在 URL 中使用 client_id、client_secret）
	 */
	@Test
	void refreshToken_with_application_json() {

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
	 * 刷新Token
	 */
	@Test
	void refreshToken_with_multipart_form_data() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		String clientId = "xuxiaowei_client_id";
		String clientSecret = "xuxiaowei_client_secret";
		String refreshToken = "KGdp3umBIf8kNIka5GKICTTE3p4GmGREgBkDc1W6UkG-ovJ1bwrgy8p33IURrwYSqSmuuzIolBjfOSb8x4KfnlyOVHbxDfZdKGLFlJ1EB69TpuT88635bcHJCpat-lg6";

		Map<String, String> map = new HashMap<>(8);
		map.put("refresh_token", refreshToken);

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(8);
		multiValueMap.put("client_id", Collections.singletonList(clientId));
		multiValueMap.put("client_secret", Collections.singletonList(clientSecret));

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token"
				+ "?grant_type=refresh_token&refresh_token={refresh_token}";

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class,
				map);

		HttpStatus statusCode = responseEntity.getStatusCode();
		log.info(String.valueOf(statusCode));
		log.info(responseEntity.getBody());
	}

	/**
	 * 客户端凭证模式：client_credentials
	 * <p>
	 * 使用 application/json
	 * <p>
	 * 注意：在 org.springframework.security:spring-security-oauth2-authorization-server
	 * 0.4.4/1.1.3 及以上版本不能使用（不能在 URL 中使用 client_id、client_secret）
	 */
	@Test
	void clientCredentials_with_application_json() {

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
	 * <p>
	 * 使用 multipart/form-data
	 */
	@Test
	void clientCredentials_with_multipart_form_data() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		String clientId = "xuxiaowei_client_id";
		String clientSecret = "xuxiaowei_client_secret";
		String scope = "snsapi_base snsapi_info";

		Map<String, String> map = new HashMap<>(8);
		map.put("scope", scope);

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(8);
		multiValueMap.put("client_id", Collections.singletonList(clientId));
		multiValueMap.put("client_secret", Collections.singletonList(clientSecret));

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token?grant_type=client_credentials&scope={scope}";

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class,
				map);

		HttpStatus statusCode = responseEntity.getStatusCode();
		log.info(String.valueOf(statusCode));
		log.info(responseEntity.getBody());
	}

	/**
	 * 客户端凭证模式：client_credentials
	 * <p>
	 * 使用 application/x-www-form-urlencoded
	 */
	@Test
	void clientCredentials_with_application_x_www_form_urlencoded() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		String clientId = "xuxiaowei_client_id";
		String clientSecret = "xuxiaowei_client_secret";
		String scope = "snsapi_base snsapi_info";

		Map<String, String> map = new HashMap<>(8);
		map.put("scope", scope);

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(8);
		multiValueMap.put("client_id", Collections.singletonList(clientId));
		multiValueMap.put("client_secret", Collections.singletonList(clientSecret));

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);

		String accessTokenUri = "http://localhost:1401/oauth2/token?grant_type=client_credentials&scope={scope}";

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
