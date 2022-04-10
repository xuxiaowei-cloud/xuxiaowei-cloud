package cloud.xuxiaowei.utils;

/**
 * 错误代码
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public enum CodeEnums {

    /**
     * 正常返回
     */
    OK("000000", "一切OK", null),

    /**
     * 系统异常
     */
    ERROR("999999", "系统异常", null),

    /**
     * 登录异常
     */
    A10000("A10000", "登录异常", "通用代码"),
    /**
     * 登录验证异常
     */
    A20000("A20000", "登录验证异常", "通用代码"),
    /**
     * 登录参数缺少
     */
    A10001("A10001", "登录参数缺少", "通用代码"),
    /**
     * 登录参数验证失败
     */
    A20001("A20001", "登录参数验证失败", "通用代码"),
    /**
     * 登录参数无用户名
     */
    A10002("A10002", "登录参数无用户名", null),
    /**
     * 登录参数非法用户名
     */
    A20002("A20002", "登录参数非法用户名", null),
    /**
     * 登录参数无密码
     */
    A10003("A10003", "登录参数无密码", null),
    /**
     * 登录参数非法密码
     */
    A20003("A20003", "登录参数非法密码", null),
    /**
     * 登录参数无CSRF Name
     */
    A10004("A10004", "登录参数无CSRF Name", null),
    /**
     * 登录参数非法CSRF Name
     */
    A20004("A20004", "登录参数非法CSRF Name", null),
    /**
     * 登录参数无CSRF Token
     */
    A10005("A10005", "登录参数无CSRF Token", null),
    /**
     * 登录参数非法CSRF Token
     */
    A20005("A20005", "登录参数非法CSRF Token", null),
    /**
     * 登录参数无验证码
     */
    A10006("A10006", "登录参数无验证码", null),
    /**
     * 登录参数非法验证码
     */
    A20006("A20006", "登录参数非法验证码", null),
    /**
     * 登录参数无图片验证码
     */
    A10007("A10007", "登录参数无图片验证码", null),
    /**
     * 登录参数非法图片验证码
     */
    A20007("A20007", "登录参数非法图片验证码", null),
    /**
     * 登录参数无短信验证码
     */
    A10008("A10008", "登录参数无短信验证码", null),
    /**
     * 登录参数非法短信验证码
     */
    A20008("A20008", "登录参数非法短信验证码", null),
    /**
     * 登录参数无邮件验证码
     */
    A10009("A10009", "登录参数无邮件验证码", null),
    /**
     * 登录参数非法邮件验证码
     */
    A20009("A20009", "登录参数非法邮件验证码", null),
    /**
     * 登录参数无解密秘钥
     */
    A10010("A10010", "登录参数无解密秘钥", null),
    /**
     * 登录参数无法解密
     */
    A20010("A20010", "登录参数无法解密", null),

    /**
     * 客户端异常
     */
    C10000("C10000", "客户端异常", "通用代码"),
    /**
     * 客户端异常
     */
    C20000("C20000", "客户端验证异常", "通用代码"),
    /**
     * 客户端参数缺少客户端ID
     */
    C10001("C10001", "客户端参数缺少客户端ID", null),
    /**
     * 客户端参数验证客户端ID异常
     */
    C20001("C20001", "客户端参数验证客户端ID异常", null),
    /**
     * 客户端参数缺少客户端秘钥
     */
    C10002("C10002", "客户端参数缺少客户端秘钥", null),
    /**
     * 客户端参数验证客户端秘钥异常
     */
    C20002("C20002", "客户端参数验证客户端秘钥异常", null),
    /**
     * 客户端参数缺少授权类型
     */
    C10003("C10003", "客户端参数缺少授权类型", null),
    /**
     * 客户端参数验证授权类型异常
     */
    C20003("C20003", "客户端参数验证授权类型异常", null),
    /**
     * 客户端参数缺少授权类型
     */
    C10004("C10004", "客户端参数缺少重定向地址", null),
    /**
     * 客户端参数验证授权类型异常
     */
    C20004("C20004", "客户端参数验证重定向地址异常", null),
    /**
     * 客户端参数缺少范围
     */
    C10005("C10005", "客户端参数缺少范围", null),
    /**
     * 客户端参数验证范围异常
     */
    C20005("C20005", "客户端参数验证范围异常", null),
    /**
     * 客户端参数缺少授权码
     */
    C10006("C10006", "客户端参数缺少授权码", null),
    /**
     * 客户端参数验证授权码异常
     */
    C20006("C20006", "客户端参数验证授权码异常", null),

    /**
     * 禁止访问
     */
    X10000("X10000", "禁止访问", "通用代码"),
    /**
     * 禁止通过网关访问服务端点
     */
    X10001("X10001", "禁止通过网关访问服务端点", null),
    /**
     * 禁止通过网关访问监控（管理）服务
     */
    X10002("X10002", "禁止通过网关访问监控（管理）服务", null),


    /**
     * 令牌异常
     */
    T10000("T10000", "令牌异常", "通用代码"),
    /**
     * 令牌未识别
     */
    T10001("T10001", "令牌未识别", null),
    /**
     * 令牌已过期
     */
    T10002("T10002", "令牌已过期", null),
    /**
     * 令牌缺失
     */
    T10003("T10003", "令牌缺失", null),
    /**
     * 令牌无效
     */
    T10004("T10004", "令牌无效", null),

    /**
     * 服务异常
     */
    S10000("S10000", "服务异常", "通用代码"),
    /**
     * 服务未发现
     */
    S10001("S10001", "服务未发现", null),
    /**
     * 服务连接异常
     */
    S10002("S10002", "服务连接异常", null),
    /**
     * 禁止未找到用户IP的请求访问服务
     */
    X10003("X10003", "禁止未找到用户IP的请求访问服务", null),

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
