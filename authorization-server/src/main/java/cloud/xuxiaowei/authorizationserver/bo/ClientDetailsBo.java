package cloud.xuxiaowei.authorizationserver.bo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.Jackson2ArrayOrStringDeserializer;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 客户详细信息
 *
 * @author xuxiaowei
 * @see BaseClientDetails
 * @see Jackson2ArrayOrStringDeserializer#deserialize(JsonParser, DeserializationContext)
 * @since 0.0.1
 */
@Data
public class ClientDetailsBo implements ClientDetails {

    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    @com.fasterxml.jackson.annotation.JsonIgnore
    private Map<String, Object> additionalInformation;

    private String autoapprove;

    private Set<String> autoApproveScopes;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        if (this.resourceIds == null) {
            return new LinkedHashSet<>();
        }
        this.resourceIds = this.resourceIds.replaceAll("\\s+", ",");
        return new LinkedHashSet<>(Arrays.asList(StringUtils.commaDelimitedListToStringArray(this.resourceIds)));
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        if (this.scope == null) {
            return new LinkedHashSet<>();
        }
        this.scope = this.scope.replaceAll("\\s+", ",");
        return new LinkedHashSet<>(Arrays.asList(StringUtils.commaDelimitedListToStringArray(this.scope)));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (this.authorizedGrantTypes == null) {
            return new LinkedHashSet<>();
        }
        this.authorizedGrantTypes = this.authorizedGrantTypes.replaceAll("\\s+", ",");
        return new LinkedHashSet<>(Arrays.asList(StringUtils.commaDelimitedListToStringArray(this.authorizedGrantTypes)));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        if (this.webServerRedirectUri == null) {
            return new LinkedHashSet<>();
        }
        this.webServerRedirectUri = this.webServerRedirectUri.replaceAll("\\s+", ",");
        return new LinkedHashSet<>(Arrays.asList(StringUtils.commaDelimitedListToStringArray(this.webServerRedirectUri)));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValidity;
    }


    public Set<String> getAutoApproveScopes() {
        if (this.autoapprove == null) {
            return new LinkedHashSet<>();
        }
        this.autoapprove = this.autoapprove.replaceAll("\\s+", ",");
        return new LinkedHashSet<>(Arrays.asList(StringUtils.commaDelimitedListToStringArray(this.autoapprove)));
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (getAutoApproveScopes() == null) {
            return false;
        }
        for (String auto : getAutoApproveScopes()) {
            if ("true".equals(auto) || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }

    public void addAdditionalInformation(String key, Object value) {
        this.additionalInformation.put(key, value);
    }

}
