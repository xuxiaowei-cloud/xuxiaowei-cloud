package cloud.xuxiaowei.gateway.handler;

import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.ResponseUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.net.ConnectException;

/**
 * 网关 异常 响应处理 {@link WebExceptionHandler}
 * <p>
 * 优先级：必填<br>
 * 大于 0：不正常<br>
 * 小于等于 0：正常<br>
 *
 * <code>org.springframework.web.reactive.function.client.DefaultClientResponseBuilder#body(String)</code>
 *
 * @author xuxiaowei
 * @see Mono#empty()
 * @see Mono#error(Throwable)
 * @since 0.0.1
 */
@Slf4j
@Component
public class GatewayErrorWebExceptionHandler implements ErrorWebExceptionHandler, Ordered {

    public static final int ORDERED = Ordered.HIGHEST_PRECEDENCE + 10000;

    @Setter
    private int order = ORDERED;

    @Override
    public int getOrder() {
        return order;
    }

    @NonNull
    @Override
    @SuppressWarnings({"deprecation"})
    public Mono<Void> handle(@NonNull ServerWebExchange exchange, @NonNull Throwable ex) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        MediaType contentType = request.getHeaders().getContentType();

        if (contentType == null) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        } else if (contentType.equals(MediaType.APPLICATION_JSON)) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        } else {
            response.getHeaders().setContentType(contentType);
        }

        response.getHeaders().setAccept(request.getHeaders().getAccept());
        response.setStatusCode(HttpStatus.OK);

        String requestId = request.getId();
        log.error(requestId, ex);

        Response<?> error = Response.error(CodeEnums.S10000.code, CodeEnums.S10000.msg);
        error.setRequestId(requestId);

        if (ex instanceof ResponseStatusException) {
            ResponseStatusException rse = (ResponseStatusException) ex;
            HttpStatus status = rse.getStatus();
            if (status.value() == HttpStatus.NOT_FOUND.value()) {
                error.setCode(CodeEnums.S10001.code);
                error.setMsg(CodeEnums.S10001.msg);
            } else {
                error.setExplain("异常代码待划分");
            }
        } else if (ex instanceof ConnectException) {
            error.setCode(CodeEnums.S10002.code);
            error.setMsg(CodeEnums.S10002.msg);
        }

        return ResponseUtils.writeWith(response, error);
    }

}
