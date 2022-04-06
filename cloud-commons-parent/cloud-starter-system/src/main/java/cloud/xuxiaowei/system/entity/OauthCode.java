package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-05
 */
@Getter
@Setter
@TableName("oauth_code")
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 授权码
     */
    @TableField("`code`")
    private String code;

    /**
     * 权限
     */
    @TableField("`authentication`")
    private byte[] authentication;

    /**
     * 权限JSON
     */
    @TableField("authentication_json")
    private String authenticationJson;

    /**
     * 创建时间，不为空，数据库自动生成
     */
    @TableField("create_date")
    private LocalDateTime createDate;


}
