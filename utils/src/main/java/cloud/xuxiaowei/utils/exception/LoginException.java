package cloud.xuxiaowei.utils.exception;

import cloud.xuxiaowei.utils.CodeEnums;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;

import javax.servlet.http.HttpSession;

/**
 * 登录 异常父类
 * <p>
 * 继承 {@link AuthenticationException} 之后，在登录时，抛出 {@link AuthenticationException} 子类，
 * 可以在 {@link HttpSession} 中获取到名字为 {@link WebAttributes#AUTHENTICATION_EXCEPTION} 的异常信息
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class LoginException extends AuthenticationException {

    /**
     * 身份验证异常的用户名
     */
    @Setter
    private String username;

    /**
     * 错误码
     */
    public String code;

    /**
     * 中文描述
     */
    public String msg;

    /**
     * 错误字段
     * <p>
     * 存在多个时，使用英文逗号隔开
     */
    private String field;

    /**
     * 说明
     */
    public String explain;

    public LoginException() {
        super(CodeEnums.A10000.msg);
        this.code = CodeEnums.A10000.code;
        this.msg = CodeEnums.A10000.msg;
    }

    public LoginException(String msg) {
        super(msg);
        this.code = CodeEnums.A10000.code;
        this.msg = msg;
    }

    public LoginException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public LoginException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public LoginException(String code, String msg, String field, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
        this.field = field;
    }

    public LoginException(String code, String msg, String field, String explain, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
        this.field = field;
        this.explain = explain;
    }

    public LoginException(Throwable cause) {
        super(CodeEnums.A10000.msg, cause);
        this.code = CodeEnums.A10000.code;
        this.msg = CodeEnums.A10000.msg;
    }

    public LoginException(String msg, Throwable cause) {
        super(msg, cause);
        this.code = CodeEnums.A10000.code;
        this.msg = msg;
    }

}
