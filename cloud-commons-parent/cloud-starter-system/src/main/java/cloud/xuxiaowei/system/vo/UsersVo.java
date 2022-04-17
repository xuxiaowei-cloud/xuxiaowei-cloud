package cloud.xuxiaowei.system.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 用户
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class UsersVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键，自增
     */
    private String usersId;

    /**
     * 用户名，不能为空，唯一键：uk__users__username
     */
    private String username;

    /**
     * 昵称，不能为空，唯一键：uk__users__nickname
     */
    private String nickname;

    /**
     * 权限
     */
    private Set<String> authoritiesList;

}
