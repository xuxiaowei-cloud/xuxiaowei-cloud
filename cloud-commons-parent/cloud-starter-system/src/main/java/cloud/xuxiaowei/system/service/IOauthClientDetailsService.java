package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.entity.OauthClientDetails;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-02
 */
public interface IOauthClientDetailsService extends IService<OauthClientDetails> {

    /**
     * 根据 客户端ID 查询 客户端的详细信息
     *
     * @param clientId 客户端ID
     * @return 返回 客户端的详细信息
     */
    OauthClientDetails getByClientId(String clientId);

}
