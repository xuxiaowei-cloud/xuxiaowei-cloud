package cloud.xuxiaowei.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URL 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class UrlUtils {

	/**
	 * 中文匹配
	 */
	private static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

	/**
	 * 根据 URI、参数名，获取参数值
	 * @param uri URI
	 * @param queryName 参数名
	 * @return 返回 参数值
	 */
	public static String getQueryValue(URI uri, String queryName) {
		if (uri == null || queryName == null) {
			return null;
		}
		String uriQuery = uri.getQuery();
		String[] querySplit = uriQuery.split("&");
		for (String query : querySplit) {
			String[] split = query.split("=");
			if (queryName.equals(split[0])) {
				return split[1];
			}
		}
		return null;
	}

	/**
	 * 转译 URL 中的中文
	 * @param url URL
	 * @return 返回 已转译中文的URL
	 */
	public static String urlChineseEncode(String url) {
		Matcher matcher = CHINESE_PATTERN.matcher(url);
		while (matcher.find()) {
			String tmp = matcher.group();
			url = url.replaceAll(tmp, UriUtils.encode(tmp, StandardCharsets.UTF_8));
		}
		return url;
	}

	/**
	 * 转 URL 参数
	 * @param object Object 对象
	 * @return 返回 URL 参数
	 * @throws UnsupportedEncodingException URLEncoder 转码异常
	 */
	public static String convertUrlParam(@NonNull Object object) throws UnsupportedEncodingException {
		// 将对象转换成 Map
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
		});
		return convertUrlParam(map);
	}

	/**
	 * 转 URL 参数
	 * @param map Map 对象
	 * @return 返回 URL 参数
	 * @throws UnsupportedEncodingException URLEncoder 转码异常
	 */
	public static String convertUrlParam(@NonNull Map<String, Object> map) throws UnsupportedEncodingException {
		// 构建 URL 参数
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = URLEncoder.encode(entry.getKey(), "UTF-8");
			String value = URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8");
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(key).append("=").append(value.replace("&", "%26"));
		}
		return sb.toString();
	}

}
