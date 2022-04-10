package cloud.xuxiaowei.audit.resilience4j;

import cloud.xuxiaowei.audit.feign.AuthorizationServerFeignService;
import cloud.xuxiaowei.oauth2.bo.AuditCodePageBo;
import cloud.xuxiaowei.utils.Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 授权服务 resilience4j 服务实现
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Service
public class AuthorizationServerResilience4jService {

    private AuthorizationServerFeignService authorizationServerFeignService;

    @Autowired
    public void setAuthorizationServerFeignService(AuthorizationServerFeignService authorizationServerFeignService) {
        this.authorizationServerFeignService = authorizationServerFeignService;
    }

    /**
     * 分页查询授权码
     *
     * @param auditCodePageBo 审计授权码分页参数
     * @return 返回 分页查询授权码 结果
     */
    @CircuitBreaker(name = "pageByAuditCode", fallbackMethod = "fallbackMethod")
    public Response<?> pageByAuditCode(AuditCodePageBo auditCodePageBo) {
        return authorizationServerFeignService.pageByAuditCode(auditCodePageBo);
    }

    /**
     * 分页查询授权码 熔断
     *
     * @param e 异常
     * @return 返回 分页查询授权码 结果
     */
    public Response<?> fallbackMethod(Exception e) {

        return Response.error("授权服务异常");
    }

}
