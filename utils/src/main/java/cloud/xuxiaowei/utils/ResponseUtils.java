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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    /**
     * 响应数据
     *
     * @param response 响应
     * @param json     String 类型的数据
     * @throws IOException IO 异常
     */
    public static void response(HttpServletResponse response, String json) throws IOException {
        response(response, (Object) json);
    }

    /**
     * 响应数据
     *
     * @param response 响应
     * @param map      Map 类型的数据
     * @throws IOException IO 异常
     */
    @SuppressWarnings("rawtypes")
    public static void response(HttpServletResponse response, Map map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(map);
        response(response, json);
    }

    /**
     * 响应数据
     *
     * @param response 响应
     * @param object   Object 类型的数据
     * @throws IOException IO 异常
     */
    @SuppressWarnings({"deprecation"})
    public static void response(HttpServletResponse response, Object object) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(object);
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
    }

}
