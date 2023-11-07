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
@ConfigurationProperties("cloud.tenant")
public class CloudTenantProperties {

	/**
	 * 默认客户主键
	 */
	private String id;

	/**
	 * 默认租户ID
	 */
	private Long defaultTenantId;

	/**
	 * 默认客户ID
	 */
	private String defaultClientId;

	/**
	 * SQL忽略拼接租户条件的租户ID
	 * <p>
	 * 超级租户
	 * <p>
	 * 1. 所有人员都是不同租户下的成员
	 * <p>
	 * 2. 运维、开发、管理人员所在的租户，可以查看所有数据
	 * <p>
	 * // @formatter:off
	 * 3. 由于本租户下的用户能看到所有租户的数据，并且不同租户下可以存在相同的用户名，
	 * 所以接口不能使用用户名查询数据，应该使用用户主键查询数据，以避免报错
	 * // @formatter:on
	 */
	private long[] ignoreAllTableTenantIds;

}
