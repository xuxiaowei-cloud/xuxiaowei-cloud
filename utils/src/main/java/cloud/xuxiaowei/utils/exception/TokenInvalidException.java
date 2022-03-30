package cloud.xuxiaowei.utils.exception;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * Token 无效 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class TokenInvalidException extends TokenException {

    public TokenInvalidException() {
        super(CodeEnums.T10001.code, CodeEnums.T10001.msg);
    }

    public TokenInvalidException(String msg) {
        super(CodeEnums.T10001.code, msg);
    }

    public TokenInvalidException(String code, String msg) {
        super(code, msg);
    }

    public TokenInvalidException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public TokenInvalidException(String code, String msg, String field, Throwable cause) {
        super(code, msg, field, cause);
    }

    public TokenInvalidException(String code, String msg, String field, String explain, Throwable cause) {
        super(code, msg, field, explain, cause);
    }

    public TokenInvalidException(Throwable cause) {
        super(cause);
    }

    public TokenInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
