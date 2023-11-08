package cloud.xuxiaowei.utils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 常量
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class Constants implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数字
	 */
	public static final List<String> DIGIT_LIST = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

	/**
	 * 数字
	 */
	public static final String DIGITS = "0123456789";

	/**
	 * 小写字母
	 */
	public static final List<String> LOWER_CASE_LETTER_LIST = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i",
			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

	/**
	 * 小写字母
	 */
	public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 大写字母
	 */
	public static final List<String> UPPER_CASE_LETTER_LIST = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

	/**
	 * 大写字母
	 */
	public static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 符号
	 */
	public static final List<String> SYMBOL_LIST = Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-",
			"_", "=", "+", "[", "{", "]", "}", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?");

	/**
	 * 符号
	 */
	public static final String SYMBOLS = "!@#$%^&*()-_=+[{]};:'\",<.>/?";

	/**
	 * 字符合集
	 */
	public static final List<String> ALL_CHAR_LIST = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-",
			"_", "=", "+", "[", "{", "]", "}", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?");

	/**
	 * 字符合集
	 */
	public static final String ALL_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-_=+[{]};:'\",<.>/?";

	/**
	 *
	 */
	public static final String MF_NAME = "/META-INF/MANIFEST.MF";

	/**
	 *
	 */
	public static final String BUILD_TIME = "Build-Time";

	/**
	 * 时间戳字段
	 */
	public static final String CURRENT_TIME_MILLIS = "currentTimeMillis";

	/**
	 * HTTP 响应中加密描述的字段
	 */
	public static final String ENCRYPT = "encrypt";

	/**
	 * HTTP 响应中解密描述的字段
	 */
	public static final String DECRYPT = "decrypt";

	/**
	 * HTTP 响应中签名描述的字段
	 */
	public static final String SIGN = "sign";

	/**
	 * 默认值
	 */
	public static final String DEFAULT = "default";

	/**
	 * Token 中传递权限的名称
	 */
	public static final String AUTHORITIES = "authorities";

	/**
	 * 端点
	 */
	public static final String ACTUATOR = "actuator";

	/**
	 * 用户名
	 */
	public static final String USERNAME = "username";

	/**
	 * 用户ID
	 */
	public static final String USERS_ID = "usersId";

	/**
	 * 超级租户
	 */
	public static final String SUPER_TENANT = "superTenant";

	/**
	 * null 值
	 */
	public static final String NULL = "null";

	/**
	 * 参数名
	 */
	public static final String PARAMETER_NAME = "parameterName";

	/**
	 * 参数类型
	 */
	public static final String PARAMETER_TYPE = "parameterType";

	/**
	 * 范围
	 */
	public static final String SCOPE = "scope";

	/**
	 * 不明确
	 */
	public static final String UNDEFINED = "undefined";

	/**
	 * 微信小程序appid
	 */
	public static final String APPID = "appid";

	/**
	 * 微信小程序 openid
	 */
	public static final String OPENID = "openid";

	/**
	 * 微信小程序 unionid
	 */
	public static final String UNIONID = "unionid";

	/**
	 * 授权类型
	 */
	public static final String GRANT_TYPE = "grant_type";

	/**
	 * 私钥
	 */
	public static final String PRIVATE_KEY = "privateKey";

	/**
	 * 图片验证码
	 */
	public static final String PATCHCA = "patchca";

	/**
	 * 租户ID
	 */
	public static final String TENANT_ID = "tenantId";

}
