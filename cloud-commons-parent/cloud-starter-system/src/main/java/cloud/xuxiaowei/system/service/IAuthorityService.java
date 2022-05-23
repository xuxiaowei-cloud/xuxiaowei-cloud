package cloud.xuxiaowei.system.service;

import cloud.xuxiaowei.system.bo.AuthorityBo;
import cloud.xuxiaowei.system.bo.AuthorityPageBo;
import cloud.xuxiaowei.system.entity.Authority;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限与权限说明表 服务类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-17
 */
public interface IAuthorityService extends IService<Authority> {

    /**
     * 查询 权限与权限说明表
     *
     * @param authorityBo 权限与权限说明参数
     * @return 返回 查询结果
     */
    List<Authority> listByAuthorityBo(AuthorityBo authorityBo);

    /**
     * 分页查询 权限与权限说明表
     *
     * @param authorityPageBo 权限与权限说明分页参数
     * @return 返回 查询结果
     */
    IPage<Authority> pageByAuthorityPageBo(AuthorityPageBo authorityPageBo);

}
