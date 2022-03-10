package cloud.xuxiaowei.utils.exception;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * 登录参数密码不存在 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class LoginParamPasswordNonExistException extends LoginParamNonExistException {

    public LoginParamPasswordNonExistException() {
        super(CodeEnums.A1102.code, CodeEnums.A1102.msg);
    }

}
