package cloud.xuxiaowei.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CaseUtils;
import org.junit.jupiter.api.Test;

/**
 * {@link CaseUtils} 命名法测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class CaseUtilsTests {

	@Test
	void toCamelCase() {
		// str – 要转换为驼峰大小写的字符串，可能为空
		// capitalizeFirstLetter – 确定第一个单词的第一个字符是否应为标题大小写的布尔值。
		// delimiters –字符集以确定大写，null和/或空数组表示空格

		String fieldName = "myFieldName";

		// Myfieldname
		log.info(CaseUtils.toCamelCase(fieldName, true));

		// myfieldname
		log.info(CaseUtils.toCamelCase(fieldName, false));
	}

}
