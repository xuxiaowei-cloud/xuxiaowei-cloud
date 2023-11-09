package cloud.xuxiaowei.file.controller;

import cloud.xuxiaowei.file.service.LocalService;
import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.map.ResponseMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上传文件到本地（本地储存）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/upload/local")
public class UploadLocalRestController {

	private LocalService localService;

	@Autowired
	public void setLocalService(LocalService localService) {
		this.localService = localService;
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

		String url = localService.upload(request, response, file, path);

		return ResponseMap.ok().put("url", url);
	}

}
