package cloud.xuxiaowei.oauth2.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cloud.xuxiaowei.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-17
 */
@Data
@TableName("oauth_access_token")
public class OauthAccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oauth_access_token_id", type = IdType.AUTO)
    private Long oauthAccessTokenId;

    private String tokenId;

    private byte[] token;

    private String authenticationId;

    private String tokenJson;

    private String jti;

    private String scope;

    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime expiration;

    private String tokenType;

    private String accessToken;

    private String refreshTokenEncryption;

    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime refreshTokenExpiration;

    private String userName;

    private String clientId;

    @TableField("`authentication`")
    private byte[] authentication;

    private String authenticationJson;

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
    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime updateDate;

    /**
     * 逻辑删除，0 未删除，1 删除
     * <p>
     * 不添加逻辑删除的注解
     */
    private Boolean deleted;


}
