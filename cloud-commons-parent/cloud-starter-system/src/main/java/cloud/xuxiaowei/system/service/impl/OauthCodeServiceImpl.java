package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.OauthCode;
import cloud.xuxiaowei.system.mapper.OauthCodeMapper;
import cloud.xuxiaowei.system.service.IOauthCodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-05
 */
@Service
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeMapper, OauthCode> implements IOauthCodeService {

    /**
     * 根据 授权码 查询授权信息
     *
     * @param code 授权码
     * @return 返回 授权信息
     */
    @Override
    public OauthCode getByCode(String code) {
        QueryWrapper<OauthCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        return getOne(queryWrapper);
    }

}
