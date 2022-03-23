package cloud.xuxiaowei.gateway.filter;

import cloud.xuxiaowei.core.properties.CloudBlackListProperties;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 黑名单 过滤器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Component
public class BlackListGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 最低优先级（最大值）：0
     * <p>
     * 大于 0 无效
     */
    public static final int DEFAULT_ORDER = 0;

    private CloudBlackListProperties cloudBlackListProperties;

    @Autowired
    public void setCloudBlackListProperties(CloudBlackListProperties cloudBlackListProperties) {
        this.cloudBlackListProperties = cloudBlackListProperties;
    }

    @Setter
    private int order = DEFAULT_ORDER;

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }

}
