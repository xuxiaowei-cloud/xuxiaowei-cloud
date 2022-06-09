package cloud.xuxiaowei.passport.service.impl;

import cloud.xuxiaowei.passport.entity.UsersLogin;
import cloud.xuxiaowei.passport.mapper.UsersLoginMapper;
import cloud.xuxiaowei.passport.service.IUsersLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-06-09
 */
@Service
public class UsersLoginServiceImpl extends ServiceImpl<UsersLoginMapper, UsersLogin> implements IUsersLoginService {

}
