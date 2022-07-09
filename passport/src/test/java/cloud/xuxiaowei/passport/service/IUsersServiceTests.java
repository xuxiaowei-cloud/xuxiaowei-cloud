package cloud.xuxiaowei.passport.service;

import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户表 服务类 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@SpringBootTest
class IUsersServiceTests {

	@Autowired
	private IUsersService usersService;

	/**
	 * 根据 用户名 查询用户信息及权限
	 */
	@Test
	void getByUsername() {
		Users users = usersService.getByUsername("xuxiaowei");
		log.info(String.valueOf(users));
	}

}
