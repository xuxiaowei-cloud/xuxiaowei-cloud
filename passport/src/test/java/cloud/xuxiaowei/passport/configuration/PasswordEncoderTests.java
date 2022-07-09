package cloud.xuxiaowei.passport.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码编辑器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class PasswordEncoderTests {

	/**
	 * 密码加密
	 */
	@Test
	void encode() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encode = passwordEncoder.encode("123");
		log.info(encode);
	}

	/**
	 * 密码比较
	 */
	@Test
	void matches() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPassword = "{bcrypt}$2a$10$UEX4P9awppGO0DACKpGbpOmcViKZqbG5ObTOr8viJJvAh1AFOGHkK";
		boolean matches = passwordEncoder.matches("123", encodedPassword);
		log.info(String.valueOf(matches));
	}

}
