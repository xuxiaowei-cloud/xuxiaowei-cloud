package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.entity.GitlabUsers;
import cloud.xuxiaowei.system.mapper.GitlabUsersMapper;
import cloud.xuxiaowei.system.service.IGitlabUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * GitLab用户 服务实现类
 * </p>
 *
 * @author 徐晓伟
 * @since 2022-12-01
 */
@Service
public class GitlabUsersServiceImpl extends ServiceImpl<GitlabUsersMapper, GitlabUsers> implements IGitlabUsersService {

}
