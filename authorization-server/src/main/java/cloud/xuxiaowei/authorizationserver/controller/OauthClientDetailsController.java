package cloud.xuxiaowei.authorizationserver.controller;

import cloud.xuxiaowei.system.annotation.ControllerAnnotation;
import cloud.xuxiaowei.system.bo.OauthClientDetailsPageBo;
import cloud.xuxiaowei.system.service.IOauthClientDetailsService;
import cloud.xuxiaowei.system.vo.OauthClientDetailsVo;
import cloud.xuxiaowei.utils.Response;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 客户表
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/oauth-client-details")
public class OauthClientDetailsController {

    private IOauthClientDetailsService oauthClientDetailsService;

    @Autowired
    public void setOauthClientDetailsService(IOauthClientDetailsService oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
    }

    /**
     * 分页查询客户
     *
     * @param request                  请求
     * @param response                 响应
     * @param oauthClientDetailsPageBo 客户分页参数
     * @return 返回 分页查询结果
     */
    @ControllerAnnotation(description = "分页查询客户")
    @PreAuthorize("hasAuthority('manage_client_read') or #oauth2.hasScope('manage_client_read')")
    @RequestMapping("/page")
    public Response<?> page(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody OauthClientDetailsPageBo oauthClientDetailsPageBo) {

        IPage<OauthClientDetailsVo> page = oauthClientDetailsService.pageByOauthClientDetails(oauthClientDetailsPageBo);

        return Response.ok(page);
    }

}
