package cloud.xuxiaowei.utils.exception;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * 登录参数不存在 异常父类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class LoginParamNonExistException extends LoginException {

    public LoginParamNonExistException() {
        super(CodeEnums.A1100.code, CodeEnums.A1100.msg);
    }

    public LoginParamNonExistException(String code, String msg) {
        super(code, msg);
    }

}
