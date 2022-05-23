package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.bo.ManageUsersPageBo;
import cloud.xuxiaowei.system.bo.UsersSaveBo;
import cloud.xuxiaowei.system.bo.UsersUpdateBo;
import cloud.xuxiaowei.system.entity.Authorities;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.mapper.UsersMapper;
import cloud.xuxiaowei.system.service.IAuthorityService;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.vo.AuthorityVo;
import cloud.xuxiaowei.system.vo.UsersVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表。	原表结构：spring-security-core-*.*.*.jar!/org/springframework/security/core/userdetails/jdbc/users.ddl	原表结构：https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-04-04
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    private IAuthorityService authorityService;

    @Autowired
    public void setAuthorityService(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * 根据 用户名 查询用户信息及权限
     * <p>
     * 待加入Redis注解进行数据缓存
     * <p>
     * 与 {@link IUsersService#getUsersVoByUsername(String)} 可以考虑合并成一个接口
     *
     * @param username 用户名
     * @return 返回 用户信息及权限
     */
    @Override
    public Users getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

    /**
     * 根据 用户名 查询用户信息
     * <p>
     * 条件无逻辑删除的判断
     *
     * @param username 用户名
     * @return 返回 用户信息
     */
    @Override
    public Users getLogicByUsername(String username) {
        return baseMapper.getLogicByUsername(username);
    }

    /**
     * 根据 昵称 查询用户信息
     *
     * @param nickname 昵称
     * @return 返回 用户信息
     */
    @Override
    public Users getByNickname(String nickname) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname", nickname);
        return getOne(queryWrapper);
    }

    /**
     * 根据 昵称 查询用户信息
     * <p>
     * 条件无逻辑删除的判断
     *
     * @param nickname 昵称
     * @return 返回 用户信息
     */
    @Override
    public Users getLogicByNickname(String nickname) {
        return baseMapper.getLogicByNickname(nickname);
    }

    /**
     * 根据 用户名 查询用户信息及权限
     *
     * @param username 用户名
     * @return 返回 用户信息及权限
     */
    @Override
    public UsersVo getUsersVoByUsername(String username) {
        Users users = baseMapper.getByUsername(username);
        return usersToUsersVo(users);
    }

    /**
     * 分页查询用户
     *
     * @param manageUsersPageBo 管理用户分页参数
     * @return 返回 分页查询结果
     */
    @Override
    public IPage<UsersVo> pageByManageUsers(ManageUsersPageBo manageUsersPageBo) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        Long current = manageUsersPageBo.getCurrent();
        Long size = manageUsersPageBo.getSize();

        Long usersId = manageUsersPageBo.getUsersId();
        String username = manageUsersPageBo.getUsername();
        String nickname = manageUsersPageBo.getNickname();

        if (usersId != null) {
            queryWrapper.eq("users_id", usersId);
        }
        if (StringUtils.hasText(username)) {
            queryWrapper.eq("username", username);
        }
        if (StringUtils.hasText(nickname)) {
            queryWrapper.eq("nickname", nickname);
        }

        IPage<Users> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);
        page(page, queryWrapper);

        Page<UsersVo> usersVoPage = new Page<>();
        BeanUtils.copyProperties(page, usersVoPage);

        List<UsersVo> usersVoList = new ArrayList<>();
        usersVoPage.setRecords(usersVoList);

        List<Users> records = page.getRecords();
        for (Users users : records) {
            UsersVo usersVo = new UsersVo();
            BeanUtils.copyProperties(users, usersVo);

            Set<AuthorityVo> authorityList = authorityService.listByUsername(usersVo.getUsername());
            usersVo.setAuthorityList(authorityList);

            usersVoList.add(usersVo);
        }

        return usersVoPage;
    }

    /**
     * 根据 用户主键 查询
     *
     * @param usersId 用户主键
     * @return 返回 查询结果
     */
    @Override
    public UsersVo getUsersVoById(Long usersId) {
        Users users = baseMapper.getByUsersId(usersId);
        return usersToUsersVo(users);
    }

    private UsersVo usersToUsersVo(Users users) {
        if (users == null) {
            return null;
        }
        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(users, usersVo);

        Set<AuthorityVo> authorityVoSet = new HashSet<>();
        usersVo.setAuthorityList(authorityVoSet);
        for (Authorities auth : users.getAuthoritiesList()) {
            AuthorityVo authorityVo = new AuthorityVo();
            authorityVo.setAuthority(auth.getAuthority());
            authorityVo.setExplain(auth.getExplain());
            authorityVoSet.add(authorityVo);
        }
        return usersVo;
    }

    /**
     * 保存用户
     *
     * @param usersSaveBo 用户
     * @return 返回 保存结果
     */
    @Override
    public boolean saveUsersSaveBo(UsersSaveBo usersSaveBo) {
        Users users = new Users();
        BeanUtils.copyProperties(usersSaveBo, users);

        // 用户密码加密
        encode(users);

        return save(users);
    }

    /**
     * 更新用户
     *
     * @param usersUpdateBo 用户
     * @return 返回 更新结果
     */
    @Override
    public boolean updateByUsersUpdateBo(UsersUpdateBo usersUpdateBo) {
        Users users = new Users();
        BeanUtils.copyProperties(usersUpdateBo, users);

        // 用户密码加密
        encode(users);

        return updateById(users);
    }

    /**
     * 用户密码加密
     *
     * @param users 用户
     */
    private void encode(Users users) {
        // 密码加密后储存
        String password = users.getPassword();
        if (StringUtils.hasText(password)) {
            PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String encode = delegatingPasswordEncoder.encode(password);
            users.setPassword(encode);
        }
    }

}
