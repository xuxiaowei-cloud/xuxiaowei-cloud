package cloud.xuxiaowei.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

/**
 * 随机字符串 工具类 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class RandomStringUtilsTests {

	@Test
	void random() {
		log.info(RandomStringUtils.random(36));
		log.info(RandomStringUtils.random(36, Constants.DIGITS));
		log.info(RandomStringUtils.random(36, Constants.LOWER_CASE_LETTERS));
		log.info(RandomStringUtils.random(36, Constants.UPPER_CASE_LETTERS));
		log.info(RandomStringUtils.random(36, Constants.SYMBOLS));
		log.info(RandomStringUtils.random(36, Constants.ALL_CHARS));

		log.info(RandomStringUtils.random(36, Constants.UPPER_CASE_LETTERS + Constants.LOWER_CASE_LETTERS));
		log.info(RandomStringUtils.random(36, Constants.DIGITS + Constants.UPPER_CASE_LETTERS));
		log.info(RandomStringUtils.random(36, Constants.DIGITS + Constants.LOWER_CASE_LETTERS));
		log.info(RandomStringUtils.random(36,
				Constants.DIGITS + Constants.LOWER_CASE_LETTERS + Constants.UPPER_CASE_LETTERS));
	}

}
