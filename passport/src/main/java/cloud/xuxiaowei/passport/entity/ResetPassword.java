package cloud.xuxiaowei.passport.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 重置密码表
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-09-21
 */
@Data
@TableName("reset_password")
public class ResetPassword implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 重置密码表主键，自增
	 */
	@TableId(value = "reset_password_id", type = IdType.AUTO)
	private Long resetPasswordId;

	/**
	 * 用户ID，不为空
	 */
	private Long usersId;

	/**
	 * 修改之前的密码
	 */
	private String beforePassword;

	/**
	 * 重置密码类型，不为空，取表：dict_data.dict_code = 'reset_password_type'
	 */
	private String resetPasswordType;

	/**
	 * 请求头，不为空，json
	 */
	private String headersMap;

	/**
	 * headers中authorization
	 */
	private String authorization;

	/**
	 * authorization解密
	 */
	private String payload;

	/**
	 * 标识
	 */
	private String userAgent;

	/**
	 * 请求ID，不为空
	 */
	private String requestId;

	/**
	 * Session ID
	 */
	private String sessionId;

	/**
	 * browscap 是否处理
	 */
	private Boolean browscap;

	/**
	 * 浏览器值（例如 Chrome）
	 */
	private String browser;

	/**
	 * 浏览器类型（例如 Browser 或 Application）
	 */
	private String browserType;

	/**
	 * 浏览器的主要版本（例如，Chrome 为 55）
	 */
	private String browserMajorVersion;

	/**
	 * 平台名称（例如 Android、iOS、Win7、Win10）
	 */
	private String platform;

	/**
	 * 平台版本（例如 4.2、10，取决于平台是什么）
	 */
	private String platformVersion;

	/**
	 * 设备类型（例如手机、台式机、平板电脑、控制台、电视设备）
	 */
	private String deviceType;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间，不为空，数据库自动生成
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createDate;

	/**
	 * 创建人，不为空
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createUsersId;

	/**
	 * 创建者IP，不为空
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createIp;

	/**
	 * 更新人，未更新时为空
	 */
	@TableField(fill = FieldFill.UPDATE)
	private String updateUsersId;

	/**
	 * 更新时间，未更新时为空
	 */
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateDate;

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

}
