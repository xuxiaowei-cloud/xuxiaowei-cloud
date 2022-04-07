package cloud.xuxiaowei.audit.controller;

import cloud.xuxiaowei.utils.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权码Code
 *
 * <code>
 * 用户权限判断：@PreAuthorize("hasAuthority('user')")
 * </code>
 * <code>
 * 客户范围判断：@PreAuthorize("#oauth2.hasScope('snsapi_base')")
 * </code>
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/code")
public class CodeRestController {

    /**
     * 分页查询授权码
     *
     * @param request  请求
     * @param response 响应
     * @return 返回 分页查询结果
     */
    @RequestMapping("/page")
    public Response<?> page(HttpServletRequest request, HttpServletResponse response) {
        return Response.ok();
    }

}
