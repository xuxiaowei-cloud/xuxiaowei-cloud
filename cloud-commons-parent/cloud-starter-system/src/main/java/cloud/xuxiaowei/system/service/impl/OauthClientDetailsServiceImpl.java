package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.bo.OauthClientDetailsPageBo;
import cloud.xuxiaowei.system.entity.OauthClientDetails;
import cloud.xuxiaowei.system.mapper.OauthClientDetailsMapper;
import cloud.xuxiaowei.system.service.IOauthClientDetailsService;
import cloud.xuxiaowei.system.vo.OauthClientDetailsVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 原表结构：https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql	GitCode 镜像仓库：https://gitcode.net/mirrors/spring-projects/spring-security-oauth/-/blob/master/spring-security-oauth2/src/test/resources/schema.sql	Gitee 镜像仓库：https://gitee.com/mirrors/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql 服务实现类
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-23
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

    /**
     * 分页查询客户
     *
     * @param oauthClientDetailsPageBo 客户 分页参数
     * @return 返回 分页结果
     */
    @Override
    public IPage<OauthClientDetailsVo> pageByOauthClientDetails(OauthClientDetailsPageBo oauthClientDetailsPageBo) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        Long current = oauthClientDetailsPageBo.getCurrent();
        Long size = oauthClientDetailsPageBo.getSize();

        IPage<OauthClientDetails> page = new Page<>(current == null ? 1 : current, size == null ? 10 : size);
        page(page, queryWrapper);

        Page<OauthClientDetailsVo> oauthClientDetailsVoPage = new Page<>();
        BeanUtils.copyProperties(page, oauthClientDetailsVoPage);

        List<OauthClientDetailsVo> oauthClientDetailsVoList = new ArrayList<>();
        oauthClientDetailsVoPage.setRecords(oauthClientDetailsVoList);

        List<OauthClientDetails> records = page.getRecords();
        for (OauthClientDetails oauthClientDetails : records) {
            OauthClientDetailsVo oauthClientDetailsVo = new OauthClientDetailsVo();
            BeanUtils.copyProperties(oauthClientDetails, oauthClientDetailsVo);

            oauthClientDetailsVoList.add(oauthClientDetailsVo);
        }

        return oauthClientDetailsVoPage;
    }

}
