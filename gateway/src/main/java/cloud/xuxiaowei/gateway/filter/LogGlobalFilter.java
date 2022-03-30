package cloud.xuxiaowei.gateway.filter;

import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 日志 过滤器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Component
public class LogGlobalFilter implements GlobalFilter, Ordered {

    public static final int ORDERED = Ordered.HIGHEST_PRECEDENCE;

    @Setter
    private int order = ORDERED;

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }

}
