package cloud.xuxiaowei.passport.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-04
 */
@Data
public class TenantVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户ID，自增，主键
	 */
	private Long tenantId;

	/**
	 * 租户名称，不为空
	 */
	private String tenantName;

	/**
	 * 用户ID，租户超级管理员，不为空
	 */
	private Long usersId;

	/**
	 * 是否启用，不能为空
	 */
	private Boolean enabled;

	/**
	 * 租户域名，用于后期增加租户成员使用该域名邮箱直接登录
	 */
	private String domainName;

	/**
	 * 用户备注信息，只有在查询用户关系时才返回此字段
	 */
	private String remark;

	/**
	 * 创建时间，不为空
	 */
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime createDate;

	/**
	 * 更新时间，未更新时为空
	 */
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	private LocalDateTime updateDate;

	/**
	 * 创建人，不为空
	 */
	private String createUsersId;

	/**
	 * 更新人，未更新时为空
	 */
	private String updateUsersId;

	/**
	 * 创建者IP，不为空
	 */
	private String createIp;

	/**
	 * 更新者IP，未更新时为空
	 */
	private String updateIp;

}
