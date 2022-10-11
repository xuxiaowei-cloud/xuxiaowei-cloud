package cloud.xuxiaowei.utils;

import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
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

}
