package cloud.xuxiaowei.authorizationserver.service;

import cloud.xuxiaowei.authorizationserver.entity.OauthAccessToken;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-06
 */
public interface IOauthAccessTokenService extends IService<OauthAccessToken> {

    /**
     * 根据 Token Id 查询 Token
     *
     * @param tokenId Token Id
     * @return 返回 Token
     */
    OauthAccessToken getByTokenId(String tokenId);

    /**
     * 保存 Token
     *
     * @param tokenId            token_id
     * @param token              token
     * @param tokenJson          token_json
     * @param authenticationId   authentication_id
     * @param userName           user_name
     * @param clientId           client_id
     * @param authentication     authentication
     * @param authenticationJson authentication_json
     * @param refreshToken       refresh_token
     * @return 返回 保存结果
     */
    boolean save(String tokenId, byte[] token, String tokenJson, String authenticationId, String userName, String clientId,
                 byte[] authentication, String authenticationJson, String refreshToken);

    /**
     * 根据 Token ID 删除 Token
     *
     * @param tokenId Token ID
     * @return 返回 删除结果
     */
    boolean removeByTokenId(String tokenId);

    /**
     * 根据 authenticationId 查询 Token
     *
     * @param authenticationId authenticationId
     * @return 返回 结果
     */
    OauthAccessToken getByAuthenticationId(String authenticationId);

    /**
     * 根据 刷新 Token 删除
     *
     * @param refreshToken 刷新 Token
     * @return 返回 删除结果
     */
    boolean removeByRefreshToken(String refreshToken);

    /**
     * 根据 用户名、客户ID 查询 授权 Token
     *
     * @param userName 用户名
     * @param clientId 客户ID
     * @return 返回 结果
     */
    List<OauthAccessToken> listByUserNameAndClientId(String userName, String clientId);

    /**
     * 根据 客户ID 查询 授权 Token
     *
     * @param clientId 客户ID
     * @return 返回 结果
     */
    List<OauthAccessToken> listByClientId(String clientId);

}
