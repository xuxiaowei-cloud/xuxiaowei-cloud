package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.OauthClientDetails;
import cloud.xuxiaowei.system.mapper.OauthClientDetailsMapper;
import cloud.xuxiaowei.system.service.IOauthClientDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-02
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

    /**
     * 根据 客户端ID 查询 客户端的详细信息
     *
     * @param clientId 客户端ID
     * @return 返回 客户端的详细信息
     */
    @Override
    public OauthClientDetails getByClientId(String clientId) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientId);
        return getOne(queryWrapper);
    }

}
