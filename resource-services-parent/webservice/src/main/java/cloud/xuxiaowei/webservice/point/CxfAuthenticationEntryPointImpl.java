package cloud.xuxiaowei.webservice.point;

import cloud.xuxiaowei.utils.RequestUtils;
import com.google.common.net.MediaType;
import lombok.extern.slf4j.Slf4j;
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
 * @see MediaType#XML_UTF_8 SOAP 1.1
 * @see ContentType#APPLICATION_SOAP_XML SOAP 1.2
 * @since 0.0.1
 */
@Slf4j
@Component
public class CxfAuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String inputStream = RequestUtils.getInputStream(request);
        log.info(inputStream);


        response.setContentType(ContentType.APPLICATION_SOAP_XML.toString());

    }

}
