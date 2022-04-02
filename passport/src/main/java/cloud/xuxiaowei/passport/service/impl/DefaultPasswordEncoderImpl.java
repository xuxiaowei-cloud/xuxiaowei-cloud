package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.configuration.DefaultBeanConfiguration;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordNonExistException;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordValidException;
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

    @Override
    public String encode(CharSequence rawPassword) {
        if (StringUtils.hasText(rawPassword)) {
            return rawPassword.toString();
        } else {
            throw new LoginParamPasswordNonExistException("登录参数不存在密码");
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        boolean matches;
        try {
            matches = passwordEncoder.matches(rawPassword, encodedPassword);
        } catch (Exception e) {
            // 可能根据用户名没有找到用户信息（即：密码），导致比较失败
            throw new LoginParamPasswordValidException("比较密码时异常", e);
        }
        if (!matches) {
            throw new LoginParamPasswordValidException("密码不匹配");
        }

        return true;
    }

}
