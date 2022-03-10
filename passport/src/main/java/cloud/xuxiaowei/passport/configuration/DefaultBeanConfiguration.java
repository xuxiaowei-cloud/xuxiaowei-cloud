package cloud.xuxiaowei.passport.configuration;

import cloud.xuxiaowei.passport.service.impl.DefaultPasswordEncoderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 默认 {@link Bean} 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class DefaultBeanConfiguration {

    /**
     * 默认密码编辑器 {@link Bean}
     *
     * @return 返回 默认密码编辑器 {@link Bean}
     */
    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new DefaultPasswordEncoderImpl();
    }

}
