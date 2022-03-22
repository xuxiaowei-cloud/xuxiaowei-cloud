package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 微服务 客户端 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties("cloud.client")
public class CloudClientProperties {

    /**
     * 授权码 地址参数
     */
    public static final String AUTHORIZE_URI_PARAMETER = "?client_id={client_id}&redirect_uri={redirect_uri}&response_type=code&scope={scope}&state={state}";

    /**
     * 授权码 地址参数
     */
    public static final String AUTHORIZE_URI_PARAMETER_FORMAT = "%s?client_id=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s";

    /**
     * 授权 Token 地址参数
     */
    public static final String ACCESS_TOKEN_URI_PARAMETER = "?code={code}&client_id={client_id}&client_secret={client_secret}&redirect_uri={redirect_uri}&grant_type=authorization_code";

    /**
     * 客户ID
     */
    private String clientId;

    /**
     * 客户秘钥
     */
    private String clientSecret;

    /**
     * 重定向地址
     */
    private String redirectUri;

    /**
     * 授权码 URI
     */
    private String authorizeUri;

    /**
     * 授权 Token URI
     */
    private String accessTokenUri;

    /**
     * 授权码 完整 URI
     *
     * @return 授权码 完整 URI
     */
    public String authorizeUri(String scope, String state) {
        return String.format(AUTHORIZE_URI_PARAMETER_FORMAT, this.authorizeUri, this.clientId, this.redirectUri, scope, state);
    }

    /**
     * 授权 Token 完整 URI
     *
     * @return 授权 Token 完整 URI
     */
    public String accessTokenUri() {
        return this.accessTokenUri + ACCESS_TOKEN_URI_PARAMETER;
    }

}
