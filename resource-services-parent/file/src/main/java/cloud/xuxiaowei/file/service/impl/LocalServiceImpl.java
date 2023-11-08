package cloud.xuxiaowei.file.service.impl;

import cloud.xuxiaowei.core.properties.CloudFileProperties;
import cloud.xuxiaowei.file.service.LocalService;
import cloud.xuxiaowei.utils.FileUtils;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 上传文件到本地（本地储存） 实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class LocalServiceImpl implements LocalService {

	/**
	 * 正则表达式,表示只允许数字、字母、/ 和 -
	 */
	private static final String PATH_REGEX = "^[a-zA-Z0-9/\\-]+$";

	/**
	 * 多 . 后缀名
	 */
	private static final List<String> MULTIPART_SUFFIX_LIST = Arrays.asList(".tar.gz", ".tar.xz");

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
	@Override
	public String upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file, String path) {

		// 检查上传路径是否合法
		if (StringUtils.hasText(path) && !path.matches(PATH_REGEX)) {
			throw new CloudRuntimeException("路径不合法");
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
			throw new CloudRuntimeException("文件名不能为空");
		}

		long maxSize = uploadLocalConfig.getMaxSize();
		if (size > maxSize) {
			String formatFileMaxSize = FileUtils.formatFileSize(maxSize);
			throw new CloudRuntimeException(String.format("文件大小超过限制（%s）：%s", formatFileMaxSize, formatFileSize));
		}

		String fileExtension = null;
		for (String suffix : MULTIPART_SUFFIX_LIST) {
			if (originalFileName.endsWith(suffix)) {
				fileExtension = suffix;
				break;
			}
		}
		if (fileExtension == null) {
			fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
		}

		List<String> suffixes = uploadLocalConfig.getSuffixes();
		if (!suffixes.contains(fileExtension)) {
			throw new CloudRuntimeException("不支持此文件类型");
		}

		String resourceLocation = uploadLocalConfig.getResourceLocation();

		// 检查并创建文件夹
		String separate = "file:";
		String parent = resourceLocation.startsWith(separate) ? resourceLocation.substring(separate.length())
				: resourceLocation;
		File folder = new File(parent, path);
		if (!folder.exists()) {
			boolean mkdir = folder.mkdir();
			if (mkdir) {
				log.info("文件夹 {} 创建结果：{}", folder.getPath(), true);
			}
			else {
				log.error("文件夹 {} 创建结果：{}", folder.getPath(), false);
				throw new CloudRuntimeException("上传时文件夹创建失败");
			}
		}

		String fileName = UUID.randomUUID() + fileExtension;

		Path filePath = Paths.get(folder.getPath(), fileName);
		try {
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e) {
			throw new CloudRuntimeException("储存文件异常", e);
		}

		String domain = uploadLocalConfig.getDomain();

		if (domain == null) {
			domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		}

		String resourceHandler = uploadLocalConfig.getResourceHandler();

		String url;
		if (resourceHandler.endsWith("**")) {
			url = domain + resourceHandler.substring(0, resourceHandler.length() - 2) + path + "/" + fileName;
		}
		else {
			url = domain + resourceHandler + path + "/" + fileName;
		}

		return url;
	}

}
