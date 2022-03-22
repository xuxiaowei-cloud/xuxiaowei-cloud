package cloud.xuxiaowei.core.oauth2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.util.Assert;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

/**
 * OAuth2 Token 序列化
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2AccessTokenJackson2Serializer extends StdSerializer<OAuth2AccessToken> {

    public OAuth2AccessTokenJackson2Serializer() {
        super(OAuth2AccessToken.class);
    }

    @Override
    public void serialize(OAuth2AccessToken token, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField(OAuth2AccessToken.ACCESS_TOKEN, token.getAccessToken());
        jgen.writeStringField(OAuth2AccessToken.TOKEN_TYPE, token.getTokenType());
        String refreshToken = token.getRefreshToken();
        if (refreshToken != null) {
            jgen.writeStringField(OAuth2AccessToken.REFRESH_TOKEN, refreshToken);
        }
        LocalDateTime expiration = token.getExpiration();
        if (expiration != null) {
            LocalDateTime now = LocalDateTime.now();
            long expiresIn = ChronoUnit.SECONDS.between(expiration, now);
            jgen.writeNumberField(OAuth2AccessToken.EXPIRES_IN, expiresIn);
        }
        Set<String> scope = token.getScope();
        if (scope != null && !scope.isEmpty()) {
            StringBuilder scopes = new StringBuilder();
            for (String s : scope) {
                Assert.hasLength(s, "Scopes cannot be null or empty. Got " + scope + "");
                scopes.append(s);
                scopes.append(" ");
            }
            jgen.writeStringField(OAuth2AccessToken.SCOPE, scopes.substring(0, scopes.length() - 1));
        }
        Map<String, Object> additionalInformation = token.getAdditionalInformation();
        for (String key : additionalInformation.keySet()) {
            jgen.writeObjectField(key, additionalInformation.get(key));
        }
        jgen.writeEndObject();
    }

}
