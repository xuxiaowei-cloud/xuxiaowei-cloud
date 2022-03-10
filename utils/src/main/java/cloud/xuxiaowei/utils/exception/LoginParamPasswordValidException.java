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

    public LoginParamPasswordValidException(String code, String msg) {
        super(code, msg);
    }

}
