package cloud.xuxiaowei.file.configuration;

import cloud.xuxiaowei.core.properties.CloudFileProperties;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Web MVC、拦截器 配置
 * <p>
 * {@link WebMvcConfigurationSupport} 优先级比 {@link WebMvcConfigurer} 高， 使用了
 * {@link WebMvcConfigurationSupport} 之后，{@link WebMvcConfigurer} 会失效
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class WebMvcConfigurationSupportConfiguration extends WebMvcConfigurationSupport {

	private CloudFileProperties cloudFileProperties;

	@Autowired
	public void setCloudFileProperties(CloudFileProperties cloudFileProperties) {
		this.cloudFileProperties = cloudFileProperties;
	}

	/**
	 * 不处理静态资源后缀名返回的格式
	 * @param registry 静态资源处理
	 */
	@Override
	protected void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {

		// 项目内静态文件处理
		// 没有此配置，将无法访问项目内静态文件，如：src/main/resources/static/favicon.ico
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		List<CloudFileProperties.MvcConfig> mvcConfigs = cloudFileProperties.getMvcConfigs();
		for (CloudFileProperties.MvcConfig mvcConfig : mvcConfigs) {
			String[] resourceHandlers = mvcConfig.getResourceHandlers();
			String[] resourceLocations = mvcConfig.getResourceLocations();
			registry.addResourceHandler(resourceHandlers).addResourceLocations(resourceLocations);
		}

		// 项目外静态文件处理
		String osName = System.getProperty("os.name");
		log.info("当前系统名称：{}", osName);
		String win = "win";
		if (osName.toLowerCase().startsWith(win)) {
			log.info("当前系统是 Windows");
		}
		else {
			log.info("当前系统不是 Windows");
		}

	}

}