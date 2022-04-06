package cloud.xuxiaowei.core.oauth2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * OAuth2 Token
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@JsonSerialize(using = OAuth2AccessTokenJackson2Serializer.class)
@JsonDeserialize(using = OAuth2AccessTokenJackson2Deserializer.class)
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2AccessToken {

    public static String BEARER_TYPE = "Bearer";

    public static String OAUTH2_TYPE = "OAuth2";

    public static String ACCESS_TOKEN = "access_token";

    public static String TOKEN_TYPE = "token_type";

    public static String EXPIRES_IN = "expires_in";

    public static String REFRESH_TOKEN = "refresh_token";

    public static String SCOPE = "scope";

    public static String JTI = "jti";

    public static String EXPIRATION = "expiration";

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private Long expiresIn;

    private Set<String> scope;

    private String jti;

    private LocalDateTime expiration;

    private Map<String, Object> additionalInformation;

}
