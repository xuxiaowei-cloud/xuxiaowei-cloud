package cloud.xuxiaowei.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

/**
 * 安全 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class SecurityUtils {

    /**
     * 根据 验证 获取 用户名
     *
     * @param authentication 验证
     * @return 返回 用户名
     */
    public static String getUserName(Authentication authentication) {
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Jwt) {
                Jwt jwt = (Jwt) principal;
                Map<String, Object> claims = jwt.getClaims();
                Object userName = claims.get("user_name");
                if (userName == null) {
                    return null;
                }
                return userName.toString();
            }
        }
        return null;
    }

}
