package cloud.xuxiaowei.passport.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 客户表 分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Oauth2RegisteredClientPageBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页，默认值：1
	 */
	private Long current;

	/**
	 * 每页显示条数，默认值：10
	 */
	private Long size;

	/**
	 * 客户内部ID
	 */
	private String id;

	/**
	 * 客户ID
	 */
	private String clientId;

	/**
	 * 客户名称
	 */
	private String clientName;

}
