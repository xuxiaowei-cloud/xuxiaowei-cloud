package cloud.xuxiaowei.passport.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 授权码、状态码
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class CodeState implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 授权码
     */
    @NotEmpty(message = "授权码 不能为空")
    private String code;

    /**
     * 状态码
     */
    @NotEmpty(message = "状态码 不能为空")
    private String state;

}
