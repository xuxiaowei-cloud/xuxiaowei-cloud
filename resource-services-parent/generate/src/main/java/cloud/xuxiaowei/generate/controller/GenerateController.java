package cloud.xuxiaowei.generate.controller;

import cloud.xuxiaowei.core.properties.CloudGenerateProperties;
import cloud.xuxiaowei.generate.bo.GenerateBo;
import cloud.xuxiaowei.generate.service.GenerateService;
import cloud.xuxiaowei.utils.DateUtils;
import com.google.common.net.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.zip.ZipOutputStream;

import static cn.hutool.core.date.DatePattern.PURE_DATETIME_PATTERN;
import static com.google.common.net.HttpHeaders.CONTENT_DISPOSITION;

/**
 * 代码生成 {@link Controller}
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Controller
public class GenerateController {

	private CloudGenerateProperties cloudGenerateProperties;

	private GenerateService generateService;

	@Autowired
	public void setCloudGenerateProperties(CloudGenerateProperties cloudGenerateProperties) {
		this.cloudGenerateProperties = cloudGenerateProperties;
	}

	@Autowired
	public void setGenerateService(GenerateService generateService) {
		this.generateService = generateService;
	}

	/**
	 * 列出所有的字段信息
	 * @param request 请求
	 * @param response 响应
	 */
	@PostMapping("/generate")
	@PreAuthorize("@ant.hasAuthority('generate')")
	public void generate(HttpServletRequest request, HttpServletResponse response,
			@Valid @RequestBody GenerateBo generateBo) throws IOException {

		LocalDateTime now = LocalDateTime.now();
		String fileName = DateUtils.format(now, PURE_DATETIME_PATTERN);
		String urlChineseEncode = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		String filename = String.format("\"%s.zip\"", urlChineseEncode);

		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		response.setContentType(MediaType.ZIP.toString());
		response.setHeader(CONTENT_DISPOSITION, "attachment; filename=" + filename);

		String filePath = generateService.filePath(cloudGenerateProperties, fileName);
		generateService.generate(generateBo, zos, filePath);
	}

}
