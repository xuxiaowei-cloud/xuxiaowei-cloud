package cloud.xuxiaowei.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 短信表
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-09-16
 */
@Getter
@Setter
public class Sms implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 短信主键
	 */
	@TableId(value = "sms_id", type = IdType.AUTO)
	private Long smsId;

	/**
	 * 短信平台，不为空，字典：dict_data.dict_code = 'sms_platform' AND dict_data.dict_data_code = sms.sms_platform
	 */
	private String smsPlatform;

	/**
	 * AccessKey ID
	 */
	private String accessKeyId;

	/**
	 * 阿里云平台的值
	 */
	private String outId;

	/**
	 * 阿里云平台的值
	 */
	private String ownerId;

	/**
	 * 手机号码
	 */
	private String phoneNumbers;

	/**
	 * 阿里云平台的值
	 */
	private String resourceOwnerAccount;

	/**
	 * 阿里云平台的值
	 */
	private String resourceOwnerId;

	/**
	 * 签名名称
	 */
	private String signName;

	/**
	 * 阿里云平台的值
	 */
	private String smsUpExtendCode;

	/**
	 * 模板CODE
	 */
	private String templateCode;

	/**
	 * 模板参数
	 */
	private String templateParam;

	/**
	 * 阿里云平台的值，响应头
	 */
	private String headers;

	/**
	 * 阿里云平台的值，响应代码
	 */
	private Integer statusCode;

	/**
	 * 阿里云平台的值
	 */
	private String bizId;

	/**
	 * 阿里云平台的值
	 */
	@TableField("`code`")
	private String code;

	/**
	 * 阿里云平台的值
	 */
	private String message;

	/**
	 * 阿里云平台的值
	 */
	private String requestId;

	/**
	 * 异常
	 */
	private String exception;

	/**
	 * 异常中的数据
	 */
	private String exceptionData;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建人，不为空
	 */
	@TableField(fill = FieldFill.INSERT)
	private String createUsersId;

	/**
	 * 创建时间，不为空，数据库自动生成
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createDate;

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
