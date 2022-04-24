package cloud.xuxiaowei.webservice.point;

import cloud.xuxiaowei.core.properties.CloudWebServiceProperties;
import cloud.xuxiaowei.utils.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.net.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SOAPConstants;
import java.io.IOException;
import java.util.List;

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

    private CloudWebServiceProperties cloudWebServiceProperties;

    @Autowired
    public void setCloudWebServiceProperties(CloudWebServiceProperties cloudWebServiceProperties) {
        this.cloudWebServiceProperties = cloudWebServiceProperties;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String requestXml = RequestUtils.getInputStream(request);
        log.info("CXF 身份验证入口点-请求：{}", requestXml);

        XmlMapper xmlMapper = new XmlMapper();

        if (!StringUtils.hasText(requestXml)) {
            String msg = String.format("WebService 请求体不能为空：%s", requestXml);
            log.error(msg);
            Response<?> error = Response.error(msg);
            error.setExplain(requestXml);
            String string = xmlMapper.writeValueAsString(error);
            // 请求体存在问题，无需关心返回内容的格式
            ResponseUtils.response(response, string, MediaType.XML_UTF_8.toString());
            return;
        }

        Document requestDocument;
        try {
            requestDocument = DocumentHelper.parseText(requestXml);
        } catch (DocumentException e) {
            String msg = "WebService 请求体不是 标准XML";
            log.error(msg, e);
            Response<?> error = Response.error(msg);
            error.setExplain(requestXml);
            String string = xmlMapper.writeValueAsString(error);
            // 请求体存在问题，无需关心返回内容的格式
            ResponseUtils.response(response, string, MediaType.XML_UTF_8.toString());
            return;
        }

        // 获取父节点
        Element rootElement = requestDocument.getRootElement();

        // QName 命名空间
        QName rootElementQname = rootElement.getQName();
        // 获取文档工厂
        DocumentFactory documentFactory = rootElementQname.getDocumentFactory();
        // 获取所有 QName 命名空间
        List<QName> qNames = documentFactory.getQNames();

        // 默认值
        String soap = SOAPConstants.URI_NS_SOAP_1_2_ENVELOPE;
        String namespace = "http://webservice.xuxiaowei.cloud";
        String methodName = "";

        // 找出调用的方法与命名空间
        for (QName qName : qNames) {

            String name = qName.getName();
            String namespaceUri = qName.getNamespaceURI();

            if ("Envelope".equals(name)) {
                soap = namespaceUri;
            }

            log.info(name + "\t" + namespaceUri);
            if (cloudWebServiceProperties.getNamespaceUriList().contains(namespaceUri)) {
                namespace = namespaceUri;
                methodName = name;
            }
        }

        Document responseDocument = DocumentHelper.createDocument();

        Element envelope = responseDocument.addElement("soap:Envelope");

        if (SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE.contains(soap)) {
            envelope.addAttribute("xmlns:soap", SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE);
            response.setContentType(MediaType.XML_UTF_8.toString());
        } else if (SOAPConstants.URI_NS_SOAP_1_2_ENVELOPE.contains(soap)) {
            envelope.addAttribute("xmlns:soap", SOAPConstants.URI_NS_SOAP_1_2_ENVELOPE);
            response.setContentType(ContentType.APPLICATION_SOAP_XML.toString());
        }

        Element body = envelope.addElement("Body");
        body.setName("soap:Body");

        Element methodNameResponse = body.addElement("Response");
        methodNameResponse.setName("ns2:" + methodName + "Response");
        methodNameResponse.addAttribute("xmlns:ns2", namespace);

        Element responseElement = methodNameResponse.addElement("response");

        Element codeElement = responseElement.addElement("code");
        Element msgElement = responseElement.addElement("msg");
        Element dataElement = responseElement.addElement("data");
        Element fieldElement = responseElement.addElement("field");
        Element explainElement = responseElement.addElement("explain");
        Element requestIdElement = responseElement.addElement("requestId");

        Response<?> error = Response.error(CodeEnums.T00000.code, CodeEnums.T00000.msg);

        if (!(authException instanceof InsufficientAuthenticationException)) {
            error.setExplain(authException.getMessage());
        }
        String code = error.getCode();
        String msg = error.getMsg();
        String field = error.getField();
        String explain = error.getExplain();
        String requestId = error.getRequestId();

        if (code != null) {
            codeElement.setText(code);
        }
        if (msg != null) {
            msgElement.setText(msg);
        }
        if (field != null) {
            fieldElement.setText(field);
        }
        if (explain != null) {
            explainElement.setText(explain);
        }
        if (requestId != null) {
            requestIdElement.setText(requestId);
        }

        String asXml = Dom4jUtils.asXml(responseDocument);
        log.info("CXF 身份验证入口点-响应：{}", asXml);

        response.getWriter().println(asXml);
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
    }

}
