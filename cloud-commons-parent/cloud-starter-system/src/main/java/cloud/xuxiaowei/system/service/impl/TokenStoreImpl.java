package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.OauthAccessToken;
import cloud.xuxiaowei.system.entity.OauthRefreshToken;
import cloud.xuxiaowei.system.service.IOauthAccessTokenService;
import cloud.xuxiaowei.system.service.IOauthRefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Token 储存 实现类
 *
 * @author xuxiaowei
 * @see InMemoryTokenStore 内存 储存
 * @see JwtTokenStore Jwt Token 储存
 * @see JwkTokenStore Jwk Token 储存
 * @see JdbcTokenStore JDBC Token 储存
 * @see RedisTokenStore Redis Token 储存
 * @since 0.0.1
 */
@Slf4j
@Service
@SuppressWarnings({"deprecation"})
public class TokenStoreImpl implements TokenStore {

    private IOauthAccessTokenService oauthAccessTokenService;

    private IOauthRefreshTokenService oauthRefreshTokenService;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) {
        this.authenticationKeyGenerator = authenticationKeyGenerator;
    }

    @Autowired
    public void setOauthAccessTokenService(IOauthAccessTokenService oauthAccessTokenService) {
        this.oauthAccessTokenService = oauthAccessTokenService;
    }

    @Autowired
    public void setOauthRefreshTokenService(IOauthRefreshTokenService oauthRefreshTokenService) {
        this.oauthRefreshTokenService = oauthRefreshTokenService;
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken oAuth2AccessToken) {
        return readAuthentication(oAuth2AccessToken.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        OAuth2Authentication authentication = null;

        String tokenId = extractTokenKey(token);

        OauthAccessToken oauthAccessToken = oauthAccessTokenService.getByTokenId(tokenId);

        try {

            if (oauthAccessToken == null) {
                throw new EmptyResultDataAccessException("空结果数据访问异常", 1);
            }

            byte[] authenticationByte = oauthAccessToken.getAuthentication();

            authentication = SerializationUtils.deserialize(authenticationByte);
        } catch (EmptyResultDataAccessException e) {
            log.info("找不到访问令牌");
        } catch (IllegalArgumentException e) {
            log.warn("无法反序列化身份验证", e);
            oauthAccessTokenService.removeByTokenId(tokenId);
        }

        return authentication;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {

        String refreshToken = null;
        if (token.getRefreshToken() != null) {
            refreshToken = token.getRefreshToken().getValue();
        }

        String tokenId = extractTokenKey(token.getValue());

        if (readAccessToken(token.getValue()) != null) {
            oauthAccessTokenService.removeByTokenId(tokenId);
        }

        byte[] tokenByte = SerializationUtils.serialize(token);
        String extractKey = authenticationKeyGenerator.extractKey(authentication);
        String name = authentication.isClientOnly() ? null : authentication.getName();
        String clientId = authentication.getOAuth2Request().getClientId();
        byte[] authenticationByte = SerializationUtils.serialize(authentication);
        String refreshTokenExtractTokenKey = extractTokenKey(refreshToken);

        oauthAccessTokenService.save(tokenId,
                tokenByte, extractKey,
                name,
                clientId,
                authenticationByte, refreshTokenExtractTokenKey);

    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;

        String tokenId = extractTokenKey(tokenValue);

        OauthAccessToken oauthAccessToken = oauthAccessTokenService.getByTokenId(tokenId);

        try {
            if (oauthAccessToken == null) {
                throw new EmptyResultDataAccessException("空结果数据访问异常", 1);
            }

            byte[] tokenByte = oauthAccessToken.getToken();

            accessToken = SerializationUtils.deserialize(tokenByte);
        } catch (EmptyResultDataAccessException e) {
            log.info("找不到访问令牌");
        } catch (IllegalArgumentException e) {
            log.warn("无法反序列化访问令牌", e);
            oauthAccessTokenService.removeByTokenId(tokenId);
        }

        return accessToken;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        String tokenId = extractTokenKey(token.getValue());
        oauthAccessTokenService.removeByTokenId(tokenId);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        String tokenId = extractTokenKey(refreshToken.getValue());
        byte[] refreshTokenByte = SerializationUtils.serialize(refreshToken);
        byte[] authenticationByte = SerializationUtils.serialize(authentication);
        oauthRefreshTokenService.save(tokenId, refreshTokenByte, authenticationByte);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String token) {
        OAuth2RefreshToken refreshToken = null;

        String tokenId = extractTokenKey(token);

        OauthRefreshToken oauthRefreshToken = oauthRefreshTokenService.getByTokenId(tokenId);

        try {
            if (oauthRefreshToken == null) {
                throw new EmptyResultDataAccessException("空结果数据访问异常", 1);
            }

            byte[] tokenByte = oauthRefreshToken.getToken();

            refreshToken = SerializationUtils.deserialize(tokenByte);
        } catch (EmptyResultDataAccessException e) {
            log.info("找不到刷新令牌");
        } catch (IllegalArgumentException e) {
            log.warn("无法反序列化刷新令牌", e);
            oauthAccessTokenService.removeByTokenId(tokenId);
        }

        return refreshToken;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        OAuth2Authentication authentication = null;

        String tokenId = extractTokenKey(token.getValue());

        OauthRefreshToken oauthRefreshToken = oauthRefreshTokenService.getByTokenId(tokenId);

        try {

            if (oauthRefreshToken == null) {
                throw new EmptyResultDataAccessException("空结果数据访问异常", 1);
            }

            byte[] authenticationByte = oauthRefreshToken.getAuthentication();

            authentication = SerializationUtils.deserialize(authenticationByte);

        } catch (EmptyResultDataAccessException e) {
            log.info("找不到访问令牌");
        } catch (IllegalArgumentException e) {
            log.warn("无法反序列化访问令牌", e);
            oauthRefreshTokenService.removeByTokenId(tokenId);
        }

        return authentication;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        String tokenId = extractTokenKey(token.getValue());
        oauthRefreshTokenService.removeByTokenId(tokenId);
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken oauthRefreshToken) {
        String refreshToken = extractTokenKey(oauthRefreshToken.getValue());
        oauthAccessTokenService.removeByRefreshToken(refreshToken);
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;

        String authenticationId = authenticationKeyGenerator.extractKey(authentication);

        OauthAccessToken oauthAccessToken = oauthAccessTokenService.getByAuthenticationId(authenticationId);

        try {
            if (oauthAccessToken == null) {
                throw new EmptyResultDataAccessException("空结果数据访问异常", 1);
            }

            byte[] tokenByte = oauthAccessToken.getToken();

            accessToken = SerializationUtils.deserialize(tokenByte);

        } catch (EmptyResultDataAccessException e) {
            log.debug("未能找到用于身份验证的访问令牌 " + authentication);
        } catch (IllegalArgumentException e) {
            log.error("无法提取访问令牌进行身份验证 " + authentication, e);
        }

        if (accessToken != null && !authenticationId.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
            oauthAccessTokenService.removeByTokenId(accessToken.getValue());
            // 保持与储存一致 (此身份验证可能代表同一用户，但详细信息已更改)
            storeAccessToken(accessToken, authentication);
        }
        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<>();

        List<OauthAccessToken> oauthAccessTokenList = oauthAccessTokenService.listByUserNameAndClientId(userName, clientId);
        for (OauthAccessToken oauthAccessToken : oauthAccessTokenList) {
            accessTokens.add(SerializationUtils.deserialize(oauthAccessToken.getToken()));
        }

        return accessTokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<>();

        List<OauthAccessToken> oauthAccessTokenList = oauthAccessTokenService.listByClientId(clientId);
        for (OauthAccessToken oauthAccessToken : oauthAccessTokenList) {
            accessTokens.add(SerializationUtils.deserialize(oauthAccessToken.getToken()));
        }

        return accessTokens;
    }

    private String extractTokenKey(String value) {
        if (value == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 算法不可用。致命的（应该在 JDK 中）。");
        }

        byte[] bytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));
        return String.format("%032x", new BigInteger(1, bytes));
    }

}
