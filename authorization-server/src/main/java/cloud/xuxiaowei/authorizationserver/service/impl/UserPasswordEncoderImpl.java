package cloud.xuxiaowei.authorizationserver.service.impl;

import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordNonExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户密码编辑器
 * <p>
 * 只存在一个 {@link  PasswordEncoder} 的 {@link Bean} 时，可以用来验证用户的密码
 *
 * @author xuxiaowei
 * @see PasswordEncoderFactories#createDelegatingPasswordEncoder()
 * @since 0.0.1
 */
@Slf4j
@Component
public class UserPasswordEncoderImpl implements PasswordEncoder {

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if (StringUtils.hasText(rawPassword)) {
            return rawPassword.toString();
        } else {
            throw new LoginParamPasswordNonExistException("登录参数不存在用户密码");
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // 在 grantType 为 password 时，对密码进行处理后才能比较，这样更安全
        String grantType = request.getParameter(OAuth2Utils.GRANT_TYPE);

        boolean matches;
        try {
            matches = passwordEncoder.matches(rawPassword, encodedPassword);
        } catch (Exception e) {
            log.debug("用户密码验证异常", e);
            matches = false;
        }

        return matches;
    }

}
