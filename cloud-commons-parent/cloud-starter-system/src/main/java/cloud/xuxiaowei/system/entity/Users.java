package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户表。	原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-05
 */
@Getter
@Setter
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名，唯一键：uk__users__username
     */
    private String username;

    /**
     * 密码，不能为空
     */
    @TableField("`password`")
    private String password;

    /**
     * 是否启用，不能为空
     */
    private Boolean enabled;

    /**
     * 帐户未过期，不能为空
     */
    private Boolean accountNonExpired;

    /**
     * 凭证未过期，不能为空
     */
    private Boolean credentialsNonExpired;

    /**
     * 帐户未锁定，不能为空
     */
    private Boolean accountNonLocked;

    /**
     * 权限
     */
    @TableField(exist = false)
    private List<Authorities> authoritiesList;

}
