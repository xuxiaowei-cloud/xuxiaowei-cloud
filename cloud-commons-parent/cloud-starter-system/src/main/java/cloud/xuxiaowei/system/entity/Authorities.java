package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

/**
 * <p>
 * 权限表。
 * 原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl
 * 原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * GitCode
 * 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * Gitee
 * 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-07
 */
@Data
public class Authorities implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 权限主键，自增
	 */
	@TableId(value = "authorities_id", type = IdType.AUTO)
	private Long authoritiesId;

	/**
	 * 用户ID
	 */
	private Long usersId;

	/**
	 * 权限
	 */
	private String authority;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建人，不为空
	 */
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	@TableField(fill = FieldFill.INSERT)
	private String createUsersId;

	/**
	 * 创建时间，不为空，数据库自动生成
	 */
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createDate;

	/**
	 * 创建者IP，不为空
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createIp;

	/**
	 * 更新人，未更新时为空
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateUsersId;

	/**
	 * 更新时间，未更新时为空
	 */
	@JsonFormat(pattern = NORM_DATETIME_PATTERN)
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateDate;

	/**
	 * 更新者IP，未更新时为空
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateIp;

	/**
	 * 逻辑删除，0 未删除，1 删除，MySQL 默认值 0，不为 NULL，注解@TableLogic。
	 */
	@TableLogic
	private Boolean deleted;

	@TableField(exist = false)
	private String explain;

}
