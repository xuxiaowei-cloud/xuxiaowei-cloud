package cloud.xuxiaowei.oauth2client.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link RestTemplate} 示例
 *
 * @author xuxiaowei
 * @see OAuth2Utils
 * @since 0.0.1
 */
@Slf4j
class RestTemplateTests {

    /**
     * 客户端凭证模式：client_credentials
     */
    @Test
    void clientCredentials() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> map = new HashMap<>(8);
        map.put("client_id", "xuxiaowei_client_id");
        map.put("client_secret", "xuxiaowei_client_secret");
        map.put("scope", "snsapi_base snsapi_userinfo");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        String accessTokenUri = "http://authorization-server.xuxiaowei.cloud:1301/oauth/token" +
                "?client_id={client_id}&client_secret={client_secret}&grant_type=client_credentials&scope={scope}";

        ResponseEntity<OAuth2AccessToken> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, OAuth2AccessToken.class, map);

        HttpStatus statusCode = responseEntity.getStatusCode();
        log.info(String.valueOf(statusCode));

        OAuth2AccessToken oauth2AccessToken = responseEntity.getBody();

        assert oauth2AccessToken != null;

        log.info(oauth2AccessToken.getValue());
        log.info(oauth2AccessToken.getTokenType());
        log.info(String.valueOf(oauth2AccessToken.getExpiration()));
        log.info(String.valueOf(oauth2AccessToken.getScope()));
        log.info(String.valueOf(oauth2AccessToken.getAdditionalInformation()));
        log.info(String.valueOf(oauth2AccessToken.getExpiresIn()));
        log.info(String.valueOf(oauth2AccessToken.getRefreshToken()));

//        // 以下为使用 String 接收数据
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class, map);
//        HttpStatus statusCode = responseEntity.getStatusCode();
//        String body = responseEntity.getBody();
//        log.info(String.valueOf(statusCode));
//        log.info(body);

        String checkTokenUrl = "http://authorization-server.xuxiaowei.cloud:1301/oauth/check_token?token=" + oauth2AccessToken.getValue();

        System.out.println();

        // 检查 Token
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> checkTokenResponseEntity = restTemplate.postForEntity(checkTokenUrl, httpEntity, Map.class);
        log.info(String.valueOf(checkTokenResponseEntity.getStatusCode()));
        log.info(String.valueOf(checkTokenResponseEntity.getBody()));
    }

}
