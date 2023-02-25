package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 钉钉用户表
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-01-31
 */
@Data
@TableName("users_dingtalk")
public class UsersDingtalk implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 钉钉用户主键ID，自增
	 */
	@TableId(value = "users_dingtalk_id", type = IdType.AUTO)
	private Long usersDingtalkId;

	private Long usersId;

	private String appid;

	/**
	 * 所选企业corpId
	 */
	private String corpId;

	private String openId;

	private String unionId;

	/**
	 * 昵称
	 */
	private String nick;

	/**
	 * 头像url
	 */
	private String avatarUrl;

	/**
	 * 个人邮箱
	 */
	private String email;

	/**
	 * 手机号对应的国家号
	 */
	private String stateCode;

	/**
	 * 手机号
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

	/**
	 * 绑定的用户
	 */
	@TableField(exist = false)
	private Users users;

}
