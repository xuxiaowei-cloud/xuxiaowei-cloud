package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis Plus 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("cloud.mybatis-plus")
public class CloudMyBatisPlusProperties {

	/**
	 * 租户字段名
	 */
	private String tenantIdColumn = "tenant_id";

	/**
	 * 是否忽略拼接多租户条件的表名
	 */
	private List<String> ignoreTables = new ArrayList<>();

}
