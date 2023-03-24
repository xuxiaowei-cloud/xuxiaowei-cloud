// package cloud.xuxiaowei.oauth2.configuration;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.access.PermissionEvaluator;
// import
// org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
// import
// org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
// import org.springframework.security.core.Authentication;
//
// import java.io.Serializable;
// import java.util.List;
//
/// **
// * 权限评估程序
// * <p>
// * 优先级：
// * <p>
// * 1. {@link List} 类型的 接口 {@link MethodSecurityExpressionHandler} 的 {@link Bean}，
// * {@link Bean} 的返回值使用 {@link DefaultMethodSecurityExpressionHandler}，在
// * {@link
// DefaultMethodSecurityExpressionHandler#setPermissionEvaluator(PermissionEvaluator)}
// * 中设置 接口 {@link MethodSecurityExpressionHandler} 的实现
// * <p>
// * 2. 当只有一个类型为 接口 {@link MethodSecurityExpressionHandler} 的实现 {@link Bean}
// *
// * @author xuxiaowei
// * @since 0.0.1
// */
// @Slf4j
// @Configuration
// public class PermissionEvaluatorConfiguration implements PermissionEvaluator {
//
// @Override
// public boolean hasPermission(Authentication authentication, Object targetDomainObject,
// Object permission) {
// log.warn("cloud-starter-oauth2组件 的 权限评估（需要自己实现），参数：authentication：{}, permission：{}",
// authentication,
// permission);
// return true;
// }
//
// @Override
// public boolean hasPermission(Authentication authentication, Serializable targetId,
// String targetType,
// Object permission) {
// log.warn("cloud-starter-oauth2组件 的 权限评估（需要自己实现），参数：authentication：{},
// targetId：{}，targetType：{}，permission：{}",
// authentication, targetId, targetType, permission);
// return true;
// }
//
// }
