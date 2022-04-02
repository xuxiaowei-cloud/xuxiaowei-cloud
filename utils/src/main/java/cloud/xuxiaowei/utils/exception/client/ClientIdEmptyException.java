package cloud.xuxiaowei.utils.exception.client;

import cloud.xuxiaowei.utils.CodeEnums;

/**
 * 客户端ID为空异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class ClientIdEmptyException extends ClientException {

    public ClientIdEmptyException() {
        this(CodeEnums.C10001.code, CodeEnums.C10001.msg);
    }

    public ClientIdEmptyException(String msg) {
        this(CodeEnums.C10001.code, msg);
    }

    public ClientIdEmptyException(String code, String msg) {
        super(code, msg);
    }

    public ClientIdEmptyException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public ClientIdEmptyException(String code, String msg, String field, Throwable cause) {
        super(code, msg, field, cause);
    }

    public ClientIdEmptyException(String code, String msg, String field, String explain, Throwable cause) {
        super(code, msg, field, explain, cause);
    }

    public ClientIdEmptyException(Throwable cause) {
        this(CodeEnums.C10001.code, CodeEnums.C10001.msg, cause);
    }

    public ClientIdEmptyException(String msg, Throwable cause) {
        this(CodeEnums.C10001.code, msg, cause);
    }

}
