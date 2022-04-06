package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.OauthCode;
import cloud.xuxiaowei.system.service.IOauthCodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.*;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 授权码服务 实现类
 *
 * @author xuxiaowei
 * @see RandomValueAuthorizationCodeServices
 * @see InMemoryAuthorizationCodeServices
 * @see JdbcAuthorizationCodeServices
 * @see RedisAuthorizationCodeServices
 * @since 0.0.1
 */
@Slf4j
@Service
public class AuthorizationCodeServicesImpl implements AuthorizationCodeServices {

    private IOauthCodeService oauthCodeService;

    @Autowired
    public void setOauthCodeService(IOauthCodeService oauthCodeService) {
        this.oauthCodeService = oauthCodeService;
    }

    /**
     * 不包含：-_
     */
    private static final char[] DEFAULT_CODEC = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private final Random RANDOM = new SecureRandom();

    @Getter
    @Setter
    private int length;

    public AuthorizationCodeServicesImpl() {
        this.length = 32;
    }

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String code = generate();

        OauthCode oauthCode = new OauthCode();
        oauthCode.setCode(code);
        oauthCode.setAuthentication(SerializationUtils.serialize(authentication));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String authenticationJson = objectMapper.writeValueAsString(authentication);
            log.info("生成授权码：{}，授权信息：{}", code, authenticationJson);
            oauthCode.setAuthenticationJson(authenticationJson);
        } catch (JsonProcessingException e) {
            log.error("将 authentication 转换为 JSON 异常", e);
        }

        oauthCodeService.save(oauthCode);

        return code;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {

        log.info("查询授权码：{}", code);
        OauthCode oauthCode = oauthCodeService.getByCode(code);
        log.info("授权信息：{}", oauthCode.getAuthenticationJson());

        byte[] authentication = oauthCode.getAuthentication();

        return SerializationUtils.deserialize(authentication);
    }

    private String generate() {
        byte[] verifierBytes = new byte[length];
        RANDOM.nextBytes(verifierBytes);
        return getAuthorizationCodeString(verifierBytes);
    }

    private String getAuthorizationCodeString(byte[] verifierBytes) {
        char[] chars = new char[verifierBytes.length];
        for (int i = 0; i < verifierBytes.length; i++) {
            chars[i] = DEFAULT_CODEC[((verifierBytes[i] & 0xFF) % DEFAULT_CODEC.length)];
        }
        return new String(chars);
    }

}
