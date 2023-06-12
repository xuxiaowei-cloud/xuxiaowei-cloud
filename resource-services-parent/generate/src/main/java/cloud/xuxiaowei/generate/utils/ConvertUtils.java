package cloud.xuxiaowei.generate.utils;

import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * 转换 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class ConvertUtils {

	/**
	 * 数据库字段类型 转 Java属性类型
	 * @param map 映射表
	 * @param fieldType 数据库字段类型
	 * @return 返回 Java属性类型
	 */
	public static String fieldTypeToPropertyType(@NonNull Map<String, String> map, @NonNull String fieldType) {
		int indexOf = fieldType.indexOf("(");
		if (indexOf == -1) {
			return map.get(fieldType);
		}
		else {
			String key = fieldType.substring(0, indexOf);
			return map.get(key);
		}
	}

}
