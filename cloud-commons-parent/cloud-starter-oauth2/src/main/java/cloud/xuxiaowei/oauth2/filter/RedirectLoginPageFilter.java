package cloud.xuxiaowei.oauth2.filter;

import cloud.xuxiaowei.core.properties.CloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向到 默认登录页面（在未配置登录页面URL时，即未设置：{@link HttpSecurity#formLogin()}） 过滤器
 *
 * @author xuxiaowei
 * @see DefaultLoginPageGeneratingFilter 默认登录页面
 * @since 0.0.1
 */
@Component
public class RedirectLoginPageFilter extends GenericFilterBean {

    private CloudSecurityProperties cloudSecurityProperties;

    @Autowired
    public void setCloudSecurityProperties(CloudSecurityProperties cloudSecurityProperties) {
        this.cloudSecurityProperties = cloudSecurityProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (isLoginUrlRequest(request)) {
            // 重定向到：默认登录页面地址
            response.sendRedirect(cloudSecurityProperties.getDefaultLoginPageUrl());
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isLoginUrlRequest(HttpServletRequest request) {
        return matches(request);
    }

    private boolean matches(HttpServletRequest request) {
        if (!HttpMethod.GET.toString().equals(request.getMethod())) {
            return false;
        }
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');
        if (pathParamIndex > 0) {
            // strip everything after the first semi-colon
            uri = uri.substring(0, pathParamIndex);
        }
        if (request.getQueryString() != null) {
            uri += "?" + request.getQueryString();
        }
        if ("".equals(request.getContextPath())) {
            return uri.equals(DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL);
        }
        return uri.equals(request.getContextPath() + DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL);
    }

}
