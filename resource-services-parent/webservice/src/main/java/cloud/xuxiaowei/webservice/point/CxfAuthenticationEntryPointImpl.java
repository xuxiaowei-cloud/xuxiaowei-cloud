package cloud.xuxiaowei.webservice.point;

import cloud.xuxiaowei.utils.RequestUtils;
import org.apache.http.entity.ContentType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CXF 身份验证入口点
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Component
public class CxfAuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String inputStream = RequestUtils.getInputStream(request);
        System.out.println(inputStream);

        response.setContentType(ContentType.APPLICATION_SOAP_XML.toString());

    }

}
