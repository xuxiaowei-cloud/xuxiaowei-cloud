package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业微信-网站用户
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-29
 */
@Data
@TableName("users_wx_work_website")
public class UsersWxWorkWebsite implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 企业微信-网站用户，自增
	 */
	@TableId(value = "users_wx_work_website_id", type = IdType.AUTO)
	private Long usersWxWorkWebsiteId;

	private Long usersId;

	private String appid;

	private String agentid;

	private String openid;

	private String unionid;

	private String gender;

	private String externalPosition;

	private String thumbAvatar;

	private String alias;

	private String qrCode;

	private String openUserid;

	private String bizMail;

	private String email;

	private String address;

	private String mobile;

	private String telephone;

	private String avatar;

	private String mainDepartment;

	@TableField("`name`")
	private String name;

	private String position;

	@TableField("`status`")
	private String status;

	private String isleader;

	@TableField("`enable`")
	private String enable;

	private String hideMobile;

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
	 * 授权范围
	 */
	private String scope;

	/**
	 * 备注
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
