package cloud.xuxiaowei.passport.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Passport API", version = "${springdoc.version}"))
public class SpringdocConfiguration {

}
