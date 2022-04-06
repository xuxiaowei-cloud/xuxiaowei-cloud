package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.OauthAccessToken;
import cloud.xuxiaowei.system.mapper.OauthAccessTokenMapper;
import cloud.xuxiaowei.system.service.IOauthAccessTokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-06
 */
@Service
public class OauthAccessTokenServiceImpl extends ServiceImpl<OauthAccessTokenMapper, OauthAccessToken> implements IOauthAccessTokenService {

    /**
     * 根据 Token Id 查询 Token
     *
     * @param tokenId Token Id
     * @return 返回 Token
     */
    @Override
    public OauthAccessToken getByTokenId(String tokenId) {
        QueryWrapper<OauthAccessToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token_id", tokenId);
        return getOne(queryWrapper);
    }

    /**
     * 保存 Token
     *
     * @param tokenId          token_id
     * @param token            token
     * @param authenticationId authentication_id
     * @param userName         user_name
     * @param clientId         client_id
     * @param authentication   authentication
     * @param refreshToken     refresh_token
     * @return 返回 保存结果
     */
    @Override
    public boolean save(String tokenId, byte[] token, String authenticationId, String userName, String clientId,
                        byte[] authentication, String refreshToken) {
        OauthAccessToken oauthAccessToken = new OauthAccessToken();

        oauthAccessToken.setTokenId(tokenId);
        oauthAccessToken.setToken(token);
        oauthAccessToken.setAuthenticationId(authenticationId);
        oauthAccessToken.setUserName(userName);
        oauthAccessToken.setClientId(clientId);
        oauthAccessToken.setAuthentication(authentication);
        oauthAccessToken.setRefreshToken(refreshToken);

        return save(oauthAccessToken);
    }

    /**
     * 根据 Token ID 删除 Token
     *
     * @param tokenId Token ID
     * @return 返回 删除结果
     */
    @Override
    public boolean removeByTokenId(String tokenId) {
        QueryWrapper<OauthAccessToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token_id", tokenId);
        return remove(queryWrapper);
    }

    /**
     * 根据 authenticationId 查询 Token
     *
     * @param authenticationId authenticationId
     * @return 返回 结果
     */
    @Override
    public OauthAccessToken getByAuthenticationId(String authenticationId) {
        QueryWrapper<OauthAccessToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("authentication_id", authenticationId);
        return getOne(queryWrapper);
    }

    /**
     * 根据 刷新 Token 删除
     *
     * @param refreshToken 刷新 Token
     * @return 返回 删除结果
     */
    @Override
    public boolean removeByRefreshToken(String refreshToken) {
        QueryWrapper<OauthAccessToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("refresh_token", refreshToken);
        return remove(queryWrapper);
    }

    /**
     * 根据 用户名、客户ID 查询 授权 Token
     *
     * @param userName 用户名
     * @param clientId 客户ID
     * @return 返回 结果
     */
    @Override
    public List<OauthAccessToken> listByUserNameAndClientId(String userName, String clientId) {
        QueryWrapper<OauthAccessToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("client_id", clientId);
        return list(queryWrapper);
    }

    /**
     * 根据 客户ID 查询 授权 Token
     *
     * @param clientId 客户ID
     * @return 返回 结果
     */
    @Override
    public List<OauthAccessToken> listByClientId(String clientId) {
        QueryWrapper<OauthAccessToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientId);
        return list(queryWrapper);
    }

}
