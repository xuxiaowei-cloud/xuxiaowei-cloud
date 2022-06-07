package cloud.xuxiaowei.oauth2.service;

import java.util.List;

/**
 * Token 服务类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface IOauthTokenService {

    /**
     * 根据 用户名 删除 Token
     *
     * @param usernames 用户名
     * @return 返回 删除结果
     */
    boolean removeByUsernames(List<String> usernames);

    /**
     * 根据 客户ID 删除 Token
     *
     * @param clientIds 客户ID
     * @return 返回 删除结果
     */
    boolean removeByClientIds(List<String> clientIds);

}
