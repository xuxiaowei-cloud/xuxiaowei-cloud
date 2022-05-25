package cloud.xuxiaowei.system.service.impl;

import cloud.xuxiaowei.system.bo.ClientSecretBo;
import cloud.xuxiaowei.system.bo.OauthClientDetailsPageBo;
import cloud.xuxiaowei.system.bo.OauthClientDetailsSaveBo;
import cloud.xuxiaowei.system.bo.OauthClientDetailsUpdateBo;
import cloud.xuxiaowei.system.entity.OauthClientDetails;
import cloud.xuxiaowei.system.mapper.OauthClientDetailsMapper;
import cloud.xuxiaowei.system.service.IOauthClientDetailsService;
import cloud.xuxiaowei.system.service.SessionService;
import cloud.xuxiaowei.system.vo.OauthClientDetailsVo;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.exception.CloudRuntimeException;
import cloud.xuxiaowei.validation.utils.ValidationUtils;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
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

    private SessionService sessionService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

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

        Long oauthClientDetailsId = oauthClientDetailsPageBo.getOauthClientDetailsId();
        String clientId = oauthClientDetailsPageBo.getClientId();

        if (oauthClientDetailsId != null) {
            queryWrapper.eq("oauth_client_details_id", oauthClientDetailsId);
        }
        if (StringUtils.hasText(clientId)) {
            queryWrapper.eq("client_id", clientId);
        }

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

    /**
     * 根据 客户主键 查询
     *
     * @param oauthClientDetailsId 客户主键
     * @return 返回 查询结果
     */
    @Override
    public OauthClientDetailsVo getOauthClientDetailsVoById(Long oauthClientDetailsId) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("oauth_client_details_id", oauthClientDetailsId);
        OauthClientDetails oauthClientDetails = getOne(queryWrapper);
        if (oauthClientDetails == null) {
            return null;
        }
        OauthClientDetailsVo oauthClientDetailsVo = new OauthClientDetailsVo();
        BeanUtils.copyProperties(oauthClientDetails, oauthClientDetailsVo);
        return oauthClientDetailsVo;
    }

    /**
     * 保存客户
     *
     * @param oauthClientDetailsSaveBo 客户
     * @return 返回 保存结果
     */
    @Override
    public boolean saveOauthClientDetailsSaveBo(OauthClientDetailsSaveBo oauthClientDetailsSaveBo) {

        String clientSecretDecrypt = clientSecretDecrypt(oauthClientDetailsSaveBo.getCode(), oauthClientDetailsSaveBo.getClientSecret());

        if (!StringUtils.hasText(clientSecretDecrypt)) {
            throw new CloudRuntimeException("客户凭证 不能为空");
        }

        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        BeanUtils.copyProperties(oauthClientDetailsSaveBo, oauthClientDetails);

        oauthClientDetails.setClientSecret(clientSecretDecrypt);

        // 客户凭证加密
        encode(oauthClientDetails);

        return save(oauthClientDetails);
    }

    /**
     * 更新客户
     *
     * @param oauthClientDetailsUpdateBo 客户
     * @return 返回 更新结果
     */
    @Override
    public boolean updateByOauthClientDetailsUpdateBo(OauthClientDetailsUpdateBo oauthClientDetailsUpdateBo) {

        String clientSecretDecrypt = clientSecretDecrypt(oauthClientDetailsUpdateBo.getCode(), oauthClientDetailsUpdateBo.getClientSecret());

        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        BeanUtils.copyProperties(oauthClientDetailsUpdateBo, oauthClientDetails);

        oauthClientDetails.setClientSecret(clientSecretDecrypt);

        // 客户凭证加密
        encode(oauthClientDetails);

        return updateById(oauthClientDetails);
    }

    /**
     * 根据 客户ID 查询客户
     *
     * @param clientId 客户ID
     * @return 返回 查询结果
     */
    @Override
    public OauthClientDetails getByClientId(String clientId) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientId);
        return getOne(queryWrapper);
    }

    /**
     * 根据 客户ID 查询客户
     * <p>
     * 条件无逻辑删除的判断
     *
     * @param clientId 客户ID
     * @return 返回 用户信息
     */
    @Override
    public OauthClientDetails getLogicByClientId(String clientId) {
        return baseMapper.getLogicByClientId(clientId);
    }

    private String clientSecretDecrypt(String code, String clientSecret) {
        String privateKey = sessionService.getAttr(Constant.PRIVATE_KEY + ":" + code);

        String clientSecretDecrypt;
        if (StringUtils.hasText(privateKey)) {
            RSA rsa = new RSA(privateKey, null);

            if (Boolean.FALSE.toString().equals(clientSecret)) {
                return null;
            }

            clientSecretDecrypt = rsa.decryptStr(clientSecret, KeyType.PrivateKey);
            ValidationUtils.validate(new ClientSecretBo(clientSecretDecrypt));
        } else {
            throw new CloudRuntimeException("未找到RSA私钥，请刷新页面后重试");
        }
        return clientSecretDecrypt;
    }

    /**
     * 客户凭证加密
     *
     * @param oauthClientDetails 客户
     */
    private void encode(OauthClientDetails oauthClientDetails) {
        // 凭证加密后储存
        String clientSecret = oauthClientDetails.getClientSecret();
        if (StringUtils.hasText(clientSecret)) {
            PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String encode = delegatingPasswordEncoder.encode(clientSecret);
            oauthClientDetails.setClientSecret(encode);
        } else {
            oauthClientDetails.setClientSecret(null);
        }
    }

}
