package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * GitLab用户
 * </p>
 *
 * @author 徐晓伟
 * @since 2022-12-01
 */
@Data
@TableName("users_gitlab")
public class UsersGitlab implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * GitLab用户主键，自增
	 */
	@TableId(value = "users_gitlab_id", type = IdType.AUTO)
	private Long usersGitlabId;

	/**
	 * 域名，不为空
	 */
	private String domain;

	/**
	 * 绑定的用户主键，唯一键：
	 */
	private Long usersId;

	/**
	 * GitLab客户ID
	 */
	private String appid;

	/**
	 * GitLab网站唯一标识
	 */
	private Long id;

	/**
	 * GitLab网站登录用户名，可能会被修改
	 */
	private String username;

	/**
	 * GitLab网站用户名称
	 */
	@TableField("`name`")
	private String name;

	/**
	 * 头像
	 */
	private String avatarUrl;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 授权凭证
	 */
	private String accessToken;

	/**
	 * 刷新凭证
	 */
	private String refreshToken;

	/**
	 * 过期时间
	 */
	private LocalDateTime expires;

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

	/**
	 * 绑定的用户
	 */
	@TableField(exist = false)
	private Users users;

}
