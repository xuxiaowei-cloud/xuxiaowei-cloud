package cloud.xuxiaowei.authorizationserver.controller;

import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 确认访问授权 Controller
 * <p>
 * 用于显示授权服务器的批准页面。
 *
 * @author xuxiaowei
 * @see SessionAttributes
 * @since 0.0.1
 */
@Controller
@SuppressWarnings({"deprecation"})
@SessionAttributes("authorizationRequest")
public class ConfirmAccessController {

    /**
     * 静默授权
     */
    private static final String SNSAPI_BASE = "snsapi_base";

    /**
     * 确认访问授权 页面
     *
     * @see WhitelabelApprovalEndpoint
     * @see SessionAttributes
     */
    @ControllerAnnotation(description = "确认访问授权 页面")
    @RequestMapping("/oauth/customize_confirm_access")
    public String customizeConfirmAccess(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, Object> map = model.asMap();
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) map.get("authorizationRequest");
        Set<String> scopes = authorizationRequest.getScope();
        String clientId = authorizationRequest.getClientId();

        Object csrf = request.getAttribute("_csrf");
        // csrf 未关闭
        if (csrf instanceof CsrfToken) {
            CsrfToken csrfToken = (CsrfToken) csrf;
            model.addAttribute("_csrf", csrfToken.getToken());
        }

        model.addAttribute("clientId", clientId);
        model.addAttribute("scopes", scopes);

        List<Boolean> scopeOptions = new ArrayList<>();
        for (int i = 0; i < scopes.size(); i++) {
            scopeOptions.add(Boolean.TRUE);
        }
        model.addAttribute("scopeOptions", scopeOptions);

        // 静默授权的情况判断
        if (scopes.size() == 1 && scopes.contains(SNSAPI_BASE)) {
            model.addAttribute("silentAuth", true);
        }

        return "oauth/customize_confirm_access";
    }

}
