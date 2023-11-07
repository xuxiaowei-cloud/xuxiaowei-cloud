package cloud.xuxiaowei.file.controller;

import cloud.xuxiaowei.core.properties.CloudFileProperties;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.FileUtils;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * 上传本地配置（本地储存配置）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/upload/local")
public class UploadLocalRestController {

	/**
	 * 正则表达式,表示只允许数字、字母、/ 和 -
	 */
	private static final String PATH_REGEX = "^[a-zA-Z0-9/\\-]+$";

	private CloudFileProperties cloudFileProperties;

	@Autowired
	public void setCloudFileProperties(CloudFileProperties cloudFileProperties) {
		this.cloudFileProperties = cloudFileProperties;
	}

	/**
	 * 上传文件储存在本地磁盘
	 * @param request 请求
	 * @param response 响应
	 * @param file 文件
	 * @param path 上传的路径，默认为当前日期，只能包含数字、字母、/、-
	 * @return 返回上传结果
	 */
	@PostMapping
	@ControllerAnnotation(description = "上传文件储存在本地磁盘")
	public Response<?> post(HttpServletRequest request, HttpServletResponse response, MultipartFile file, String path) {

		// 检查上传路径是否合法
		if (StringUtils.hasText(path) && !path.matches(PATH_REGEX)) {
			return Response.error("路径不合法");
		}
		else {
			path = LocalDate.now().toString();
		}

		long size = file.getSize();
		String contentType = file.getContentType();
		String originalFileName = file.getOriginalFilename();

		String formatFileSize = FileUtils.formatFileSize(size);

		log.info("接收到 {} 类型的文件：{}，大小：{}（{}）", contentType, originalFileName, size, formatFileSize);

		// 获取配置
		CloudFileProperties.UploadLocalConfig uploadLocalConfig = cloudFileProperties.getUploadLocalConfig();

		if (originalFileName == null) {
			return Response.error("文件名不能为空");
		}

		long maxSize = uploadLocalConfig.getMaxSize();
		if (size > maxSize) {
			String formatFileMaxSize = FileUtils.formatFileSize(maxSize);
			return Response.error(String.format("文件大小超过限制（%s）：%s", formatFileMaxSize, formatFileSize));
		}

		String fileExtension;
		String targz = ".tar.gz";
		if (originalFileName.endsWith(targz)) {
			fileExtension = targz;
		}
		else {
			fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		}

		List<String> suffixes = uploadLocalConfig.getSuffixes();
		if (!suffixes.contains(fileExtension)) {
			return Response.error("不支持此文件类型");
		}

		String resourceLocation = uploadLocalConfig.getResourceLocation();

		// 检查并创建文件夹
		String separate = "file:";
		String parent = resourceLocation.startsWith(separate) ? resourceLocation.substring(separate.length())
				: resourceLocation;
		File folder = new File(parent, path);
		if (!folder.exists()) {
			boolean mkdir = folder.mkdir();
			log.info("文件夹 {} 创建结果：{}", folder.getPath(), mkdir);
		}

		String fileName = UUID.randomUUID() + fileExtension;

		Path filePath = Paths.get(folder.getPath(), fileName);
		try {
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e) {
			throw new CloudRuntimeException("储存文件异常", e);
		}

		return Response.ok();
	}

}
