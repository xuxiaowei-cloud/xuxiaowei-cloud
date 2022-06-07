package cloud.xuxiaowei.oauth2.service.impl;

import cloud.xuxiaowei.oauth2.service.IOauthAccessTokenService;
import cloud.xuxiaowei.oauth2.service.IOauthRefreshTokenService;
import cloud.xuxiaowei.oauth2.service.IOauthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Token 服务实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class OauthTokenServiceImpl implements IOauthTokenService {

    private IOauthAccessTokenService oauthAccessTokenService;

    private IOauthRefreshTokenService oauthRefreshTokenService;

    @Autowired
    public void setOauthAccessTokenService(IOauthAccessTokenService oauthAccessTokenService) {
        this.oauthAccessTokenService = oauthAccessTokenService;
    }

    @Autowired
    public void setOauthRefreshTokenService(IOauthRefreshTokenService oauthRefreshTokenService) {
        this.oauthRefreshTokenService = oauthRefreshTokenService;
    }

    /**
     * 根据 用户名 删除 Token
     *
     * @param usernames 用户名
     * @return 返回 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByUsernames(List<String> usernames) {
        boolean access = oauthAccessTokenService.removeByUsernames(usernames);
        boolean refresh = oauthRefreshTokenService.removeByUsernames(usernames);
        return access || refresh;
    }

    /**
     * 根据 客户ID 删除 Token
     *
     * @param clientIds 客户ID
     * @return 返回 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByClientIds(List<String> clientIds) {
        boolean access = oauthAccessTokenService.removeByClientIds(clientIds);
        boolean refresh = oauthRefreshTokenService.removeByClientIds(clientIds);
        return access || refresh;
    }

}
