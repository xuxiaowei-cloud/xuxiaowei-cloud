package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 飞书用户表
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-04-02
 */
@Getter
@Setter
@TableName("users_feishu_webpage")
public class UsersFeishuWebpage implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 飞书用户主键ID，自增
	 */
	@TableId(value = "users_feishu_webpage_id", type = IdType.AUTO)
	private Long usersFeishuWebpageId;

	/**
	 * 系统用户ID
	 */
	private Long usersId;

	/**
	 * 飞书ID
	 */
	private String appid;

	/**
	 * 用户在应用内的唯一标识，等同于open_id
	 */
	private String sub;

	/**
	 * 用户姓名
	 */
	@TableField("`name`")
	private String name;

	/**
	 * 用户头像，等同于avatar_url
	 */
	private String picture;

	/**
	 * 用户在应用内的唯一标识, 等同于sub
	 */
	private String openId;

	/**
	 * 用户统一ID，在同一租户开发的所有应用内的唯一标识
	 */
	private String unionId;

	/**
	 * 用户英文名称
	 */
	private String enName;

	/**
	 * 当前企业标识
	 */
	private String tenantKey;

	/**
	 * 用户头像，等同于picture
	 */
	private String avatarUrl;

	/**
	 * 用户头像 72x72
	 */
	private String avatarThumb;

	/**
	 * 用户头像 240x240
	 */
	private String avatarMiddle;

	/**
	 * 用户头像 640x640
	 */
	private String avatarBig;

	/**
	 * 用户 user id，申请了员工信息获取权限(获取用户 user ID)的应用会返回该字段【仅自建应用】
	 */
	private String userId;

	/**
	 * 用户工号，申请了员工信息获取权限(获取用户 user ID)的应用会返回该字段【仅自建应用】
	 */
	private String employeeNo;

	/**
	 * 用户邮箱，申请了邮箱获取权限(获取用户邮箱信息)的应用会返回该字段
	 */
	private String email;

	/**
	 * 用户手机号，申请了手机号获取权限(获取用户手机号)的应用会返回该字段
	 */
	private String mobile;

	private String accessToken;

	private String refreshToken;

	/**
	 * 超时时间
	 */
	private Long expireIn;

	private LocalDateTime expires;

	/**
	 * 绑定时间
	 */
	private LocalDateTime bindingDate;

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

	@TableField(exist = false)
	private Users users;

}
