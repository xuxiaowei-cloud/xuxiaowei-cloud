package cloud.xuxiaowei.gateway.point;

import cloud.xuxiaowei.gateway.filter.LogGlobalFilter;
import cloud.xuxiaowei.log.service.ILogService;
import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.RequestUtils;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.server.BearerTokenServerAuthenticationEntryPoint;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import static cloud.xuxiaowei.utils.Constant.IP;
import static cloud.xuxiaowei.utils.Constant.REQUEST_ID;

/**
 * 异常服务器认证入口点
 *
 * @author xuxiaowei
 * @see BearerTokenServerAuthenticationEntryPoint
 * @since 0.0.1
 */
@Slf4j
@Component
public class ExceptionServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private ILogService logService;

    @Autowired
    public void setLogService(ILogService logService) {
        this.logService = logService;
    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {

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

        Response<?> error = Response.error(CodeEnums.T10001.code, CodeEnums.T10001.msg);
        error.setRequestId(requestId);

        if (ex instanceof AuthenticationCredentialsNotFoundException) {
            error.setCode(CodeEnums.T10003.code);
            error.setMsg(CodeEnums.T10003.msg);
        } else {
            error.setExplain("异常代码待划分");
        }

        return ResponseUtils.writeWith(response, error);
    }

}
