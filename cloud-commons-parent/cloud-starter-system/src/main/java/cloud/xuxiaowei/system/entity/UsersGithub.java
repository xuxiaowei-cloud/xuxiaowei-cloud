package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * Github用户表
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-30
 */
@Data
@TableName("users_github")
public class UsersGithub implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Github用户表主键，自增
	 */
	@TableId(value = "users_github_id", type = IdType.AUTO)
	private Long usersGithubId;

	private Long usersId;

	private String appid;

	private String login;

	private Integer id;

	private String nodeId;

	private String avatarUrl;

	private Integer gravatarId;

	private String url;

	private String htmlUrl;

	private String followersUrl;

	private String followingUrl;

	private String gistsUrl;

	private String starredUrl;

	private String subscriptionsUrl;

	private String organizationsUrl;

	private String reposUrl;

	private String eventsUrl;

	private String receivedEventsUrl;

	@TableField("`type`")
	private String type;

	private String siteAdmin;

	@TableField("`name`")
	private String name;

	private String company;

	private String blog;

	private String location;

	private String email;

	private String hireable;

	private String bio;

	private String twitterUsername;

	private String publicRepos;

	private String publicGists;

	private String followers;

	@TableField("`following`")
	private String following;

	private String createdAt;

	private String updatedAt;

	/**
	 * 绑定时间
	 */
	private LocalDateTime bindingDate;

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
