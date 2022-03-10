package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.configuration.DefaultBeanConfiguration;
import cloud.xuxiaowei.utils.exception.LoginParamPasswordNonExistException;
import cloud.xuxiaowei.utils.exception.LoginParamPasswordValidException;
import lombok.SneakyThrows;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * 默认密码编辑器
 *
 * @author xuxiaowei
 * @see PasswordEncoderFactories#createDelegatingPasswordEncoder()
 * @see DefaultBeanConfiguration#passwordEncoder
 * @since 0.0.1
 */
public class DefaultPasswordEncoderImpl implements PasswordEncoder {

    @SneakyThrows
    @Override
    public String encode(CharSequence rawPassword) {
        if (StringUtils.hasText(rawPassword)) {
            return rawPassword.toString();
        } else {
            throw new LoginParamPasswordNonExistException();
        }
    }

    @SneakyThrows
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        if (!matches) {
            throw new LoginParamPasswordValidException();
        }
        return true;
    }

}
