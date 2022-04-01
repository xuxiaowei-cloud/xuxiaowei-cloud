package cloud.xuxiaowei.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 请求 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
public class RequestUtils {

    /**
     * 获取 Headers Map
     *
     * @param request 请求
     * @return 返回 Headers Map
     */
    public static Map<String, List<String>> getHeadersMap(HttpServletRequest request) {
        Map<String, List<String>> map = new HashMap<>(8);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            List<String> headerValuesList = Collections.list(headerValues);
            map.put(headerName, headerValuesList);
        }
        return map;
    }

    /**
     * 获取 Headers Map
     *
     * @param request 请求
     * @return 返回 Headers Map
     */
    public static Map<String, List<String>> getHeadersMap(ServerHttpRequest request) {
        Map<String, List<String>> map = new HashMap<>(8);
        HttpHeaders httpHeaders = request.getHeaders();
        for (String headerName : httpHeaders.keySet()) {
            List<String> headerValues = httpHeaders.get(headerName);
            map.put(headerName, headerValues);
        }
        return map;
    }

    /**
     * 获取 Headers JSON
     *
     * @param request 请求
     * @return 返回 Headers JSON
     */
    public static String getHeadersJson(HttpServletRequest request) {
        Map<String, List<String>> headersMap = getHeadersMap(request);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(headersMap);
        } catch (JsonProcessingException e) {
            log.error("使用jackson将请求头处理为JSON异常：", e);
        }
        return null;
    }

    public static String getHeadersJson(ServerHttpRequest request) {
        Map<String, List<String>> headersMap = getHeadersMap(request);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(headersMap);
        } catch (JsonProcessingException e) {
            log.error("使用jackson将请求头处理为JSON异常：", e);
        }
        return null;
    }

    /**
     * 获取标识
     *
     * @param request 请求
     * @return 返回 标识
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.USER_AGENT);
    }

    /**
     * 获取标识
     *
     * @param request 请求
     * @return 返回 标识
     */
    public static String getUserAgent(ServerHttpRequest request) {
        HttpHeaders httpHeaders = request.getHeaders();
        return httpHeaders.getFirst(HttpHeaders.USER_AGENT);
    }

}
