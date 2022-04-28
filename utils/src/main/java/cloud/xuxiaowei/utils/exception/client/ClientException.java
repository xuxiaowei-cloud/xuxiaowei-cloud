package cloud.xuxiaowei.utils.exception.client;

import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.exception.CloudException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 客户端 异常父类
 * 注意：<p>
 * 1、{@link ClientException} 及其子异常不属于 {@link CloudException} 异常的子类<p>
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ClientException extends OAuth2Exception {

    /**
     * 错误码
     */
    @Getter
    protected String code;

    /**
     * 中文描述
     */
    @Getter
    protected String msg;

    /**
     * 错误字段
     * <p>
     * 存在多个时，使用英文逗号隔开
     */
    @Getter
    protected String field;

    /**
     * 说明
     */
    @Getter
    protected String explain;

    public ClientException() {
        super(CodeEnums.C10000.msg);
        this.code = CodeEnums.C10000.code;
        this.msg = CodeEnums.C10000.msg;
    }

    public ClientException(String msg) {
        super(msg);
        this.code = CodeEnums.C10000.code;
        this.msg = msg;
    }

    public ClientException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ClientException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public ClientException(String code, String msg, String field, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
        this.field = field;
    }

    public ClientException(String code, String msg, String field, String explain, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
        this.field = field;
        this.explain = explain;
    }

    public ClientException(Throwable cause) {
        super(CodeEnums.C10000.msg, cause);
        this.code = CodeEnums.C10000.code;
        this.msg = CodeEnums.C10000.msg;
    }

    public ClientException(String msg, Throwable cause) {
        super(msg, cause);
        this.code = CodeEnums.C10000.code;
        this.msg = msg;
    }

}
