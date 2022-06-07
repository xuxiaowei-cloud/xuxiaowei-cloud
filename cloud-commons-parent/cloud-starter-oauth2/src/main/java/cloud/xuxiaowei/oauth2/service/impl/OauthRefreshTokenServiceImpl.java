package cloud.xuxiaowei.oauth2.service.impl;

import cloud.xuxiaowei.oauth2.bo.AuditRefreshTokenPageBo;
import cloud.xuxiaowei.oauth2.entity.OauthAccessToken;
import cloud.xuxiaowei.oauth2.entity.OauthRefreshToken;
import cloud.xuxiaowei.oauth2.mapper.OauthRefreshTokenMapper;
import cloud.xuxiaowei.oauth2.service.IOauthRefreshTokenService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@Service
public class OauthRefreshTokenServiceImpl extends ServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken> implements IOauthRefreshTokenService {

    /**
     * 分页查询刷新Token
     *
     * @param auditRefreshTokenPageBo 审计刷新Token分页参数
     * @return 返回 分页查询结果
     */
    @Override
    @DS("log")
    public IPage<OauthRefreshToken> pageByAuditRefreshToken(AuditRefreshTokenPageBo auditRefreshTokenPageBo) {
        QueryWrapper<OauthRefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("oauth_refresh_token_id");
        Long current = auditRefreshTokenPageBo.getCurrent();
        Long size = auditRefreshTokenPageBo.getSize();

        Long oauthRefreshTokenId = auditRefreshTokenPageBo.getOauthRefreshTokenId();
        if (oauthRefreshTokenId != null) {
            queryWrapper.eq("oauth_refresh_token_id", oauthRefreshTokenId);
        }

        String tokenId = auditRefreshTokenPageBo.getTokenId();
        if (StringUtils.hasText(tokenId)) {
            queryWrapper.eq("token_id", tokenId);
        }

        String username = auditRefreshTokenPageBo.getUsername();
        if (StringUtils.hasText(username)) {
            queryWrapper.eq("username", username);
        }

        String clientId = auditRefreshTokenPageBo.getClientId();
        if (StringUtils.hasText(clientId)) {
            queryWrapper.eq("client_id", clientId);
        }

        String remoteAddress = auditRefreshTokenPageBo.getRemoteAddress();
        if (StringUtils.hasText(remoteAddress)) {
            queryWrapper.eq("remote_address", remoteAddress);
        }

        String scope = auditRefreshTokenPageBo.getScope();
        if (StringUtils.hasText(scope)) {
            queryWrapper.eq("scope", scope);
        }

        String redirectUri = auditRefreshTokenPageBo.getRedirectUri();
        if (StringUtils.hasText(redirectUri)) {
            queryWrapper.eq("redirect_uri", redirectUri);
        }

        String refreshToken = auditRefreshTokenPageBo.getRefreshToken();
        if (StringUtils.hasText(refreshToken)) {
            queryWrapper.eq("refresh_token", refreshToken);
        }

        String sessionId = auditRefreshTokenPageBo.getSessionId();
        if (StringUtils.hasText(sessionId)) {
            queryWrapper.eq("session_id", sessionId);
        }

        String state = auditRefreshTokenPageBo.getState();
        if (StringUtils.hasText(state)) {
            queryWrapper.eq("state", state);
        }

        IPage<OauthRefreshToken> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);
        return page(page, queryWrapper);
    }

    /**
     * 根据 用户名 删除 Token
     *
     * @param usernames 用户名
     * @return 返回 删除结果
     */
    @Override
    public boolean removeByUsernames(List<String> usernames) {
        QueryWrapper<OauthRefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("username", usernames);
        return remove(queryWrapper);
    }

    /**
     * 根据 客户ID 删除 Token
     *
     * @param clientIds 客户ID
     * @return 返回 删除结果
     */
    @Override
    public boolean removeByClientIds(List<String> clientIds) {
        QueryWrapper<OauthRefreshToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("client_id", clientIds);
        return remove(queryWrapper);
    }

}
