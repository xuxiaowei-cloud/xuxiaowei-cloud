package cloud.xuxiaowei.utils;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * {@link CaseFormat} 命名法测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class CaseFormatTests {

	/**
	 * 转 Java 变量命名约定
	 * <p>
	 * 其他命名法相互转换雷同
	 */
	@Test
	void toLowerCamel() {

		// lowerHyphen
		log.info(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "lower-hyphen"));

		// lowerUnderscore
		log.info(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "lower_underscore"));

		// upperCamel
		log.info(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, "UpperCamel"));

		// upperUnderscore
		log.info(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "UPPER_UNDERSCORE"));

	}

}
