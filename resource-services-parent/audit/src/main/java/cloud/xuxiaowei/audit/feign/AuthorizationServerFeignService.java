package cloud.xuxiaowei.audit.feign;

import cloud.xuxiaowei.openfeign.interceptor.AuthorizationRequestInterceptor;
import cloud.xuxiaowei.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 授权服务 feign 接口
 *
 * @author xuxiaowei
 * @see FeignClient#contextId() 防止出现相同的 {@link FeignClient#value()} 时异常
 * @since 0.0.1
 */
@FeignClient(value = "authorization-server", contextId = "authorization-server", configuration = {AuthorizationRequestInterceptor.class})
public interface AuthorizationServerFeignService {

    /**
     * 分页查询授权码
     *
     * @return 返回 分页查询授权码 结果
     */
    @PostMapping("/code/page")
    Response<?> codePage();

}
