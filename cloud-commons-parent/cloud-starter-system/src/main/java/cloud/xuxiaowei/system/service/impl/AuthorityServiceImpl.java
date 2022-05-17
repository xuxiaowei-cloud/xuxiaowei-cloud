package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.Authority;
import cloud.xuxiaowei.system.mapper.AuthorityMapper;
import cloud.xuxiaowei.system.service.IAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限与权限说明表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-17
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

}
