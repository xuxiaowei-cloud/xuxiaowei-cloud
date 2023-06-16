package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 微服务 文件 配置
 * <p>
 * 运行时刷新
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties("cloud.file")
public class CloudFileProperties {

	/**
	 * MVC 配置列表
	 * <p>
	 * 默认为空 list
	 * <p>
	 * 使用 MVC 配置的项目外文件映射为网络路径的配置
	 */
	private List<MvcConfig> mvcConfigs = new ArrayList<>();

	/**
	 * MVC 配置
	 * <p>
	 * 使用 MVC 配置的项目外文件映射为网络路径的配置
	 *
	 * @author xuxiaowei
	 * @since 0.0.1
	 */
	@Data
	public static class MvcConfig {

		/**
		 * 静态资源/网络路径
		 * <p>
		 * 需要将此路径加入 Security 白名单
		 * <p>
		 * 如：/file-data/**
		 */
		private String[] resourceHandlers;

		/**
		 * 本地资源/磁盘路径
		 * <p>
		 * Windows 示例：file:D:\\file-data\\、file:/file-data/（此配置代表当前磁盘根目录下的 /file-data/
		 * 文件夹）
		 * <p>
		 * Linux 示例：file:/file-data/
		 */
		private String[] resourceLocations;

	}

}
