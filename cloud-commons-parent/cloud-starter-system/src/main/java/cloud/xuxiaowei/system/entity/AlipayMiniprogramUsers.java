package cloud.xuxiaowei.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 支付宝小程序用户
 * </p>
 *
 * @author xuxiaowei
 * @since 2023-02-02
 */
@Data
@TableName("alipay_miniprogram_users")
public class AlipayMiniprogramUsers implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 支付宝小程序用户主键ID，自增
	 */
	@TableId(value = "alipay_miniprogram_users_id", type = IdType.AUTO)
	private Long alipayMiniprogramUsersId;

	private String appid;

	/**
	 * 支付宝用户的userId。支付宝用户的唯一标识。以2088开头的16位数字。
	 */
	private String userId;

	/**
	 * union_id是支付宝用户在开放平台的唯一标识符，在配置应用分组后会返回该值。 同一用户的union_id在同一分组内应用保持一致。
	 */
	private String unionId;

	/**
	 * 支付宝用户的openId，支付宝用户唯一标识
	 */
	private String openId;

	/**
	 * 淘宝id
	 */
	private String taobaoId;

	/**
	 * 省份名称
	 */
	private String age;

	/**
	 * 省份名称
	 */
	private String area;

	/**
	 * 用户头像地址。 注意：如果没有数据（用户未设置）时不会返回该信息，请做好容错。
	 */
	private String avatar;

	/**
	 * 经营/业务范围（用户类型是公司类型时才有此字段）。
	 */
	private String businessScope;

	/**
	 * 【证件号码】结合证件类型使用.【注意】只is_certified为T的时候才有意义，否则不保证准确性.
	 */
	private String certNo;

	/**
	 * 【证件类型】0:身份证 1:护照 2:军官证 3:士兵证 4:回乡证 5:临时身份证 6:户口簿 7:警官证 8:台胞证 9:营业执照10:其它证件
	 * 11:港澳居民来往内地通行证 12:台湾居民来往大陆通行证 13:台湾居民居住证 14:港澳居民居住证 15:港澳身份证
	 * 16:外国人永久居住证【注意】只有is_certified为T的时候才有意义，否则不保证准确性.
	 */
	private String certType;

	/**
	 * 市名称
	 */
	private String city;

	/**
	 * 学信网返回的学校名称，有可能为空。
	 */
	private String collegeName;

	/**
	 * 国家码
	 */
	private String countryCode;

	/**
	 * 学信网返回的学历/学位信息，数据质量一般，请谨慎使用，取值包括：博士研究生、硕士研究生、高升本、专科、博士、硕士、本科、夜大电大函大普通班、专科(高职)、第二学士学位。
	 */
	private String degree;

	/**
	 * 展示名称，用于展示和识别用户。返回的值是支付宝账户的可用外标之一，根据展示名称产品规则可能返回手机号/邮箱/支付宝号其中的一个。同一个账户在不同的时期返回的展示名称可能随规则变化而变化。
	 */
	private String displayName;

	/**
	 * 优先获取email登录名，如果不存在，则返回mobile登录名
	 */
	private String email;

	/**
	 * 入学时间，yyyy-mm-dd格式
	 */
	private String enrollmentTime;

	/**
	 * 企业证照地址的详细地址（用户类型是公司用户才有该字段）【注意】只有is_certified为T的时候才有意义，否则不保证准确性.
	 */
	private String entLicenseAddress;

	/**
	 * 企业证照地址所在地区或县（用户类型是公司用户才有该字段）【注意】只有is_certified为T的时候才有意义，否则不保证准确性，目前企业营业执照地址不含县(区)，area为空。
	 */
	private String entLicenseArea;

	/**
	 * 企业证照地址所在市（用户类型是公司用户才有该字段）【注意】只有is_certified为T的时候才有意义，否则不保证准确性.
	 */
	private String entLicenseCity;

	/**
	 * 企业证照地址所在省份（用户类型是公司用户才有该字段）【注意】只有is_certified为T的时候才有意义，否则不保证准确性.
	 */
	private String entLicenseProvince;

	/**
	 * 企业代理人证件有效期（用户类型是公司类型时才有此字段）。
	 */
	private String firmAgentPersonCertExpiryDate;

	/**
	 * 企业代理人证件号码（用户类型是公司类型时才有此字段）
	 */
	private String firmAgentPersonCertNo;

	/**
	 * 企业代理人证件类型, 返回值参考cert_type字段（用户类型是公司类型时才有此字段）
	 */
	private String firmAgentPersonCertType;

	/**
	 * 企业代理人姓名（用户类型是公司类型时才有此字段）。
	 */
	private String firmAgentPersonName;

	/**
	 * 企业法人证件有效期（用户类型是公司类型时才有此字段）。
	 */
	private String firmLegalPersonCertExpiryDate;

	/**
	 * 法人证件号码（用户类型是公司类型时才有此字段）
	 */
	private String firmLegalPersonCertNo;

	/**
	 * 企业法人证件类型, 返回值参考cert_type字段（用户类型是公司类型时才有此字段）
	 */
	private String firmLegalPersonCertType;

	/**
	 * 企业法人名称（用户类型是公司类型时才有此字段）
	 */
	private String firmLegalPersonName;

	/**
	 * 公司类型，包括（用户类型是公司类型时才有此字段）： CO(公司) INST(事业单位), COMM(社会团体), NGO(民办非企业组织),
	 * STATEORGAN(党政国家机关)
	 */
	private String firmType;

	/**
	 * 性别。枚举值如下： F：女性； M：男性。
	 */
	private String gender;

	/**
	 * 预期毕业时间，不保证准确性，yyyy-mm-dd格式。
	 */
	private String graduationTime;

	/**
	 * 身份证地址信息
	 */
	private String identityCardAddress;

	/**
	 * 身份证地区信息
	 */
	private String identityCardArea;

	/**
	 * 身份证市信息
	 */
	private String identityCardCity;

	/**
	 * 身份证省信息
	 */
	private String identityCardProvince;

	/**
	 * 是否是金融机构或特殊单位，"I"表示金融机构, "C"表示无余额账户的单位账户, "V", 表示虚拟主体(类似机构仅内部户无余额户),
	 * "N"表示非金融机构且非无余额账户的单位账户,"?"表示匿名用户
	 */
	private String instOrCorp;

	/**
	 * T/F, 实名认证为T，且证件类型为身份证，则输出是否成年字段，否则不输出
	 */
	private String isAdult;

	/**
	 * 余额账户是否被冻结。 T--被冻结；F--未冻结
	 */
	private String isBalanceFrozen;

	/**
	 * 账户是否被冻结，T/F，T冻结，F未冻结
	 */
	private String isBlocked;

	/**
	 * 是否通过实名认证。T是通过 F是没有实名认证。
	 */
	private String isCertified;

	/**
	 * 是否是学生
	 */
	private String isStudentCertified;

	/**
	 * 营业执照有效期，yyyyMMdd或长期，（用户类型是公司类型时才有此字段）。
	 */
	private String licenseExpiryDate;

	/**
	 * 企业执照号码（用户类型是公司类型时才有此字段）。
	 */
	private String licenseNo;

	/**
	 * 支付宝会员等级
	 */
	private String memberGrade;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 用户昵称。 注意：如果没有数据（用户未设置）时不会返回该信息，请做好容错。
	 */
	private String nickName;

	/**
	 * 组织机构代码（用户类型是公司类型时才有此字段）
	 */
	private String organizationCode;

	/**
	 * 个人用户生日
	 */
	private String personBirthday;

	/**
	 * 生日。不包含具体年份，格式MMdd
	 */
	private String personBirthdayWithoutYear;

	/**
	 * 证件有效期（用户类型是个人的时候才有此字段）。
	 */
	private String personCertExpiryDate;

	/**
	 * 证件起始日期（用户类型是个人的时候才可能有此字段，不保证准确，同时有可能为空）。
	 */
	private String personCertIssueDate;

	/**
	 * 电话号码
	 */
	private String phone;

	/**
	 * 职业
	 */
	private String profession;

	/**
	 * 省份名称
	 */
	private String province;

	/**
	 * 省份名称
	 */
	private String address;

	/**
	 * 若用户是个人用户，则是用户的真实姓名；若是企业用户，则是企业名称。【注意】只有is_certified为T的时候才有意义，否则不保证准确性.
	 */
	private String userName;

	/**
	 * 身份证民族信息
	 */
	private String userNation;

	/**
	 * 用户状态（Q/T/B/W）。 Q代表快速注册用户 T代表正常用户 B代表被冻结账户 W代表已注册，未激活的账户
	 */
	private String userStatus;

	/**
	 * 用户类型（1/2） 1代表公司账户2代表个人账户
	 */
	private String userType;

	/**
	 * 邮政编码。
	 */
	private String zip;

	/**
	 * 访问令牌。通过该令牌调用需要授权类接口
	 */
	private String accessToken;

	/**
	 * 授权token开始时间，作为有效期计算的起点
	 */
	private LocalDateTime authStart;

	/**
	 * 令牌类型，permanent表示返回的access_token和refresh_token永久有效，非永久令牌不返回该字段
	 */
	private String authTokenType;

	/**
	 * 访问令牌的有效时间，单位是秒。
	 */
	private String expiresIn;

	/**
	 * 刷新令牌的有效时间，单位是秒。
	 */
	private String reExpiresIn;

	/**
	 * 刷新令牌。通过该令牌可以刷新access_token
	 */
	private String refreshToken;

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

}
