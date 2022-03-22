package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.core.oauth2.OAuth2AccessToken;
import cloud.xuxiaowei.core.properties.CloudClientProperties;
import cloud.xuxiaowei.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权码 Code {@link RestController}
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/code")
public class CodeRestController {

    private CloudClientProperties cloudClientProperties;

    @Autowired
    public void setCloudClientProperties(CloudClientProperties cloudClientProperties) {
        this.cloudClientProperties = cloudClientProperties;
    }

    /**
     * 根据 授权码、状态码 获取 Token
     *
     * @param request  请求
     * @param response 响应
     * @param code     授权码
     * @param state    状态码
     * @return 返回 Token
     */
    @RequestMapping(params = {"code", "state"})
    private Response<?> index(HttpServletRequest request, HttpServletResponse response,
                              String code, String state) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> map = new HashMap<>(8);
        map.put("code", code);
        map.put("state", state);
        map.put("client_id", cloudClientProperties.getClientId());
        map.put("client_secret", cloudClientProperties.getClientSecret());
        map.put("redirect_uri", cloudClientProperties.getRedirectUri());

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        OAuth2AccessToken oauth2AccessToken = restTemplate.postForObject(cloudClientProperties.accessTokenUri(),
                httpEntity, OAuth2AccessToken.class, map);

        return Response.ok(oauth2AccessToken);
    }

}
