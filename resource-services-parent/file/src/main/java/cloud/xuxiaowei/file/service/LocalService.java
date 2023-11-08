package cloud.xuxiaowei.file.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上传文件到本地（本地储存） 服务接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface LocalService {

	/**
	 * 上传文件储存在本地磁盘
	 * @param request 请求
	 * @param response 响应
	 * @param file 文件
	 * @param path 上传的路径，默认为当前日期，只能包含数字、字母、/、-
	 * @return 返回上传结果
	 */
	String upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file, String path);

}
