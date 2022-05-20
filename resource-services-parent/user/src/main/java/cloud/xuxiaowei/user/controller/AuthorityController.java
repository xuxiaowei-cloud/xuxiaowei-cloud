package cloud.xuxiaowei.user.controller;

import cloud.xuxiaowei.system.entity.Authority;
import cloud.xuxiaowei.system.service.IAuthorityService;
import cloud.xuxiaowei.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 权限与权限说明表 前端控制器
 * </p>
 *
 * @author xuxiaowei
 * @since 2022-05-17
 */
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    private IAuthorityService authorityService;

    @Autowired
    public void setAuthorityService(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * 获取 权限与权限说明 字典
     *
     * @param request  请求
     * @param response 响应
     * @return 返回 结果
     */
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('manage_user_authority') or #oauth2.hasScope('manage_user_authority')")
    public Response<?> list(HttpServletRequest request, HttpServletResponse response) {

        List<Authority> authorityList = authorityService.list();

        return Response.ok(authorityList);
    }

}
