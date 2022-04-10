package cloud.xuxiaowei.oauth2.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 审计授权Token分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class AuditAccessTokenPageBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long current;

    private Long size;

}
