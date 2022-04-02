package cloud.xuxiaowei.utils.exception.client;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * 客户端ID 验证异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class ClientIdException extends ClientException {

    public ClientIdException() {
        this(CodeEnums.C20001.code, CodeEnums.C20001.msg);
    }

    public ClientIdException(String msg) {
        this(CodeEnums.C20001.code, msg);
    }

    public ClientIdException(String code, String msg) {
        super(code, msg);
    }

    public ClientIdException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public ClientIdException(String code, String msg, String field, Throwable cause) {
        super(code, msg, field, cause);
    }

    public ClientIdException(String code, String msg, String field, String explain, Throwable cause) {
        super(code, msg, field, explain, cause);
    }

    public ClientIdException(Throwable cause) {
        this(CodeEnums.C20001.code, CodeEnums.C20001.msg, cause);
    }

    public ClientIdException(String msg, Throwable cause) {
        this(CodeEnums.C20001.code, msg, cause);
    }

}
