package cloud.xuxiaowei.oauth2client.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

/**
 * {@link ClientCredentialsAccessTokenProvider} 示例
 *
 * @author xuxiaowei
 * @see ClientCredentialsResourceDetails
 * @since 0.0.1
 */
@Slf4j
class ClientCredentialsAccessTokenProviderTests {

    /**
     * 客户端凭证模式：client_credentials
     */
    @Test
    void clientCredentials() {

        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setClientId("xuxiaowei_client_id");
        clientCredentialsResourceDetails.setClientSecret("xuxiaowei_client_secret");
        clientCredentialsResourceDetails.setScope(Arrays.asList("snsapi_base", "snsapi_userinfo"));
        clientCredentialsResourceDetails.setGrantType("client_credentials");
        clientCredentialsResourceDetails.setAccessTokenUri("http://authorization-server.xuxiaowei.cloud:1301/oauth/token");

        ClientCredentialsAccessTokenProvider clientCredentialsAccessTokenProvider = new ClientCredentialsAccessTokenProvider();
        OAuth2AccessToken oauth2AccessToken = clientCredentialsAccessTokenProvider.obtainAccessToken(clientCredentialsResourceDetails, null);

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

        String checkTokenUrl = "http://authorization-server.xuxiaowei.cloud:1301/oauth/check_token?token=" + oauth2AccessToken.getValue();

        // 检查 Token
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> checkTokenResponseEntity = restTemplate.postForEntity(checkTokenUrl, httpEntity, Map.class);
        log.info(String.valueOf(checkTokenResponseEntity.getStatusCode()));
        log.info(String.valueOf(checkTokenResponseEntity.getBody()));
    }

}
