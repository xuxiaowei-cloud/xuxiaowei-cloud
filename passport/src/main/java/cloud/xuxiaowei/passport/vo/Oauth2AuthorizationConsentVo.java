package cloud.xuxiaowei.passport.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 授权同意书表
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Oauth2AuthorizationConsentVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long tenantId;

	private String registeredClientId;

	private String principalName;

	private String authorities;

	private String clientId;

	private String clientName;

}