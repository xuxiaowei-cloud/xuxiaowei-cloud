package cloud.xuxiaowei.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * 响应 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class ResponseUtils {

    /**
     * 使用 Mono 响应数据
     *
     * @param response 响应
     * @param object   数据
     * @return 返回 响应结果
     */
    @SneakyThrows
    public static Mono<Void> writeWith(ServerHttpResponse response, Object object) {

        // 响应状态码
        response.setStatusCode(HttpStatus.OK);

        // 不可使用 APPLICATION_JSON（部分浏览器会乱码）
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        @SuppressWarnings("all")
        byte[] bytes = objectMapper.writeValueAsBytes(object);

        DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(dataBuffer));
    }

}
