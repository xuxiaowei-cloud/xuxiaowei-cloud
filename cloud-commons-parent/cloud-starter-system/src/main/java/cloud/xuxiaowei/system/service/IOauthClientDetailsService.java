package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.bo.OauthClientDetailsPageBo;
import cloud.xuxiaowei.system.entity.OauthClientDetails;
import cloud.xuxiaowei.system.vo.OauthClientDetailsVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-23
 */
public interface IOauthClientDetailsService extends IService<OauthClientDetails> {

    /**
     * 分页查询客户
     *
     * @param oauthClientDetailsPageBo 客户 分页参数
     * @return 返回 分页结果
     */
    IPage<OauthClientDetailsVo> pageByOauthClientDetails(OauthClientDetailsPageBo oauthClientDetailsPageBo);

}
