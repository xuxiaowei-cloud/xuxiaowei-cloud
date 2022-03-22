package cloud.xuxiaowei.core.oauth2;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * OAuth2 Token 反序列化
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2AccessTokenJackson2Deserializer extends StdDeserializer<OAuth2AccessToken> {

    public OAuth2AccessTokenJackson2Deserializer() {
        super(OAuth2AccessToken.class);
    }

    @Override
    public OAuth2AccessToken deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        String tokenValue = null;
        String tokenType = null;
        String refreshToken = null;
        Long expiresIn = null;
        Set<String> scope = null;
        Map<String, Object> additionalInformation = new LinkedHashMap<>();

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String name = jp.getCurrentName();
            jp.nextToken();
            if (OAuth2AccessToken.ACCESS_TOKEN.equals(name)) {
                tokenValue = jp.getText();
            } else if (OAuth2AccessToken.TOKEN_TYPE.equals(name)) {
                tokenType = jp.getText();
            } else if (OAuth2AccessToken.REFRESH_TOKEN.equals(name)) {
                refreshToken = jp.getText();
            } else if (OAuth2AccessToken.EXPIRES_IN.equals(name)) {

                String text = jp.getText();

                try {
                    expiresIn = jp.getLongValue();
                } catch (JsonParseException e) {
                    expiresIn = Long.valueOf(jp.getText());
                }
            } else if (OAuth2AccessToken.SCOPE.equals(name)) {
                scope = parseScope(jp);
            } else {
                additionalInformation.put(name, jp.readValueAs(Object.class));
            }
        }

        OAuth2AccessToken accessToken = new OAuth2AccessToken();
        accessToken.setAccessToken(tokenValue);
        accessToken.setTokenType(tokenType);
        if (expiresIn != null && expiresIn != 0) {
            LocalDateTime expiration = LocalDateTime.now().minusSeconds(expiresIn);
            accessToken.setExpiration(expiration);
            accessToken.setExpiresIn(expiresIn);
        }
        accessToken.setRefreshToken(refreshToken);
        accessToken.setScope(scope);
        accessToken.setAdditionalInformation(additionalInformation);

        return accessToken;
    }

    /**
     * 解析 scope
     *
     * @param jp Json 解析器
     * @return 返回 List
     * @throws IOException 解析异常
     */
    private Set<String> parseScope(JsonParser jp) throws IOException {
        Set<String> scope;
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            scope = new TreeSet<>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                scope.add(jp.getValueAsString());
            }
        } else {
            String text = jp.getText();
            scope = OAuth2Utils.parseParameterList(text);
        }
        return scope;
    }

}
