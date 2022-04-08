package cloud.xuxiaowei.authorizationserver.service;

import cloud.xuxiaowei.authorizationserver.entity.OauthRefreshToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-06
 */
public interface IOauthRefreshTokenService extends IService<OauthRefreshToken> {

    /**
     * 保存 刷新 Token
     *
     * @param tokenId            Token ID
     * @param refreshToken       刷新 Token
     * @param refreshTokenJson   刷新 Token JSon
     * @param authentication     权限
     * @param authenticationJson 权限 JSON
     */
    void save(String tokenId, byte[] refreshToken, String refreshTokenJson, byte[] authentication, String authenticationJson);

    /**
     * 根据 Token ID 获取 刷新 Token
     *
     * @param tokenId Token ID
     * @return 返回 刷新 Token
     */
    OauthRefreshToken getByTokenId(String tokenId);

    /**
     * 根据 Token ID 删除 刷新Token
     *
     * @param tokenId Token ID
     * @return 返回 删除结果
     */
    boolean removeByTokenId(String tokenId);

}
