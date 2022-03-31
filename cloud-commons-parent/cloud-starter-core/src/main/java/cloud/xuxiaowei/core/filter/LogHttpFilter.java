package cloud.xuxiaowei.core.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static cloud.xuxiaowei.utils.Constant.IP;
import static cloud.xuxiaowei.utils.Constant.REQUEST_ID;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * 日志 过滤器
 * <p>
 * 用户在日志输出时，动态添加指定的信息，如：用户唯一标识，IP等
 * <p>
 * 最高优先级
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Order(HIGHEST_PRECEDENCE)
@Component
public class LogHttpFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String remoteHost = req.getRemoteHost();
        MDC.put(IP, remoteHost);

        String requestId = req.getHeader(REQUEST_ID);
        if (requestId == null) {
            MDC.put(REQUEST_ID, UUID.randomUUID().toString());
        } else {
            MDC.put(REQUEST_ID, requestId);
        }

        super.doFilter(req, res, chain);
    }

}
