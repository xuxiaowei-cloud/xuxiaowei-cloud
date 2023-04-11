package cloud.xuxiaowei.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MDC 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class MdcUtils {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 向 {@link MDC} 中传 {@link List} 数据
	 * @param key 键
	 * @param list List 数据
	 */
	@SneakyThrows
	public static void put(@NonNull String key, @NonNull List<String> list) {
		String value = OBJECT_MAPPER.writeValueAsString(list);
		MDC.put(key, value);
	}

	/**
	 * 从 {@link MDC} 获取 {@link List} 数据
	 * @param key 键
	 * @return List 数据
	 */
	@SneakyThrows
	public static List<String> getList(@NonNull String key) {
		String value = MDC.get(key);
		if (value == null) {
			return new ArrayList<>();
		}
		return OBJECT_MAPPER.readValue(value, new TypeReference<List<String>>() {
		});
	}

	/**
	 * 清空 忽略拼接租户条件的临时表
	 */
	public static void clearIgnoreTables() {
		MDC.remove(MdcConstants.TMP_IGNORE_TABLES);
	}

	/**
	 * 添加 忽略拼接租户条件的临时表
	 * @param value 忽略拼接租户条件的临时表，此表不进行租户条件拼接
	 */
	public static void putIgnoreTable(@NonNull String value) {
		put(MdcConstants.TMP_IGNORE_TABLES, Collections.singletonList(value));
	}

	/**
	 * 添加 忽略拼接租户条件的临时表
	 * @param list 忽略拼接租户条件的临时表，此表不进行租户条件拼接
	 */
	public static void putIgnoreTables(@NonNull List<String> list) {
		put(MdcConstants.TMP_IGNORE_TABLES, list);
	}

	/**
	 * 获取 忽略拼接租户条件的临时表
	 * @return 忽略拼接租户条件的临时表
	 */
	public static List<String> getIgnoreTables() {
		return getList(MdcConstants.TMP_IGNORE_TABLES);
	}

	/**
	 * 设置 拼接租户条件的临时租户ID
	 * @param value 拼接租户条件的临时租户ID
	 */
	public static void putTmpTenantId(@NonNull Long value) {
		putTmpTenantId(String.valueOf(value));
	}

	/**
	 * 设置 拼接租户条件的临时租户ID
	 * @param value 拼接租户条件的临时租户ID
	 */
	public static void putTmpTenantId(@NonNull String value) {
		MDC.put(MdcConstants.TMP_TENANT_ID, value);
	}

	/**
	 * 获取 拼接租户条件的临时租户ID
	 */
	public static String getTmpTenantId() {
		return MDC.get(MdcConstants.TMP_TENANT_ID);
	}

	/**
	 * 清空 拼接租户条件的临时租户ID
	 */
	public static void clearTmpTenantId() {
		MDC.remove(MdcConstants.TMP_TENANT_ID);
	}

}
