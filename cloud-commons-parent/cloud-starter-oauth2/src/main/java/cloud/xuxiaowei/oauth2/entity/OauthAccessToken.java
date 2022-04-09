package cloud.xuxiaowei.oauth2.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@Data
@TableName("oauth_access_token")
public class OauthAccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long oauthAccessTokenId;

    private String tokenId;

    private byte[] token;

    private String tokenJson;

    private String jti;

    private String scope;

    private LocalDateTime expiration;

    private String tokenType;

    private String accessToken;

    private String refreshTokenEncryption;

    private LocalDateTime refreshTokenExpiration;

    private String authenticationId;

    private String userName;

    private String clientId;

    @TableField("`authentication`")
    private byte[] authentication;

    private String authenticationJson;

    private String username;

    private String remoteAddress;

    private String sessionId;

    private String authoritiesJson;

    private String redirectUri;

    private String responseType;

    private String state;

    private String refreshToken;

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
