package cloud.xuxiaowei.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

/**
 * 社交绑定
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class SocialVo {

	/**
	 * 社交名称
	 */
	private String socialName;

	/**
	 * 社交代码
	 */
	private String socialCode;

	/**
	 * 社交平台用户名
	 */
	private String nickname;

	/**
	 * 头像
	 */
	private String headimgurl;

	/**
	 * 绑定时间
	 */
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime bindingDate;

	/**
	 * 是否绑定
	 */
	private Boolean binding;

}
