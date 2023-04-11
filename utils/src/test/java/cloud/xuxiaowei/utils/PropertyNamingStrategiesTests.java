package cloud.xuxiaowei.utils;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * {@link PropertyNamingStrategies} 命名法测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class PropertyNamingStrategiesTests {

	@Test
	void translate() {

		// 首字母大写的驼峰命名法
		// "userName" is translated to "UserName"
		log.info(new PropertyNamingStrategies.UpperCamelCaseStrategy().translate("userName"));

		// 首字母小写横线命名法
		// "userName" is translated to "user-name"
		log.info(new PropertyNamingStrategies.KebabCaseStrategy().translate("userName"));

		// 小写字母下划线命名法
		// "userName" is translated to "user_name"
		// "UserName" is translated to "user_name"
		// "USER_NAME" is translated to "user_name"
		// "user_name" is translated to "user_name" (unchanged)
		// "user" is translated to "user" (unchanged)
		// "User" is translated to "user"
		// "USER" is translated to "user"
		// "_user" is translated to "user"
		// "_User" is translated to "user"
		// "__user" is translated to "_user" (the first of two underscores was removed)
		// "user__name" is translated to "user__name" (unchanged, with two underscores)
		log.info(new PropertyNamingStrategies.SnakeCaseStrategy().translate("userName"));

		// 大写字母的下划线命名法
		// "userName" is translated to "USER_NAME"
		log.info(new PropertyNamingStrategies.UpperSnakeCaseStrategy().translate("userName"));

		// 转小写
		// "userName" is translated to "username"
		log.info(new PropertyNamingStrategies.LowerCaseStrategy().translate("userName"));

		// 小数点分隔命名法
		// "userName" is translated to "user.name"
		log.info(new PropertyNamingStrategies.LowerDotCaseStrategy().translate("userName"));
	}

}
