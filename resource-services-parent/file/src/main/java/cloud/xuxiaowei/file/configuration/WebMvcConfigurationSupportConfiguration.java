package cloud.xuxiaowei.file.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC、拦截器 配置
 * <p>
 * {@link WebMvcConfigurationSupport} 优先级比 {@link WebMvcConfigurer} 高， 使用了
 * {@link WebMvcConfigurationSupport} 之后，{@link WebMvcConfigurer} 会失效
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class WebMvcConfigurationSupportConfiguration extends WebMvcConfigurationSupport {

	public static final String RESOURCE_HANDLER = "/uploads/**";

	/**
	 * 不处理静态资源后缀名返回的格式
	 * @param registry 静态资源处理
	 */
	@Override
	protected void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {

		// 项目内静态文件处理
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		// 项目外静态文件处理
		String osName = System.getProperty("os.name");
		String win = "win";
		if (osName.toLowerCase().startsWith(win)) {
			registry.addResourceHandler(RESOURCE_HANDLER).addResourceLocations("file:D:\\uploads\\");
		}
		else {
			registry.addResourceHandler(RESOURCE_HANDLER).addResourceLocations("file:/uploads/");
		}

	}

}