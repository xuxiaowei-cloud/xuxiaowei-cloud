package cloud.xuxiaowei.gateway.handler;

import cloud.xuxiaowei.gateway.filter.LogGlobalFilter;
import cloud.xuxiaowei.log.service.ILogService;
import cloud.xuxiaowei.utils.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;

import static cloud.xuxiaowei.utils.Constant.IP;
import static cloud.xuxiaowei.utils.Constant.REQUEST_ID;

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

    private ILogService logService;

    @Autowired
    public void setLogService(ILogService logService) {
        this.logService = logService;
    }

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
        HttpHeaders headers = response.getHeaders();

        // 解决服务未发现时跨域问题
        // Access to XMLHttpRequest at '……' from origin '……' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
        headers.setAccessControlAllowOrigin(RequestUtils.getOrigin(request));
        // Access to XMLHttpRequest at '……' from origin '……' has been blocked by CORS policy: The value of the 'Access-Control-Allow-Credentials' header in the response is '' which must be 'true' when the request's credentials mode is 'include'. The credentials mode of requests initiated by the XMLHttpRequest is controlled by the withCredentials attribute.
        headers.setAccessControlAllowCredentials(true);

        // 日志中放入请求ID
        String requestId = request.getId();
        MDC.put(REQUEST_ID, requestId);

        // 请求中放入用户IP
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        if (remoteAddress == null) {
            Response<?> error = Response.error(CodeEnums.X10003.code, CodeEnums.X10003.msg);
            return ResponseUtils.writeWith(response, error);
        }
        InetAddress address = remoteAddress.getAddress();
        String hostName = address.getHostName();
        String hostAddress = address.getHostAddress();
        MDC.put(IP, hostAddress);

        LogGlobalFilter.save(logService, request, hostAddress, hostName, ex);

        MediaType contentType = request.getHeaders().getContentType();

        if (contentType == null) {
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        } else if (contentType.equals(MediaType.APPLICATION_JSON)) {
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        } else {
            headers.setContentType(contentType);
        }

        headers.setAccept(request.getHeaders().getAccept());
        response.setStatusCode(HttpStatus.OK);

        log.error(requestId, ex);

        Response<?> error = Response.error(CodeEnums.S10000.code, CodeEnums.S10000.msg);
        error.setRequestId(requestId);

        if (ex instanceof ResponseStatusException) {
            ResponseStatusException rse = (ResponseStatusException) ex;
            HttpStatus status = rse.getStatus();
            if (status.value() == HttpStatus.NOT_FOUND.value()) {
                error.setCode(CodeEnums.S10001.code);

                URI uri = request.getURI();
                String path = uri.getPath();
                String[] split = path.split("/");
                if (split.length < 1) {
                    error.setMsg(CodeEnums.S10001.msg);
                } else {
                    String service = split[1];
                    ServiceEnums serviceEnums = ServiceEnums.getEnum(service);
                    if (serviceEnums == null) {
                        error.setMsg(CodeEnums.S10001.msg);
                    } else {
                        error.setMsg(CodeEnums.S10001.msg + "：" + serviceEnums.name);
                    }
                }

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
