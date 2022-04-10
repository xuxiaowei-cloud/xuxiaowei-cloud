package cloud.xuxiaowei.oauth2.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 审计授权码分页参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class AuditCodePageBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long current;

    private Long size;

    private String codeId;

    private String code;

    private String username;

    private String clientId;

    private String remoteAddress;

}
