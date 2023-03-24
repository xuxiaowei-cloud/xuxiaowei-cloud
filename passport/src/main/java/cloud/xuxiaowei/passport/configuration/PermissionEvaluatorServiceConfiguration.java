package cloud.xuxiaowei.passport.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * 权限评估程序
 * <p>
 * 优先级参见：{@link cloud.xuxiaowei.oauth2.configuration.PermissionEvaluatorConfiguration}
 *
 * @see cloud.xuxiaowei.oauth2.configuration.PermissionEvaluatorConfiguration
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class PermissionEvaluatorServiceConfiguration implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		log.warn("服务 的 权限评估（需要自己实现），参数：authentication：{}, permission：{}", authentication, permission);
		return true;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		log.warn("服务 的 权限评估（需要自己实现），参数：authentication：{}, targetId：{}，targetType：{}，permission：{}", authentication,
				targetId, targetType, permission);
		return true;
	}

}
