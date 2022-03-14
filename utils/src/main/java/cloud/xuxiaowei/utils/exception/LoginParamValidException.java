package cloud.xuxiaowei.utils.exception;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * 登录参数验证 异常父类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class LoginParamValidException extends LoginException {

    public LoginParamValidException() {
        super(CodeEnums.A1200.code, CodeEnums.A1200.msg);
    }

    public LoginParamValidException(String msg) {
        super(CodeEnums.A1200.code, msg);
    }

    public LoginParamValidException(String code, String msg) {
        super(code, msg);
    }

    public LoginParamValidException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public LoginParamValidException(String code, String msg, String field, Throwable cause) {
        super(code, msg, field, cause);
    }

    public LoginParamValidException(String code, String msg, String field, String explain, Throwable cause) {
        super(code, msg, field, explain, cause);
    }

    public LoginParamValidException(Throwable cause) {
        super(CodeEnums.A1200.msg, cause);
        this.code = CodeEnums.A1200.code;
        this.msg = CodeEnums.A1200.msg;
    }

    public LoginParamValidException(String msg, Throwable cause) {
        super(msg, cause);
        this.code = CodeEnums.A1200.code;
        this.msg = msg;
    }

}
