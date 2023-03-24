// package cloud.xuxiaowei.passport.configuration;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
// import
// org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
// import
// org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//
// import java.util.Collections;
// import java.util.List;
//
/// **
// * Security 方法表达式处理程序
// * <p>
// * 优先级参见：{@link cloud.xuxiaowei.oauth2.configuration.PermissionEvaluatorConfiguration}
// *
// * @author xuxiaowei
// * @since 0.0.1
// */
// @Configuration
// public class MethodSecurityExpressionHandlerConfiguration {
//
// private PermissionEvaluatorServiceConfiguration
// permissionEvaluatorServiceConfiguration;
//
// @Autowired
// public void
// setPermissionEvaluatorServiceConfiguration(PermissionEvaluatorServiceConfiguration
// permissionEvaluatorServiceConfiguration) {
// this.permissionEvaluatorServiceConfiguration = permissionEvaluatorServiceConfiguration;
// }
//
// /**
// * 自定义 Security 方法表达式处理程序（权限评估程序）
// * <p>
// * 由于
// * {@link GlobalMethodSecurityConfiguration#setMethodSecurityExpressionHandler(List)}
// * 限制，该 List 只能设置一个值
// * <p>
// * 优先级参见：{@link cloud.xuxiaowei.oauth2.configuration.PermissionEvaluatorConfiguration}
// * @see GlobalMethodSecurityConfiguration#setMethodSecurityExpressionHandler(List)
// * @see cloud.xuxiaowei.oauth2.configuration.PermissionEvaluatorConfiguration
// */
// @Bean
// public List<MethodSecurityExpressionHandler> methodSecurityExpressionHandler() {
// DefaultMethodSecurityExpressionHandler expressionHandler = new
// DefaultMethodSecurityExpressionHandler();
// // 更推荐使用 Bean（可以在 接口 PermissionEvaluator 的实现中使用其他 Bean）
// expressionHandler.setPermissionEvaluator(permissionEvaluatorServiceConfiguration);
// return Collections.singletonList(expressionHandler);
// }
//
// }
