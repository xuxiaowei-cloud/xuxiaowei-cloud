package cloud.xuxiaowei.utils.exception.client;

import cloud.xuxiaowei.utils.CodeEnums;
import lombok.Getter;
import lombok.Setter;

/**
 * 授权码 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class AuthorizationCodeException extends ClientException {

    @Getter
    @Setter
    private String authorizationCode;

    public AuthorizationCodeException() {
        this(CodeEnums.C20006.code, CodeEnums.C20006.msg);
    }

    public AuthorizationCodeException(String msg) {
        this(CodeEnums.C20006.code, msg);
    }

    public AuthorizationCodeException(String code, String msg) {
        super(code, msg);
    }

    public AuthorizationCodeException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public AuthorizationCodeException(String code, String msg, String field, Throwable cause) {
        super(code, msg, field, cause);
    }

    public AuthorizationCodeException(String code, String msg, String field, String explain, Throwable cause) {
        super(code, msg, field, explain, cause);
    }

    public AuthorizationCodeException(Throwable cause) {
        this(CodeEnums.C20006.code, CodeEnums.C20006.msg, cause);
    }

    public AuthorizationCodeException(String msg, Throwable cause) {
        this(CodeEnums.C20006.code, msg, cause);
    }

}
