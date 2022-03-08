package cloud.xuxiaowei.authorizationserver.filter;

import cloud.xuxiaowei.core.properties.CloudProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

    private CloudProperties cloudProperties;

    @Autowired
    public void setCloudProperties(CloudProperties cloudProperties) {
        this.cloudProperties = cloudProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 重定向到登录页面
        httpServletResponse.sendRedirect(cloudProperties.getLoginPageUrl());

    }

}
