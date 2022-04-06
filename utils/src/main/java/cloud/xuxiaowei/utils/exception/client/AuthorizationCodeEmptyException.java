package cloud.xuxiaowei.utils.exception.client;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * 授权码 为空 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class AuthorizationCodeEmptyException extends AuthorizationCodeException {

    public AuthorizationCodeEmptyException() {
        this(CodeEnums.C10006.code, CodeEnums.C10006.msg);
    }

    public AuthorizationCodeEmptyException(String msg) {
        this(CodeEnums.C10006.code, msg);
    }

    public AuthorizationCodeEmptyException(String code, String msg) {
        super(code, msg);
    }

    public AuthorizationCodeEmptyException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public AuthorizationCodeEmptyException(String code, String msg, String field, Throwable cause) {
        super(code, msg, field, cause);
    }

    public AuthorizationCodeEmptyException(String code, String msg, String field, String explain, Throwable cause) {
        super(code, msg, field, explain, cause);
    }

    public AuthorizationCodeEmptyException(Throwable cause) {
        this(CodeEnums.C10006.code, CodeEnums.C10006.msg, cause);
    }

    public AuthorizationCodeEmptyException(String msg, Throwable cause) {
        this(CodeEnums.C10006.code, msg, cause);
    }

}
