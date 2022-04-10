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
    @DS("xuxiaowei_cloud_log")
    public IPage<OauthRefreshToken> pageByAuditRefreshToken(AuditRefreshTokenPageBo auditRefreshTokenPageBo) {
        QueryWrapper<OauthRefreshToken> queryWrapper = new QueryWrapper<>();
        Long current = auditRefreshTokenPageBo.getCurrent();
        Long size = auditRefreshTokenPageBo.getSize();

        IPage<OauthRefreshToken> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);
        return page(page, queryWrapper);
    }

}
