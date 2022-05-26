package cloud.xuxiaowei.system.mapper;

import cloud.xuxiaowei.system.entity.OauthClientDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql Mapper 接口
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-23
 */
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {

    /**
     * 根据 客户ID 查询客户
     * <p>
     * 条件无逻辑删除的判断
     *
     * @param clientId 客户ID
     * @return 返回 用户信息
     */
    OauthClientDetails getLogicByClientId(@Param("clientId") String clientId);

}
