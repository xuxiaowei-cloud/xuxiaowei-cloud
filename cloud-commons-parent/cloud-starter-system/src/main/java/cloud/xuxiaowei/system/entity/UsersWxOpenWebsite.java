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
@TableName("users_wx_open_website")
public class UsersWxOpenWebsite implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 微信开放平台-网站用户主键，自增
	 */
	@TableId(value = "users_wx_open_website_id", type = IdType.AUTO)
	private Long usersWxOpenWebsiteId;

	/**
	 * 绑定的用户主键，唯一键：uk__users_wx_open_website__appid__openid__users_id
	 */
	private Long usersId;

	/**
	 * 微信开放平台用户appid，不为空，唯一键：uk__users_wx_open_website__appid__openid
	 */
	private String appid;

	/**
	 * 用户唯一标识，不为空，唯一键：uk__users_wx_open_website__appid__openid
	 */
	private String openid;

	/**
	 * 多账户唯一标识
	 */
	private String unionid;

	/**
	 * 普通用户昵称
	 */
	private String nickname;

	/**
	 * 普通用户性别，1为男性，2为女性
	 */
	private String sex;

	/**
	 * 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为en
	 */
	@TableField("`language`")
	private String language;

	/**
	 * 普通用户个人资料填写的省份
	 */
	private String province;

	/**
	 * 普通用户个人资料填写的城市
	 */
	private String city;

	/**
	 * 国家，如中国为CN
	 */
	private String country;

	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	 */
	private String headimgurl;

	/**
	 * 用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
	 */
	private String privilege;

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
