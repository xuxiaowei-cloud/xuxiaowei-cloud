package cloud.xuxiaowei.passport.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/login")
public class LoginRestController {

    /**
     * 登录失败
     *
     * @param request  请求
     * @param response 响应
     * @param session  Session，不存在时自动创建
     * @return 返回 登录失败提示语
     */
    @RequestMapping("/failure")
    public Map<String, Object> failure(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Map<String, Object> map = new HashMap<>(4);

        String referer = request.getHeader("Referer");

        // Spring Security 最后一次异常
        // 跨域时，需要 Session 共享才能获取到
        Object springSecurityLastException = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        // Session 创建时间
        long creationTime = session.getCreationTime();
        // 最后一次访问时间
        long lastAccessedTime = session.getLastAccessedTime();
        // 最大非活动时间
        int maxInactiveInterval = session.getMaxInactiveInterval();

        map.put("code", "500");
        map.put("msg", "登录失败");

        return map;
    }

    /**
     * 登录成功
     *
     * @param request  请求
     * @param response 响应
     * @return 返回 登录成功提示语
     */
    @RequestMapping("/success")
    public Map<String, Object> success(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", "200");
        map.put("msg", "登录成功");
        return map;
    }

}
