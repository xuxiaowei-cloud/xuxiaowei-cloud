package cloud.xuxiaowei.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应数据
 *
 * @param <T> 泛型
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(String code, String msg, String field) {
        this.code = code;
        this.msg = msg;
        this.field = field;
    }

    public static Response<?> ok() {
        return new Response<>(CodeEnums.OK.code, CodeEnums.OK.msg);
    }

    public static Response<?> ok(String msg) {
        return new Response<>(CodeEnums.OK.code, msg);
    }

    public static <T> Response<?> ok(T data) {
        Response<T> response = new Response<>(CodeEnums.OK.code, CodeEnums.OK.msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<?> ok(T data, String msg) {
        Response<T> response = new Response<>(CodeEnums.OK.code, CodeEnums.OK.msg);
        response.setData(data);
        return response;
    }

    public static Response<?> error() {
        return new Response<>(CodeEnums.ERROR.code, CodeEnums.ERROR.msg);
    }

    public static Response<?> error(String msg) {
        return new Response<>(CodeEnums.ERROR.code, msg);
    }

    public static Response<?> error(String code, String msg) {
        return new Response<>(code, msg);
    }

    /**
     * 响应代码
     *
     * @see CodeEnums#code
     */
    private String code;

    /**
     * 响应消息
     *
     * @see CodeEnums#msg
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 错误字段
     * <p>
     * 存在多个时，使用英文逗号隔开
     */
    private String field;

    /**
     * 说明
     */
    private String explain;

    /**
     * 请求ID
     * <p>
     * 只有 Getter 方法，无 Setter 方法
     */
    @Setter(value = AccessLevel.NONE)
    private String requestId;

}
