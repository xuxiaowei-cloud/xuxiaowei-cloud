package cloud.xuxiaowei.canal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * 用于支持：@PreAuthorize("#oauth2.hasScope('snsapi_base')")
 * <p>
 * 需要新增配置：spring.main.allow-bean-definition-overriding: true
 * <p>
 * 本类不可移动到组件jar包中（写在哪里，就只能在哪里使用）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class Oauth2MethodSecurityExpressionHandlerGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

}
