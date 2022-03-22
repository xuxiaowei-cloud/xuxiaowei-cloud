package cloud.xuxiaowei.oauth2client.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

/**
 * {@link OAuth2RestTemplate} 示例
 *
 * @author xuxiaowei
 * @see OAuth2ProtectedResourceDetails
 * @see ClientCredentialsResourceDetails
 * @since 0.0.1
 */
@Slf4j
class OAuth2RestTemplateTests {

    /**
     * 凭证式：client_credentials
     */
    @Test
    void clientCredentials() {

        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setClientId("xuxiaowei_client_id");
        clientCredentialsResourceDetails.setClientSecret("xuxiaowei_client_secret");
        clientCredentialsResourceDetails.setScope(Arrays.asList("snsapi_base", "snsapi_userinfo"));
        clientCredentialsResourceDetails.setGrantType("client_credentials");
        clientCredentialsResourceDetails.setAccessTokenUri("http://authorization-server.xuxiaowei.cloud:1301/oauth/token");

        OAuth2RestTemplate oauth2RestTemplate = new OAuth2RestTemplate(clientCredentialsResourceDetails);
        OAuth2AccessToken oauth2AccessToken = oauth2RestTemplate.getAccessToken();

        log.info(oauth2AccessToken.getValue());
        log.info(oauth2AccessToken.getTokenType());
        log.info(String.valueOf(oauth2AccessToken.getExpiration()));
        log.info(String.valueOf(oauth2AccessToken.getScope()));
        log.info(String.valueOf(oauth2AccessToken.getAdditionalInformation()));
        log.info(String.valueOf(oauth2AccessToken.getExpiresIn()));
        log.info(String.valueOf(oauth2AccessToken.getRefreshToken()));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        String checkTokenUrl  = "http://authorization-server.xuxiaowei.cloud:1301/oauth/check_token?token=" + oauth2AccessToken.getValue();

        // 检查 Token
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> checkTokenResponseEntity = restTemplate.postForEntity(checkTokenUrl, httpEntity, Map.class);
        log.info(String.valueOf(checkTokenResponseEntity.getStatusCode()));
        log.info(String.valueOf(checkTokenResponseEntity.getBody()));
    }

}
