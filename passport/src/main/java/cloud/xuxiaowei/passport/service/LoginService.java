package cloud.xuxiaowei.passport.service;

import cloud.xuxiaowei.utils.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录 服务
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface LoginService {

    /**
     * 登录失败处理
     *
     * @param request 请求
     * @return 返回 登录失败处理结果
     */
    Response<?> failure(HttpServletRequest request);

}
