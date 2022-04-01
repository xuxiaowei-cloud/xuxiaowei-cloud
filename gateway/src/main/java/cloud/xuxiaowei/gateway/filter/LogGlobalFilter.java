package cloud.xuxiaowei.gateway.filter;

import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.ResponseUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import static cloud.xuxiaowei.utils.Constant.IP;

/**
 * 日志 过滤器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class LogGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 最低优先级（最大值）：0
     * <p>
     * 大于 0 无效
     */
    public static final int ORDERED = Ordered.HIGHEST_PRECEDENCE;

    @Setter
    private int order = ORDERED;

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String requestId = request.getId();
        MDC.put(Response.REQUEST_ID, requestId);

        InetSocketAddress remoteAddress = request.getRemoteAddress();
        if (remoteAddress == null) {
            Response<?> error = Response.error(CodeEnums.X10003.code, CodeEnums.X10003.msg);

            return ResponseUtils.writeWith(response, error);
        }

        InetAddress address = remoteAddress.getAddress();
        String hostAddress = address.getHostAddress();
        MDC.put(IP, hostAddress);

        return chain.filter(exchange);
    }

}
