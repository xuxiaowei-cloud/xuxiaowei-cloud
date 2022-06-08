package cloud.xuxiaowei.system.filter;

import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.SecurityUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter 执行之后 过滤器
 * <p>
 * 在日志中添加用户标识
 * <p>
 * 优先级高于 -100（即：数值小于 -100），将无效
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Order(-100)
@Component
public class BearerAfterHttpFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String userName = SecurityUtils.getUserName();

        MDC.put(Constant.NAME, userName);

        super.doFilter(req, res, chain);
    }

}
