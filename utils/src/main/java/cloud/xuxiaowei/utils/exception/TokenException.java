package cloud.xuxiaowei.utils.exception;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * Token 异常父类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class TokenException extends CloudException {

    public TokenException() {
        super(CodeEnums.T10000.code, CodeEnums.T10000.msg);
    }

    public TokenException(String msg) {
        super(CodeEnums.T10000.code, msg);
    }

    public TokenException(String code, String msg) {
        super(code, msg);
    }

    public TokenException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public TokenException(String code, String msg, String field, Throwable cause) {
        super(code, msg, field, cause);
    }

    public TokenException(String code, String msg, String field, String explain, Throwable cause) {
        super(code, msg, field, explain, cause);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }

    public TokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
