package cloud.xuxiaowei.authorizationserver.service.impl;

import cloud.xuxiaowei.utils.ClientType;
import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.ServiceEnums;
import cloud.xuxiaowei.utils.exception.login.LoginParamPasswordNonExistException;
import cloud.xuxiaowei.utils.exception.login.LoginServiceException;
import cloud.xuxiaowei.utils.map.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    private final String NO_INSTANCES_AVAILABLE = "No instances available ";

    private HttpServletRequest request;

    private RestTemplate restTemplate;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        // 客户端类型
        String clientType = request.getParameter(Constant.CLIENT_TYPE);

        // 微信客户端
        if (ClientType.WECHAT_APPLET.grantType.equals(grantType) && ClientType.WECHAT_APPLET.clientType.equals(clientType)) {
            return wechatApplet(rawPassword);
        }

        boolean matches;
        try {
            matches = passwordEncoder.matches(rawPassword, encodedPassword);
        } catch (Exception e) {
            log.debug("用户密码验证异常", e);
            matches = false;
        }

        return matches;
    }

    private boolean wechatApplet(CharSequence code) {
        String service = ServiceEnums.WECHAT_APPLET.service;

        try {
            ResponseMap responseMap = restTemplate.getForObject(String.format("http://%s/onLogin?code=%s", service, code), ResponseMap.class);
            if (responseMap == null) {
                throw new LoginServiceException("微信服务授权失败：返回值为空");
            }

            String responseMapCode = responseMap.getCode();
            if (CodeEnums.OK.code.equals(responseMapCode)) {
                Map<String, Object> data = responseMap.getData();
                if (data == null) {
                    throw new LoginServiceException("微信服务授权失败：返回值data为空");
                }

                Object openidObj = data.get(Constant.OPENID);
                if (openidObj == null) {
                    throw new LoginServiceException("微信服务授权失败：返回值openid为空");
                }

                // 验证微信小程序openid（即参数中的 username）
                String openid = request.getParameter(Constant.USERNAME);
                if (!openidObj.equals(openid)) {
                    throw new LoginServiceException("微信服务授权失败：非法openid");
                }

                Object appidObj = data.get(Constant.APPID);
                if (appidObj == null) {
                    throw new LoginServiceException("微信服务授权失败：返回值appid为空");
                }

                // 验证微信小程序appid
                String appid = request.getParameter(Constant.APPID);
                if (!appidObj.equals(appid)) {
                    throw new LoginServiceException("微信服务授权失败：非法appid");
                }

                return true;
            } else {
                throw new LoginServiceException("微信服务授权失败：状态码异常：" + responseMapCode);
            }
        } catch (Exception e) {
            log.error("微信授权异常", e);
            String message = e.getMessage();
            if ((NO_INSTANCES_AVAILABLE + service).equals(message)) {
                throw new LoginServiceException("授权时：微信服务不可用");
            }
            throw new LoginServiceException("授权时：微信服务异常");
        }
    }

}
