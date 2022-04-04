package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.mapper.AuthoritiesMapper;
import cloud.xuxiaowei.system.service.IAuthoritiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表。	原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements IAuthoritiesService {

}
