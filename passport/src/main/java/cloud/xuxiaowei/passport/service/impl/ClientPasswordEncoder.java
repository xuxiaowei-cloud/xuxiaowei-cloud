package cloud.xuxiaowei.passport.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 客户密码编辑器
 *
 * @author xuxiaowei
 * @see PasswordEncoderFactories#createDelegatingPasswordEncoder()
 * @since 0.0.1
 */
@Slf4j
public class ClientPasswordEncoder implements PasswordEncoder {

	public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	public String encode(CharSequence rawPassword) {
		return PASSWORD_ENCODER.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		log.debug("客户密码比较");
		return PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
	}

}
