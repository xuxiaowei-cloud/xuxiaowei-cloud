package cloud.xuxiaowei.passport.service;

import cloud.xuxiaowei.system.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详情服务 接口实现
 */
@Slf4j
@SpringBootTest
class UserDetailsServiceTests {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	/**
	 * 根据 用户 查询用户信息与权限
	 */
	@Test
	void loadUserByUsername() {
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("xuxiaowei");
		log.info(String.valueOf(userDetails));
	}

}
