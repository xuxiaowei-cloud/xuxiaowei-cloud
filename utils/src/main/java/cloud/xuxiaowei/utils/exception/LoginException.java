package cloud.xuxiaowei.utils.exception;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * 登录 异常父类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class LoginException extends CloudException {

    public LoginException() {
        super(CodeEnums.A1000.code, CodeEnums.A1000.msg);
    }

    public LoginException(String code, String msg) {
        super(code, msg);
    }

}
