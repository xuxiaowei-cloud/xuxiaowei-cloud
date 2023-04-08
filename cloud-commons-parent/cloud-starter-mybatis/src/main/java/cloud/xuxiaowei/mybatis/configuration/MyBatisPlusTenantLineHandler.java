package cloud.xuxiaowei.mybatis.configuration;

import cloud.xuxiaowei.core.properties.CloudMyBatisPlusProperties;
import cloud.xuxiaowei.utils.MdcConstants;
import cloud.xuxiaowei.utils.SecurityUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

			// 临时租户ID
			String tmpTenantId = MDC.get(MdcConstants.TMP_TENANT_ID);

			if (StringUtils.hasText(tmpTenantId)) {
				try {
					long l = Long.parseLong(tmpTenantId);
					log.info("使用临时租户ID：{}", l);
					return new LongValue(l);
				}
				catch (Exception e) {
					log.error("临时租户ID 不合法，将会使用默认值", e);
				}
			}

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
