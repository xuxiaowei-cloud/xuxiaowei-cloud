package cloud.xuxiaowei.oauth2.service.impl;

import cloud.xuxiaowei.oauth2.bo.AuditAccessTokenPageBo;
import cloud.xuxiaowei.oauth2.entity.OauthAccessToken;
import cloud.xuxiaowei.oauth2.mapper.OauthAccessTokenMapper;
import cloud.xuxiaowei.oauth2.service.IOauthAccessTokenService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@Service
public class OauthAccessTokenServiceImpl extends ServiceImpl<OauthAccessTokenMapper, OauthAccessToken> implements IOauthAccessTokenService {

    /**
     * 分页查询授权Token
     *
     * @param auditAccessTokenPageBo 审计授权Token分页参数
     * @return 返回 分页查询结果
     */
    @Override
    @DS("log")
    public IPage<OauthAccessToken> pageByAuditAccessToken(AuditAccessTokenPageBo auditAccessTokenPageBo) {
        QueryWrapper<OauthAccessToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("oauth_access_token_id");
        Long current = auditAccessTokenPageBo.getCurrent();
        Long size = auditAccessTokenPageBo.getSize();

        IPage<OauthAccessToken> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);
        return page(page, queryWrapper);
    }

}
