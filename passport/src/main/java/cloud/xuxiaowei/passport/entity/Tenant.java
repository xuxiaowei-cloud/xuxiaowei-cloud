package cloud.xuxiaowei.passport.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-04
 */
@Data
public class Tenant implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户ID，自增，主键
	 */
	@TableId(value = "tenant_id", type = IdType.AUTO)
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
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createDate;

	/**
	 * 更新时间，未更新时为空
	 */
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateDate;

	/**
	 * 创建人，不为空
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createUsersId;

	/**
	 * 更新人，未更新时为空
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateUsersId;

	/**
	 * 创建者IP，不为空
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createIp;

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

}
