package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 用户表。	原表结构：spring-security-core-*.*.*.jar!\org\springframework\security\core\userdetails\jdbc\users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
@Getter
@Setter
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    @TableField("`password`")
    private String password;

    private Boolean enabled;


}
