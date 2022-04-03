package cloud.xuxiaowei.utils;

/**
 * 服务 枚举
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public enum ServiceEnums {

    ADMIN_SERVER("admin-server", "监控（管理）服务"),

    GATEWAY("gateway", "网关服务"),

    AUTHORIZATION_SERVER("authorization-server", "授权服务"),

    PASSPORT("passport", "登录服务"),

    ;

    public final String value;

    public final String name;

    ServiceEnums(String service, String serviceName) {
        this.value = service;
        this.name = serviceName;
    }

    public static ServiceEnums getEnum(String value) {
        ServiceEnums[] enums = ServiceEnums.values();
        for (ServiceEnums e : enums) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }

}
