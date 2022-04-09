package cloud.xuxiaowei.authorizationserver.service.impl;

import cloud.xuxiaowei.authorizationserver.entity.OauthCode;
import cloud.xuxiaowei.authorizationserver.mapper.OauthCodeMapper;
import cloud.xuxiaowei.authorizationserver.service.IOauthCodeService;
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
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeMapper, OauthCode> implements IOauthCodeService {

}
