package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 码云Gitee用户表
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-10
 */
@Data
@TableName("gitee_users")
public class GiteeUsers implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 码云Gitee用户表主键，自增
	 */
	@TableId(value = "gitee_users_id", type = IdType.AUTO)
	private Long giteeUsersId;

	/**
	 * 绑定的用户主键，唯一键：uk__gitee_users__appid__id__users_id
	 */
	private Long usersId;

	/**
	 * AppID(码云Gitee client_id)，唯一键：uk__gitee_users__appid__id
	 */
	private String appid;

	/**
	 * 码云Gitee唯一标识，不为空，唯一键：uk__gitee_users__appid__id
	 */
	private Integer id;

	/**
	 * 码云Gitee登录用户名，不为空
	 */
	private String login;

	/**
	 * 码云Gitee用户名，不为空
	 */
	@TableField("`name`")
	private String name;

	/**
	 * 头像
	 */
	private String avatarUrl;

	/**
	 * 公开资料URL
	 */
	private String url;

	/**
	 * 空间URL
	 */
	private String htmlUrl;

	/**
	 * 企业备注名
	 */
	private String remark;

	/**
	 * 粉丝URL
	 */
	private String followersUrl;

	private String followingUrl;

	private String gistsUrl;

	/**
	 * star项目URL
	 */
	private String starredUrl;

	/**
	 * 订阅项目URL
	 */
	private String subscriptionsUrl;

	/**
	 * 组织URL
	 */
	private String organizationsUrl;

	/**
	 * 仓库URL
	 */
	private String reposUrl;

	private String eventsUrl;

	/**
	 * 接收事件
	 */
	private String receivedEventsUrl;

	/**
	 * 类型
	 */
	@TableField("`type`")
	private String type;

	/**
	 * 博客地址
	 */
	private String blog;

	/**
	 * 微博地址
	 */
	private String weibo;

	/**
	 * 自我介绍
	 */
	private String bio;

	/**
	 * 公共仓库数
	 */
	private Integer publicRepos;

	private Integer publicGists;

	/**
	 * 粉丝数
	 */
	private Integer followers;

	/**
	 * 关注的人
	 */
	@TableField("`following`")
	private Integer following;

	/**
	 * star数
	 */
	private Integer stared;

	/**
	 * 关注的仓库
	 */
	private Integer watched;

	/**
	 * 创建时间
	 */
	private LocalDateTime createdAt;

	/**
	 * 更新时间
	 */
	private LocalDateTime updatedAt;

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
	 * 授权范围
	 */
	private String scope;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 职务
	 */
	private String profession;

	/**
	 * 微信
	 */
	private String wechat;

	/**
	 * QQ
	 */
	private String qq;

	/**
	 * 领英账户
	 */
	private String linkedin;

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
