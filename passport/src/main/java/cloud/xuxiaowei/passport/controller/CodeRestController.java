package cloud.xuxiaowei.passport.controller;

import cloud.xuxiaowei.core.oauth2.OAuth2AccessToken;
import cloud.xuxiaowei.core.properties.CloudClientProperties;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.ResponseUtils;
import cloud.xuxiaowei.utils.map.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    /**
     * 在这里只使用 {@link RestTemplate} 而不使用 <code>@FeignClient</code>，
     * 原因是：本服务调用其他服务较少，单独引入 <code>@FeignClient</code> 并不合适
     */
    private RestTemplate restTemplate;

    @Autowired
    public void setCloudClientProperties(CloudClientProperties cloudClientProperties) {
        this.cloudClientProperties = cloudClientProperties;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 根据 授权码、状态码 获取 Token
     *
     * @param request  请求
     * @param response 响应
     * @param session  Session，不存在时自动创建
     * @param code     授权码
     * @param state    状态码
     * @throws IOException 重定向异常
     */
    @RequestMapping(params = {"code", "state"})
    private void index(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                       String code, String state) throws IOException {

        String sessionState = session.getAttribute(cloudClientProperties.getStateName()) + "";
        if (!StringUtils.hasText(sessionState) || !sessionState.equals(state)) {
            Response<?> error = Response.error("非法获取Token");
            ResponseUtils.response(response, error);
            return;
        }

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

        String homePage = cloudClientProperties.getHomePage();

        assert oauth2AccessToken != null;
        String accessToken = oauth2AccessToken.getAccessToken();

        response.sendRedirect(homePage + "?accessToken=" + accessToken);
    }

    /**
     * 错误
     *
     * @param request          请求
     * @param response         响应
     * @param session          Session，不存在时自动创建
     * @param error            错误类型
     * @param errorDescription 错误描述
     * @param state            状态码
     * @return 返回 错误原因
     */
    @RequestMapping(params = {"error", "error_description", "state"})
    private Response<?> errorState(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                   String error, @RequestParam("error_description") String errorDescription,
                                   String code, String msg, String data, String explain, String field, String requestId,
                                   String scope, String state) {

        ResponseMap responseMap = ResponseMap.error()
                .put("error", error)
                .put("error_description", errorDescription)
                .put("data", data)
                .put("explain", explain)
                .put("field", field)
                .put("requestId", requestId)
                .put("scope", scope)
                .put("state", state);

        if (StringUtils.hasText(code)) {
            responseMap.setCode(code);
        }
        if (StringUtils.hasText(msg)) {
            responseMap.setMsg(msg);
        }

        return responseMap;
    }

    /**
     * 错误
     *
     * @param request          请求
     * @param response         响应
     * @param session          Session，不存在时自动创建
     * @param error            错误类型
     * @param errorDescription 错误描述
     * @return 返回 错误原因
     */
    @RequestMapping(params = {"error", "error_description"})
    private Response<?> error(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                              String error, @RequestParam("error_description") String errorDescription,
                              String code, String msg, String data, String explain, String field, String requestId,
                              String scope) {

        return errorState(request, response, session,
                error, errorDescription,
                code, msg, data, explain, field, requestId,
                scope, null);
    }

}
