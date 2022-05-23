package cloud.xuxiaowei.system.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * 权限表保存参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class AuthoritiesSaveBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名 不能为空")
    private String username;

    /**
     * 权限
     */
    @Size(min = 1, message = "权限 不能为空")
    @NotNull(message = "权限 不能为空")
    private Set<String> authorityList;

}
