package cloud.xuxiaowei.mybatis.configuration;

import cloud.xuxiaowei.core.properties.CloudMyBatisPlusProperties;
import cloud.xuxiaowei.core.properties.CloudTenantProperties;
import cloud.xuxiaowei.utils.MdcConstants;
import cloud.xuxiaowei.utils.MdcUtils;
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

	private CloudTenantProperties cloudTenantProperties;

	@Autowired
	public void setCloudMyBatisPlusProperties(CloudMyBatisPlusProperties cloudMyBatisPlusProperties) {
		this.cloudMyBatisPlusProperties = cloudMyBatisPlusProperties;
	}

	@Autowired
	public void setCloudTenantProperties(CloudTenantProperties cloudTenantProperties) {
		this.cloudTenantProperties = cloudTenantProperties;
	}

	/**
	 * 获取租户 ID 值表达式，只支持单个 ID 值
	 * <p>
	 * @return 租户 ID 值表达式，为空时返回默认租户：1
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

			// 从配置文件中获取默认租户并返回
			return new LongValue(cloudTenantProperties.getDefaultTenantId());
		}
		return new LongValue(tenantId);
	}

	@Override
	public String getTenantIdColumn() {
		return cloudMyBatisPlusProperties.getTenantIdColumn();
	}

	/**
	 * 根据表名判断是否忽略拼接多租户条件
	 * <p>
	 * 默认都要进行解析并拼接多租户条件
	 * @param tableName 表名
	 * @return 是否忽略, true:表示忽略，false:需要解析并拼接多租户条件
	 */
	@Override
	public boolean ignoreTable(String tableName) {
		List<String> ignoreTables = cloudMyBatisPlusProperties.getIgnoreTables();
		for (String ignoreTable : ignoreTables) {
			if (tableName.equals(ignoreTable)) {
				return true;
			}
		}

		List<String> mdcIgnoreTables = MdcUtils.getIgnoreTables();
		for (String ignoreTable : mdcIgnoreTables) {
			if (tableName.equals(ignoreTable)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 忽略插入租户字段逻辑
	 * @param columns 插入字段
	 * @param tenantIdColumn 租户 ID 字段
	 * @return
	 */
	@Override
	public boolean ignoreInsert(List<Column> columns, String tenantIdColumn) {
		return TenantLineHandler.super.ignoreInsert(columns, tenantIdColumn);
	}

}
