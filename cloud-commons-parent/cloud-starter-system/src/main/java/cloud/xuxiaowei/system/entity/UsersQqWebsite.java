package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * QQ网站应用表
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-10-13
 */
@Data
@TableName("users_qq_website")
public class UsersQqWebsite implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * QQ网站应用，主键，自增
	 */
	@TableId(value = "users_qq_website_id", type = IdType.AUTO)
	private Long usersQqWebsiteId;

	/**
	 * 绑定的用户主键，唯一键：
	 */
	private Long usersId;

	private String appid;

	private String openid;

	private String unionid;

	private String nickname;

	private String gender;

	private String genderType;

	@TableField("binding_date")
	private LocalDateTime bindingDate;

	private String accessToken;

	private Integer expiresIn;

	private String refreshToken;

	private String isLost;

	private String province;

	private String city;

	@TableField("`year`")
	private String year;

	@TableField("`level`")
	private String level;

	private String constellation;

	private String isYellowVip;

	private String yellowVipLevel;

	private String isYellowYearVip;

	private String vip;

	private String figureurl;

	@TableField("figureurl_1")
	private String figureurl1;

	@TableField("figureurl_2")
	private String figureurl2;

	private String figureurlQq;

	@TableField("figureurl_qq_1")
	private String figureurlQq1;

	@TableField("figureurl_qq_2")
	private String figureurlQq2;

	private String figureurlType;

	/**
	 * 企业备注名
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
