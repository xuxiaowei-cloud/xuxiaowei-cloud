package cloud.xuxiaowei.authorizationserver.controller;

import cloud.xuxiaowei.utils.Response;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelErrorEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param request      请求
     * @param response     响应
     * @param clientId     客户ID
     * @param redirectUri  重定向URI
     * @param responseType 响应类型
     * @param scope        范围
     * @param state        状态码
     * @param model        页面中的值
     * @return 错误页面
     * @see WhitelabelErrorEndpoint
     * @see SessionAttributes
     */
    @RequestMapping("/oauth/customize_error")
    public String customizeConfirmAccess(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(value = "client_id", required = false) String clientId,
                                         @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                                         @RequestParam(value = "response_type", required = false) String responseType,
                                         String scope, String state, Model model) {

        Object error = request.getAttribute("error");

        // 错误摘要可能包含恶意用户输入，
        // 它需要被转义以防止XSS
        String errorSummary;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;

            model.addAttribute("oauth2ErrorCode", oauthError.getOAuth2ErrorCode());
            model.addAttribute("message", oauthError.getMessage());

            model.addAttribute("httpErrorCode", oauthError.getHttpErrorCode());

            model.addAttribute("message", oauthError.getMessage());

            Map<String, String> additionalInformation = oauthError.getAdditionalInformation();

            if (additionalInformation != null) {
                model.addAttribute(Response.CODE, additionalInformation.get(Response.CODE));
                model.addAttribute(Response.MSG, additionalInformation.get(Response.MSG));
                model.addAttribute(Response.DATA, additionalInformation.get(Response.DATA));
                model.addAttribute(Response.FIELD, additionalInformation.get(Response.FIELD));
                model.addAttribute(Response.EXPLAIN, additionalInformation.get(Response.EXPLAIN));
                model.addAttribute(Response.REQUEST_ID, additionalInformation.get(Response.REQUEST_ID));
            }

            errorSummary = "OAuth2Exception";
        } else {
            errorSummary = "未知错误，需要增加错误类型判断";
            model.addAttribute("error", error);
        }

        model.addAttribute("errorSummary", errorSummary);

        return "oauth/customize_error";
    }

}
