package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 微信开放平台-网站用户
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-01
 */
@Data
@TableName("wx_open_website_users")
public class WxOpenWebsiteUsers implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 微信开放平台-网站用户主键，自增
	 */
	@TableId(value = "wx_open_website_users_id", type = IdType.AUTO)
	private Long wxOpenWebsiteUsersId;

	/**
	 * 微信开放平台用户appid
	 */
	private String appid;

	/**
	 * 用户唯一标识
	 */
	private String openid;

	/**
	 * 多账户唯一标识
	 */
	private String unionid;

	/**
	 * 绑定的用户主键
	 */
	private Long usersId;

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
