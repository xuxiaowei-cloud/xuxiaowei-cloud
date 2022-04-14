package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.controller.IndexController;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordNonExistException;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordValidException;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 默认密码编辑器
 *
 * @author xuxiaowei
 * @see PasswordEncoderFactories#createDelegatingPasswordEncoder()
 * @since 0.0.1
 */
@Component
public class DefaultPasswordEncoderImpl implements PasswordEncoder {

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
            throw new LoginParamPasswordNonExistException("登录参数不存在密码");
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        HttpSession session = request.getSession();
        String rsaPrivateKeyBase64 = session.getAttribute(IndexController.RSA_PRIVATE_KEY_BASE64) + "";

        boolean matches;
        try {

            RSA rsa = new RSA(rsaPrivateKeyBase64, null);
            String rawPasswordDecryptStr = rsa.decryptStr(rawPassword.toString(), KeyType.PrivateKey);

            matches = passwordEncoder.matches(rawPasswordDecryptStr, encodedPassword);
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
