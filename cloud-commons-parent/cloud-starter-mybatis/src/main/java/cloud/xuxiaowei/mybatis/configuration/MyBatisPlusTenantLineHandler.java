package cloud.xuxiaowei.mybatis.configuration;

import cloud.xuxiaowei.core.properties.CloudMyBatisPlusProperties;
import cloud.xuxiaowei.utils.SecurityUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 租户处理器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class MyBatisPlusTenantLineHandler implements TenantLineHandler {

	private CloudMyBatisPlusProperties cloudMyBatisPlusProperties;

	@Autowired
	public void setCloudMyBatisPlusProperties(CloudMyBatisPlusProperties cloudMyBatisPlusProperties) {
		this.cloudMyBatisPlusProperties = cloudMyBatisPlusProperties;
	}

	/**
	 * 租户ID
	 * @return 返回租户ID，为空时返回默认租户：1
	 */
	@Override
	public Expression getTenantId() {
		Long tenantId = SecurityUtils.getTenantId();
		if (tenantId == null) {
			return new LongValue(1);
		}
		return new LongValue(tenantId);
	}

	@Override
	public String getTenantIdColumn() {
		return cloudMyBatisPlusProperties.getTenantIdColumn();
	}

	@Override
	public boolean ignoreTable(String tableName) {
		List<String> ignoreTables = cloudMyBatisPlusProperties.getIgnoreTables();
		for (String ignoreTable : ignoreTables) {
			if (tableName.equals(ignoreTable)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean ignoreInsert(List<Column> columns, String tenantIdColumn) {
		return TenantLineHandler.super.ignoreInsert(columns, tenantIdColumn);
	}

}
