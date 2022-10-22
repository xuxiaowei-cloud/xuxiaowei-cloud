package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 微博网站用户
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-22
 */
@Data
@TableName("wei_bo_website_users")
public class WeiBoWebsiteUsers implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 微博网站用户主键，自增
	 */
	@TableId(value = "wei_bo_website_users_id", type = IdType.AUTO)
	private Long weiBoWebsiteUsersId;

	/**
	 * 绑定的用户主键，唯一键：uk__wei_bo_website_users__users_id
	 */
	private Long usersId;

	/**
	 * 微博客户ID，唯一键：uk__wei_bo_website_users__appid__id
	 */
	private String appid;

	/**
	 * 用户UID，不为空，唯一键：uk__wei_bo_website_users__appid__id
	 */
	private Long id;

	/**
	 * 字符串型的用户UID
	 */
	private String idstr;

	/**
	 * 用户昵称
	 */
	private String screenName;

	/**
	 * 友好显示名称
	 */
	@TableField("`name`")
	private String name;

	/**
	 * 用户所在省级ID
	 */
	private Integer province;

	/**
	 * 用户所在城市ID
	 */
	private Integer city;

	/**
	 * 用户所在地
	 */
	private String location;

	/**
	 * 用户个人描述
	 */
	@TableField("`description`")
	private String description;

	/**
	 * 用户博客地址
	 */
	private String url;

	/**
	 * 用户头像地址（中图），50×50像素
	 */
	private String profileImageUrl;

	/**
	 * 用户的微博统一URL地址
	 */
	private String profileUrl;

	/**
	 * 用户的个性化域名
	 */
	private String domain;

	/**
	 * 用户的微号
	 */
	private String weihao;

	/**
	 * 性别，m：男、f：女、n：未知
	 */
	private String gender;

	/**
	 * 粉丝数
	 */
	private Integer followersCount;

	/**
	 * 关注数
	 */
	private Integer friendsCount;

	/**
	 * 微博数
	 */
	private Integer statusesCount;

	/**
	 * 收藏数
	 */
	private Integer favouritesCount;

	/**
	 * 用户创建（注册）时间
	 */
	private LocalDateTime createdAt;

	/**
	 * 暂未支持
	 */
	@TableField("`following`")
	private Boolean following;

	/**
	 * 是否允许所有人给我发私信，true：是，false：否
	 */
	private Boolean allowAllActMsg;

	/**
	 * 是否允许标识用户的地理位置，true：是，false：否
	 */
	private Boolean geoEnabled;

	/**
	 * 是否是微博认证用户，即加V用户，true：是，false：否
	 */
	private Boolean verified;

	/**
	 * 暂未支持
	 */
	private Integer verifiedType;

	/**
	 * 用户备注信息，只有在查询用户关系时才返回此字段
	 */
	private String remark;

	/**
	 * 是否允许所有人对我的微博进行评论，true：是，false：否
	 */
	private Integer allowAllComment;

	/**
	 * 用户头像地址（大图），180×180像素
	 */
	private String avatarLarge;

	/**
	 * 用户头像地址（高清），高清头像原图
	 */
	private String avatarHd;

	/**
	 * 认证原因
	 */
	private String verifiedReason;

	/**
	 * 该用户是否关注当前登录用户，true：是，false：否
	 */
	private Boolean followMe;

	/**
	 * 用户的在线状态，0：不在线、1：在线
	 */
	private Integer onlineStatus;

	/**
	 * 用户的互粉数
	 */
	private Integer biFollowersCount;

	/**
	 * 用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
	 */
	private String lang;

	/**
	 * 绑定时间
	 */
	private LocalDateTime bindingDate;

	/**
	 * 授权凭证
	 */
	private String accessToken;

	/**
	 * 过期时间
	 */
	private LocalDateTime expires;

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
