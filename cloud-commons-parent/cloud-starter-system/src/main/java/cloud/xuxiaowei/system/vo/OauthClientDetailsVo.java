package cloud.xuxiaowei.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

import static cloud.xuxiaowei.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * 客户 返回值
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class OauthClientDetailsVo {

    private static final long serialVersionUID = 1L;

    private Long oauthClientDetailsId;

    /**
     * 唯一键：uk__oauth_client_details__client_id
     */
    private String clientId;

    private String resourceIds;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime createDate;

    @JsonFormat(pattern = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime updateDate;

}
