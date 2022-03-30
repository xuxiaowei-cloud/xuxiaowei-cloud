package cloud.xuxiaowei.utils;

/**
 * 错误代码
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public enum CodeEnums {

    OK("000000", "一切OK", null),

    ERROR("999999", "系统异常", null),

    A10000("A10000", "登录异常", "通用代码"),

    A10001("A10001", "登录参数缺少", "通用代码"),
    A20001("A20001", "登录参数验证失败", "通用代码"),

    A10002("A10002", "登录参数无用户名", null),
    A20002("A20002", "登录参数非法用户名", null),

    A10003("A10003", "登录参数无密码", null),
    A20003("A20003", "登录参数非法密码", null),

    A10004("A10004", "登录参数无CSRF Name", null),
    A20004("A20004", "登录参数非法CSRF Name", null),

    A10005("A10005", "登录参数无CSRF Token", null),
    A20005("A20005", "登录参数非法CSRF Token", null),

    A10006("A10006", "登录参数无验证码", null),
    A20006("A20006", "登录参数非法验证码", null),

    A10007("A10007", "登录参数无图片验证码", null),
    A20007("A20007", "登录参数非法图片验证码", null),

    A10008("A10008", "登录参数无短信验证码", null),
    A20008("A20008", "登录参数非法短信验证码", null),

    A10009("A10009", "登录参数无邮件验证码", null),
    A20009("A20009", "登录参数非法邮件验证码", null),

    A10010("A10010", "登录参数无解密秘钥", null),
    A20010("A20010", "登录参数无法解密", null),

    X10000("X10000", "禁止访问", "通用代码"),

    X10001("X10001", "禁止通过网关访问服务端点", null),
    X10002("X10002", "禁止通过网关访问监控（管理）服务", null)

    ;

    /**
     * 错误码
     */
    public final String code;

    /**
     * 中文描述
     */
    public final String msg;

    /**
     * 说明
     */
    public final String explain;

    CodeEnums(String code, String msg, String explain) {
        this.code = code;
        this.msg = msg;
        this.explain = explain;
    }

}
