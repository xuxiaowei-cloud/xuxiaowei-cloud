// package cloud.xuxiaowei.gateway.configuration;
//
// import org.springdoc.core.GroupedOpenApi;
// import org.springdoc.core.SwaggerUiConfigParameters;
// import org.springframework.cloud.gateway.route.RouteDefinition;
// import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Lazy;
//
// import java.net.URI;
// import java.util.ArrayList;
// import java.util.List;
//
/// **
// * SpringDoc 配置
// * <p>
// * 使用 springdoc.swagger-ui.urls 配置后，此处配置将无效
// *
// * @author xuxiaowei
// * @since 0.0.1
// * @see <a href=
// *
// "https://github.com/springdoc/springdoc-openapi-demos/blob/master/springdoc-openapi-microservices/gateway-service/src/main/java/org/springdoc/demo/services/gateway/GatewayApplication.java">Gateway
// * Configuration</a>
// */
// @Configuration
// public class SpringdocConfiguration {
//
// /**
// * 注意：官方示例此处有错误
// * <p>
// * 使用 springdoc.swagger-ui.urls 配置后，此处配置将无效
// * @see <a href=
// *
// "https://github.com/springdoc/springdoc-openapi-demos/blob/master/springdoc-openapi-microservices/gateway-service/src/main/java/org/springdoc/demo/services/gateway/GatewayApplication.java">Gateway
// * Configuration</a>
// */
// @Bean
// @Lazy(false)
// public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters,
// RouteDefinitionLocator locator) {
// List<GroupedOpenApi> groups = new ArrayList<>();
// List<RouteDefinition> definitions =
// locator.getRouteDefinitions().collectList().block();
// assert definitions != null;
// definitions.forEach(routeDefinition -> {
// String id = routeDefinition.getId();
// if (!"openapi".equals(id)) {
// URI uri = routeDefinition.getUri();
// String host = uri.getHost();
// swaggerUiConfigParameters.addGroup(host);
// GroupedOpenApi build = GroupedOpenApi.builder().pathsToMatch("/" + host +
// "/**").group(host).build();
// groups.add(build);
// }
// });
// return groups;
// }
//
// }
