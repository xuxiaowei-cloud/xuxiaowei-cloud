package cloud.xuxiaowei.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

/**
 * 响应数据
 *
 * @param <T> 泛型
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String CODE = "code";

	public static final String MSG = "msg";

	public static final String DATA = "data";

	public static final String FIELD = "field";

	public static final String EXPLAIN = "explain";

	public static final String REQUEST_ID = "requestId";

	/**
	 * 仅为自动装载数据使用
	 */
	private Response() {

	}

	public Response(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Response(String code, String msg, String field) {
		this.code = code;
		this.msg = msg;
		this.field = field;
	}

	public static Response<?> ok() {
		return new Response<>(CodeEnums.OK.code, CodeEnums.OK.msg);
	}

	public static Response<?> ok(String msg) {
		return new Response<>(CodeEnums.OK.code, msg);
	}

	public static <T> Response<?> ok(T data) {
		Response<T> response = new Response<>(CodeEnums.OK.code, CodeEnums.OK.msg);
		response.setData(data);
		return response;
	}

	public static <T> Response<?> ok(T data, String msg) {
		Response<T> response = new Response<>(CodeEnums.OK.code, msg);
		response.setData(data);
		return response;
	}

	public static Response<?> error() {
		return new Response<>(CodeEnums.ERROR.code, CodeEnums.ERROR.msg);
	}

	public static Response<?> error(String msg) {
		return new Response<>(CodeEnums.ERROR.code, msg);
	}

	public static Response<?> error(String code, String msg) {
		return new Response<>(code, msg);
	}

	/**
	 * 响应代码
	 *
	 * @see CodeEnums#code
	 */
	private String code;

	/**
	 * 响应消息
	 *
	 * @see CodeEnums#msg
	 */
	private String msg;

	/**
	 * 返回数据
	 */
	private T data;

	/**
	 * 错误字段
	 * <p>
	 * 存在多个时，使用英文逗号隔开
	 */
	private String field;

	/**
	 * 说明
	 */
	private String explain;

	/**
	 * 请求ID
	 */
	private String requestId;

	/**
	 * 打包后 Maven 坐标版本号
	 * <p>
	 * 需要在 org.apache.maven.plugins:maven-jar-plugin 的 configuration &gt; archive &gt;
	 * manifestEntries &gt; Implementation-Version 添加 ${project.version}
	 */
	@Setter(AccessLevel.NONE)
	private String implVersion;

	/**
	 * 打包时的时间
	 */
	@Setter(AccessLevel.NONE)
	private String buildTime;

	/**
	 * 打包时的时间
	 */
	@Setter(AccessLevel.NONE)
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime buildTimeParse;

	/**
	 * 获取 请求ID
	 * @return 在请求ID 为 null，返回 MDC 中的 请求ID
	 */
	public String getRequestId() {
		if (this.requestId == null) {
			return MDC.get(Constant.REQUEST_ID);
		}
		return this.requestId;
	}

	public String getImplVersion() {
		Package pkg = getClass().getPackage();
		return pkg.getImplementationVersion();
	}

	public String getBuildTime() {
		Manifest manifest;
		try {
			manifest = new Manifest(getClass().getResourceAsStream(Constant.MF_NAME));
		}
		catch (IOException e) {
			return null;
		}
		Attributes attributes = manifest.getMainAttributes();
		return attributes.getValue(Constant.BUILD_TIME);
	}

	public LocalDateTime getBuildTimeParse() {
		try {
			Instant instant = Instant.parse(getBuildTime());
			return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		}
		catch (Exception e) {
			return null;
		}
	}

}
