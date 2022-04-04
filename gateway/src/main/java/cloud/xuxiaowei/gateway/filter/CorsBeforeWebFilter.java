package cloud.xuxiaowei.gateway.filter;

import cloud.xuxiaowei.utils.RequestUtils;
import cloud.xuxiaowei.utils.ServiceEnums;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.DefaultCorsProcessor;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 在 CORS 之前执行
 *
 * @author xuxiaowei
 * @see DefaultCorsProcessor#process(CorsConfiguration, ServerWebExchange)
 * @since 0.0.1
 */
public class CorsBeforeWebFilter implements WebFilter {

    public static final List<String> NOT_EXIST_ORIGIN_LIST = new ArrayList<>();

    static {
        NOT_EXIST_ORIGIN_LIST.add(String.format("/%s/oauth/authorize", ServiceEnums.AUTHORIZATION_SERVER.value));
    }

    @NonNull
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        URI uri = request.getURI();
        String path = uri.getPath();

        // 解决 form action 提交数据无 origin 跨域问题
        if (NOT_EXIST_ORIGIN_LIST.contains(path)) {
            String schemeHost = RequestUtils.getSchemeHost(request);
            response.getHeaders().setAccessControlAllowOrigin(schemeHost);
        }

        return chain.filter(exchange);
    }

}
