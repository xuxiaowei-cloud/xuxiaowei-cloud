package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表。	原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-06
 */
@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名，不能为空，唯一键：uk__users__username
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
     * 更新时间，未更新时为空
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;

    /**
     * 创建时间，不为空，数据库自动生成
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 权限
     */
    @TableField(exist = false)
    private List<Authorities> authoritiesList;

}
