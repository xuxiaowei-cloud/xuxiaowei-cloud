package cloud.xuxiaowei.oauth2.service.impl;

import cloud.xuxiaowei.oauth2.bo.AuditCodePageBo;
import cloud.xuxiaowei.oauth2.entity.OauthCode;
import cloud.xuxiaowei.oauth2.mapper.OauthCodeMapper;
import cloud.xuxiaowei.oauth2.service.IOauthCodeService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-10
 */
@Service
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeMapper, OauthCode> implements IOauthCodeService {

    /**
     * 分页查询授权码
     *
     * @param auditCodePageBo 审计授权码分页参数
     * @return 返回 分页查询结果
     */
    @Override
    @DS("xuxiaowei_cloud_log")
    public IPage<OauthCode> pageByAuditCode(AuditCodePageBo auditCodePageBo) {
        QueryWrapper<OauthCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("code_id");
        Long current = auditCodePageBo.getCurrent();
        Long size = auditCodePageBo.getSize();
        String codeId = auditCodePageBo.getCodeId();
        String code = auditCodePageBo.getCode();
        String username = auditCodePageBo.getUsername();
        String clientId = auditCodePageBo.getClientId();
        String remoteAddress = auditCodePageBo.getRemoteAddress();

        if (StringUtils.hasText(codeId)) {
            queryWrapper.eq("code_id", codeId);
        }
        if (StringUtils.hasText(code)) {
            queryWrapper.eq("code", code);
        }
        if (StringUtils.hasText(username)) {
            queryWrapper.eq("username", username);
        }
        if (StringUtils.hasText(clientId)) {
            queryWrapper.eq("client_id", clientId);
        }
        if (StringUtils.hasText(remoteAddress)) {
            queryWrapper.eq("remote_address", remoteAddress);
        }

        IPage<OauthCode> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);
        return page(page, queryWrapper);
    }

}
