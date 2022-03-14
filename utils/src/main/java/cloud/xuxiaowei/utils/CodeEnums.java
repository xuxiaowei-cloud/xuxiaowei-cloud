package cloud.xuxiaowei.utils;

/**
 * 错误代码
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public enum CodeEnums {

    OK("00000", "一切OK", null),

    ERROR("99999", "系统异常", null),

    A1000("A1000", "登录异常", "通用代码"),

    A1100("A1100", "缺少登录参数", "通用代码"),
    A1200("A1200", "登录参数验证失败", "通用代码"),

    A1101("A1101", "登录参数无用户名", null),
    A1201("A1201", "登录参数非法用户名", null),

    A1102("A1102", "登录参数无密码", null),
    A1202("A1202", "登录参数非法密码", null),

    A1103("A1103", "登录参数无CSRF Name", null),
    A1203("A1203", "登录参数非法CSRF Name", null),

    A1104("A1104", "登录参数无CSRF Token", null),
    A1204("A1204", "登录参数非法CSRF Token", null),

    A1105("A1105", "登录参数无验证码", null),
    A1205("A1205", "登录参数非法验证码", null),

    A1106("A1106", "登录参数无图片验证码", null),
    A1206("A1206", "登录参数非法图片验证码", null),

    A1107("A1107", "登录参数无短信验证码", null),
    A1207("A1207", "登录参数非法短信验证码", null),

    A1108("A1108", "登录参数无邮件验证码", null),
    A1208("A1208", "登录参数非法邮件验证码", null),

    A1109("A1109", "登录参数无解密秘钥", null),
    A1209("A1209", "登录参数非法解密", null),

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
