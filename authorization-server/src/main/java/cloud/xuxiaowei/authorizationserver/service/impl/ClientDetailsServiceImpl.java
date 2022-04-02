package cloud.xuxiaowei.authorizationserver.service.impl;

import cloud.xuxiaowei.authorizationserver.bo.ClientDetailsBo;
import cloud.xuxiaowei.system.entity.OauthClientDetails;
import cloud.xuxiaowei.system.service.IOauthClientDetailsService;
import cloud.xuxiaowei.utils.exception.client.ClientIdEmptyException;
import cloud.xuxiaowei.utils.exception.client.ClientIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.security.Principal;
import java.util.Map;

/**
 * 提供有关 OAuth2 客户端的详细信息的服务。
 *
 * @author xuxiaowei
 * @see ExceptionHandlerExceptionResolver
 * @see AuthorizationEndpoint#authorize(Map, Map, SessionStatus, Principal) 授权码
 * @since 0.0.1
 */
@Slf4j
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private IOauthClientDetailsService oauthClientDetailsService;

    @Autowired
    public void setOauthClientDetailsService(IOauthClientDetailsService oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
    }

    /**
     * 根据 客户ID 查询 客户端的详细信息
     *
     * @param clientId 客户ID
     * @return 返回 客户端的详细信息
     * @throws ClientRegistrationException 客户注册异常
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (!StringUtils.hasText(clientId)) {
            throw new ClientIdEmptyException();
        }

        OauthClientDetails oauthClientDetails = oauthClientDetailsService.getByClientId(clientId);

        if (oauthClientDetails == null) {
            throw new ClientIdException();
        }

        ClientDetailsBo clientDetailsBo = new ClientDetailsBo();
        BeanUtils.copyProperties(oauthClientDetails, clientDetailsBo);

        return clientDetailsBo;
    }

}
