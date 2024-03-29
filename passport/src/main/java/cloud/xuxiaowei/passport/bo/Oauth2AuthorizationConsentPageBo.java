package cloud.xuxiaowei.passport.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 授权同意书表 分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Oauth2AuthorizationConsentPageBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页，默认值：1
	 */
	private long current = 1L;

	/**
	 * 每页显示条数，默认值：10
	 */
	private long size = 10L;

	/**
	 * 客户ID
	 */
	private String registeredClientId;

	/**
	 * 用户名
	 */
	private String principalName;

}
