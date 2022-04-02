package cloud.xuxiaowei.authorizationserver.controller;

import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelErrorEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 用于显示授权服务器的错误页面（响应）。
 *
 * @author xuxiaowei
 * @see WhitelabelErrorEndpoint
 * @since 0.0.1
 */
@Controller
public class ConfirmErrorController {

    private static final String ERROR = "<html><body><h1>OAuth 错误</h1><p>%errorSummary%</p></body></html>";

    /**
     * 自定义 用于显示授权服务器的错误页面（响应）。
     *
     * @see WhitelabelErrorEndpoint
     * @see SessionAttributes
     */
    @RequestMapping("/oauth/customize_error")
    public ModelAndView customizeConfirmAccess(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam(value = "client_id", required = false) String clientId,
                                               @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                                               @RequestParam(value = "response_type", required = false) String responseType,
                                               String scope, String state) {

        Map<String, Object> model = new HashMap<>(4);
        Object error = request.getAttribute("error");

        // 错误摘要可能包含恶意用户输入，
        // 它需要被转义以防止XSS
        String errorSummary;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;

            oauthError.addAdditionalInformation("client_id", clientId);
            oauthError.addAdditionalInformation("redirect_uri", redirectUri);
            oauthError.addAdditionalInformation("response_type", responseType);
            oauthError.addAdditionalInformation("scope", scope);
            oauthError.addAdditionalInformation("state", state);

            errorSummary = HtmlUtils.htmlEscape(oauthError.getSummary());
        } else {
            errorSummary = "Unknown error";
        }
        String errorContent = ERROR.replace("%errorSummary%", errorSummary);
        View errorView = new View() {
            @Override
            public String getContentType() {
                return "text/html;charset=UTF-8";
            }

            @Override
            public void render(Map<String, ?> model, @NonNull HttpServletRequest request, HttpServletResponse response) throws Exception {
                response.setContentType(getContentType());
                response.getWriter().append(errorContent);
            }
        };
        return new ModelAndView(errorView, model);
    }

}
