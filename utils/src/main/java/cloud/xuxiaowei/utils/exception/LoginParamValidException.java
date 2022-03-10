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

    public LoginParamValidException(String code, String msg) {
        super(code, msg);
    }

}
