package cloud.xuxiaowei.system.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 客户 分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class OauthClientDetailsPageBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long current;

    private Long size;

    private Long oauthClientDetailsId;

    private String clientId;

}
