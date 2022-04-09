package cloud.xuxiaowei.authorizationserver.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@Getter
@Setter
@TableName("oauth_refresh_token")
public class OauthRefreshToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long oauthRefreshTokenId;

    private String tokenId;

    private Blob token;

    private String tokenJson;

    private String refreshToken;

    private LocalDateTime expiration;

    @TableField("`authentication`")
    private Blob authentication;

    private String authenticationJson;

    private String username;

    private String remoteAddress;

    private String authoritiesJson;

    private String clientId;

    private String redirectUri;

    private String scope;

    private String responseType;

    private String state;

    private String sessionId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;


}
