package cloud.xuxiaowei.authorizationserver.service.impl;

import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordNonExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户密码编辑器
 * <p>
 * 该类不能是 {@link Bean}，否则 {@link UserPasswordEncoderImpl} 无效
 * <p>
 * 本类使用 {@link #setApplicationContext(ApplicationContext)} 来获取 {@link Bean}
 *
 * @author xuxiaowei
 * @see PasswordEncoderFactories#createDelegatingPasswordEncoder()
 * @since 0.0.1
 */
@Slf4j
public class ClientPasswordEncoderImpl implements PasswordEncoder {

    /**
     * 用于获取 Bean
     */
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if (StringUtils.hasText(rawPassword)) {
            return rawPassword.toString();
        } else {
            throw new LoginParamPasswordNonExistException("登录参数不存在客户凭证");
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String grantType;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.warn("RequestContextHolder.getRequestAttributes() 为空，请配置：`hystrix.command.default.execution.isolation.strategy=SEMAPHORE`");
            grantType = null;
        } else {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            // 在 grantType 为 password 时，对密码进行处理后才能比较，这样更安全
            grantType = request.getParameter(OAuth2Utils.GRANT_TYPE);
        }

        boolean matches;
        try {
            matches = passwordEncoder.matches(rawPassword, encodedPassword);
        } catch (Exception e) {
            log.debug("客户凭证验证异常", e);
            matches = false;
        }

        return matches;
    }

}
