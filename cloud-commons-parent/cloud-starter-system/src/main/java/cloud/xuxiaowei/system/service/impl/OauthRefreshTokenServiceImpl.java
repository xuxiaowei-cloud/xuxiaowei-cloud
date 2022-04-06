package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.OauthRefreshToken;
import cloud.xuxiaowei.system.mapper.OauthRefreshTokenMapper;
import cloud.xuxiaowei.system.service.IOauthRefreshTokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-06
 */
@Service
public class OauthRefreshTokenServiceImpl extends ServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken> implements IOauthRefreshTokenService {

    /**
     * 保存 刷新 Token
     *
     * @param tokenId            Token ID
     * @param refreshToken       刷新 Token
     * @param refreshTokenJson   刷新 Token JSon
     * @param authentication     权限
     * @param authenticationJson 权限 JSON
     */
    @Override
    public void save(String tokenId, byte[] refreshToken, String refreshTokenJson, byte[] authentication, String authenticationJson) {
        OauthRefreshToken oauthRefreshToken = new OauthRefreshToken();

        oauthRefreshToken.setTokenId(tokenId);
        oauthRefreshToken.setToken(refreshToken);
        oauthRefreshToken.setTokenJson(refreshTokenJson);
        oauthRefreshToken.setAuthentication(authentication);
        oauthRefreshToken.setAuthenticationJson(authenticationJson);

        save(oauthRefreshToken);
    }

    /**
     * 根据 Token ID 获取 刷新 Token
     *
     * @param tokenId Token ID
     * @return 返回 刷新 Token
     */
    @Override
    public OauthRefreshToken getByTokenId(String tokenId) {
        QueryWrapper<OauthRefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token_id", tokenId);
        return getOne(queryWrapper);
    }

    /**
     * 根据 Token ID 删除 刷新Token
     *
     * @param tokenId Token ID
     * @return 返回 删除结果
     */
    @Override
    public boolean removeByTokenId(String tokenId) {
        QueryWrapper<OauthRefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token_id", tokenId);
        return remove(queryWrapper);
    }

}
