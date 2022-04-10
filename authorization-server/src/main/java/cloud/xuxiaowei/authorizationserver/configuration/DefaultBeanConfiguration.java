package cloud.xuxiaowei.authorizationserver.configuration;

import cloud.xuxiaowei.utils.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.RedisAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.*;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cloud.xuxiaowei.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * 默认 {@link Bean} 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class DefaultBeanConfiguration {

    private DataSource dataSource;

    /**
     * 来自 spring-cloud-context-*.*.*.jar
     */
    private KeyProperties keyProperties;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setKeyProperties(KeyProperties keyProperties) {
        this.keyProperties = keyProperties;
    }

    /**
     * 默认用户信息 {@link UserDetailsService} {@link Bean}
     * <p>
     * 在 {@link UserDetailsService} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     * <p>
     * 存在 {@link UserDetailsService} 对应的 {@link Bean} 时，控制台不会输出默认用户名为“user”的密码
     *
     * @return 在 {@link UserDetailsService} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     */
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }

    /**
     * 授权 Code {@link Bean}
     * <p>
     * 在 {@link AuthorizationCodeServices} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link AuthorizationCodeServices} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     * @see InMemoryAuthorizationCodeServices 基于内存的 code 服务
     * @see RedisAuthorizationCodeServices 基于 Redis 的 code 服务
     * @see JdbcAuthorizationCodeServices 基于 JDBC 的 code 服务
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource) {
            private final RandomValueStringGenerator GENERATOR = new RandomValueStringGenerator();

            @Override
            public String createAuthorizationCode(OAuth2Authentication authentication) {
                // 自定义 code 长度
                GENERATOR.setLength(32);
                String code = GENERATOR.generate();
                store(code, authentication);
                return code;
            }

            @Override
            protected void store(String code, OAuth2Authentication authentication) {

                ObjectMapper objectMapper = new ObjectMapper();
                String authenticationJson;
                try {
                    authenticationJson = objectMapper.writeValueAsString(authentication);
                } catch (JsonProcessingException e) {
                    log.error("OAuth2Authentication 格式化为 JSON 异常", e);
                    authenticationJson = "{}";
                }

                String username = authentication.getName();

                Authentication userAuthentication = authentication.getUserAuthentication();
                Object details = userAuthentication.getDetails();

                String remoteAddress;
                String sessionId;
                if (details instanceof WebAuthenticationDetails) {
                    WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) details;
                    remoteAddress = webAuthenticationDetails.getRemoteAddress();
                    sessionId = webAuthenticationDetails.getSessionId();
                } else {
                    remoteAddress = null;
                    sessionId = null;
                }

                Collection<GrantedAuthority> authorities = authentication.getAuthorities();
                String authoritiesJson;
                try {
                    authoritiesJson = objectMapper.writeValueAsString(authorities);
                } catch (JsonProcessingException e) {
                    log.error("GrantedAuthority 格式化为 JSON 异常", e);
                    authoritiesJson = "{}";
                }

                OAuth2Request oauth2Request = authentication.getOAuth2Request();
                String clientId = oauth2Request.getClientId();
                String redirectUri = oauth2Request.getRedirectUri();

                Map<String, String> requestParameters = oauth2Request.getRequestParameters();
                String scope = requestParameters.get(OAuth2Utils.SCOPE);
                String responseType = requestParameters.get(OAuth2Utils.RESPONSE_TYPE);
                String state = requestParameters.get(OAuth2Utils.STATE);

                new JdbcTemplate(dataSource).update(
                        "insert into oauth_code (code, authentication, authentication_json, username, remote_address, authorities_json, client_id, redirect_uri, `scope`, response_type, `state`, session_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        new Object[]{code, new SqlLobValue(SerializationUtils.serialize(authentication)), authenticationJson, username, remoteAddress, authoritiesJson, clientId, redirectUri, scope, responseType, state, sessionId},
                        new int[]{Types.VARCHAR, Types.BLOB, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
            }

        };
    }

    /**
     * {@link KeyPair} {@link Bean}
     * <p>
     * 在 {@link KeyPair} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link KeyPair} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     */
    @Bean
    public KeyPair keyPair() {
        KeyProperties.KeyStore keyStore = keyProperties.getKeyStore();
        Resource location = keyStore.getLocation();
        String alias = keyStore.getAlias();
        String password = keyStore.getPassword();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(location, password.toCharArray());
        return keyStoreKeyFactory.getKeyPair(alias, password.toCharArray());
    }

    /**
     * 加密 Token {@link Bean}
     * <p>
     * 在 {@link JwtAccessTokenConverter} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link JwtAccessTokenConverter} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(KeyPair keyPair) {
        // 加密 Token
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    /**
     * JDBC Token 储存
     * 在 {@link TokenStore} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link TokenStore} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     * @see InMemoryTokenStore 内存 储存
     * @see JwtTokenStore Jwt Token 储存
     * @see JwkTokenStore Jwk Token 储存
     * @see RedisTokenStore Redis Token 储存
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource) {

            private final AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

            @Override
            public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
                String refreshToken = null;
                if (token.getRefreshToken() != null) {
                    refreshToken = token.getRefreshToken().getValue();
                }

                if (readAccessToken(token.getValue()) != null) {
                    removeAccessToken(token.getValue());
                }

                ObjectMapper objectMapper = new ObjectMapper();
                String tokenJson;
                try {
                    tokenJson = objectMapper.writeValueAsString(token);
                } catch (JsonProcessingException e) {
                    log.error("OAuth2AccessToken 格式化为 JSON 异常", e);
                    tokenJson = "{}";
                }
                String authenticationJson;
                try {
                    authenticationJson = objectMapper.writeValueAsString(authentication);
                } catch (JsonProcessingException e) {
                    log.error("OAuth2Authentication 格式化为 JSON 异常", e);
                    authenticationJson = "{}";
                }

                Map<String, Object> additionalInformation = token.getAdditionalInformation();

                String jti = String.valueOf(additionalInformation.get(AccessTokenConverter.JTI));
                String scope = authentication.getOAuth2Request().getRequestParameters().get(OAuth2Utils.SCOPE);
                Date expiration = token.getExpiration();
                String tokenType = token.getTokenType();
                String accessToken = token.getValue();

                String refreshTokenEncryption = token.getRefreshToken().getValue();
                Date refreshTokenExpiration;
                if (token.getRefreshToken() instanceof DefaultExpiringOAuth2RefreshToken) {
                    DefaultExpiringOAuth2RefreshToken oauth2RefreshToken = (DefaultExpiringOAuth2RefreshToken) token.getRefreshToken();
                    refreshTokenExpiration = oauth2RefreshToken.getExpiration();
                } else {
                    refreshTokenExpiration = null;
                }

                Authentication userAuthentication = authentication.getUserAuthentication();
                Object details = userAuthentication.getDetails();

                String remoteAddress;
                String sessionId;
                if (details instanceof WebAuthenticationDetails) {
                    WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) details;
                    remoteAddress = webAuthenticationDetails.getRemoteAddress();
                    sessionId = webAuthenticationDetails.getSessionId();
                } else {
                    remoteAddress = null;
                    sessionId = null;
                }

                Collection<GrantedAuthority> authorities = authentication.getAuthorities();
                String authoritiesJson;
                try {
                    authoritiesJson = objectMapper.writeValueAsString(authorities);
                } catch (JsonProcessingException e) {
                    log.error("GrantedAuthority 格式化为 JSON 异常", e);
                    authoritiesJson = "{}";
                }

                OAuth2Request oauth2Request = authentication.getOAuth2Request();
                String redirectUri = oauth2Request.getRedirectUri();

                Map<String, String> requestParameters = oauth2Request.getRequestParameters();
                String responseType = requestParameters.get(OAuth2Utils.RESPONSE_TYPE);
                String state = requestParameters.get(OAuth2Utils.STATE);

                new JdbcTemplate(dataSource).update("insert into oauth_access_token (token_id, token, token_json, jti, `scope`, expiration, token_type, access_token, refresh_token_encryption, refresh_token_expiration, authentication_id, user_name, client_id, authentication, authentication_json, remote_address, session_id, authorities_json, redirect_uri, response_type, `state`, refresh_token) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        new Object[]{extractTokenKey(token.getValue()),
                                new SqlLobValue(serializeAccessToken(token)), tokenJson, jti, scope, expiration, tokenType, accessToken, refreshTokenEncryption, refreshTokenExpiration,
                                authenticationKeyGenerator.extractKey(authentication), authentication.isClientOnly() ? null : authentication.getName(), authentication.getOAuth2Request().getClientId(),
                                new SqlLobValue(serializeAuthentication(authentication)), authenticationJson, remoteAddress, sessionId, authoritiesJson, redirectUri, responseType, state,
                                extractTokenKey(refreshToken)},
                        new int[]{Types.VARCHAR, Types.BLOB, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
            }

            @Override
            public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {

                ObjectMapper objectMapper = new ObjectMapper();
                String refreshTokenJson;

                String refreshTokenStr;
                Date expiration;
                try {
                    DefaultExpiringOAuth2RefreshToken oauth2RefreshToken = (DefaultExpiringOAuth2RefreshToken) refreshToken;
                    Map<String, String> map = new HashMap<>(4);
                    refreshTokenStr = oauth2RefreshToken.getValue();
                    expiration = oauth2RefreshToken.getExpiration();
                    map.put("refreshToken", refreshTokenStr);
                    map.put("expiration", DateUtils.format(expiration, DEFAULT_DATE_TIME_FORMAT));
                    refreshTokenJson = objectMapper.writeValueAsString(map);
                } catch (JsonProcessingException e) {
                    log.error("OAuth2RefreshToken 格式化为 JSON 异常", e);
                    refreshTokenJson = "{}";
                    refreshTokenStr = null;
                    expiration = null;
                }
                String authenticationJson;
                try {
                    authenticationJson = objectMapper.writeValueAsString(authentication);
                } catch (JsonProcessingException e) {
                    log.error("OAuth2Authentication 格式化为 JSON 异常", e);
                    authenticationJson = "{}";
                }

                String username = authentication.getName();

                Authentication userAuthentication = authentication.getUserAuthentication();
                Object details = userAuthentication.getDetails();

                String remoteAddress;
                String sessionId;
                if (details instanceof WebAuthenticationDetails) {
                    WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) details;
                    remoteAddress = webAuthenticationDetails.getRemoteAddress();
                    sessionId = webAuthenticationDetails.getSessionId();
                } else {
                    remoteAddress = null;
                    sessionId = null;
                }

                Collection<GrantedAuthority> authorities = authentication.getAuthorities();
                String authoritiesJson;
                try {
                    authoritiesJson = objectMapper.writeValueAsString(authorities);
                } catch (JsonProcessingException e) {
                    log.error("GrantedAuthority 格式化为 JSON 异常", e);
                    authoritiesJson = "{}";
                }

                OAuth2Request oauth2Request = authentication.getOAuth2Request();
                String clientId = oauth2Request.getClientId();
                String redirectUri = oauth2Request.getRedirectUri();

                Map<String, String> requestParameters = oauth2Request.getRequestParameters();
                String scope = requestParameters.get(OAuth2Utils.SCOPE);
                String responseType = requestParameters.get(OAuth2Utils.RESPONSE_TYPE);
                String state = requestParameters.get(OAuth2Utils.STATE);

                new JdbcTemplate(dataSource).update(
                        "insert into oauth_refresh_token (token_id, token, token_json, refresh_token, expiration, authentication, authentication_json, username, remote_address, authorities_json, client_id, redirect_uri, `scope`, response_type, `state`, session_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        new Object[]{extractTokenKey(refreshToken.getValue()),
                                new SqlLobValue(serializeRefreshToken(refreshToken)), refreshTokenJson, refreshTokenStr, expiration,
                                new SqlLobValue(serializeAuthentication(authentication)), authenticationJson, username, remoteAddress, authoritiesJson, clientId, redirectUri, scope, responseType, state, sessionId},
                        new int[]{Types.VARCHAR, Types.BLOB, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.BLOB, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
            }

        };
    }

}
