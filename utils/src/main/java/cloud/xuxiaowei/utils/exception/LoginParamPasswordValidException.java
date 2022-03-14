package cloud.xuxiaowei.utils.exception;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * 登录参数密码验证 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class LoginParamPasswordValidException extends LoginParamValidException {

    public LoginParamPasswordValidException() {
        super(CodeEnums.A1202.code, CodeEnums.A1202.msg);
    }

    public LoginParamPasswordValidException(String msg) {
        super(CodeEnums.A1202.code, msg);
    }

    public LoginParamPasswordValidException(String code, String msg) {
        super(code, msg);
    }

    public LoginParamPasswordValidException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public LoginParamPasswordValidException(String code, String msg, String field, Throwable cause) {
        super(code, msg, field, cause);
    }

    public LoginParamPasswordValidException(String code, String msg, String field, String explain, Throwable cause) {
        super(code, msg, field, explain, cause);
    }

    public LoginParamPasswordValidException(Throwable cause) {
        super(CodeEnums.A1202.msg, cause);
        this.code = CodeEnums.A1202.code;
        this.msg = CodeEnums.A1202.msg;
    }

    public LoginParamPasswordValidException(String msg, Throwable cause) {
        super(msg, cause);
        this.code = CodeEnums.A1202.code;
        this.msg = msg;
    }

}
