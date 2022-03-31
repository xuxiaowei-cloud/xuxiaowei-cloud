package cloud.xuxiaowei.authorizationserver.configuration;

import cloud.xuxiaowei.utils.CodeEnums;
import cloud.xuxiaowei.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;

/**
 * Web 响应异常翻译器 配置
 * <p>
 * 使用 {@link OAuth2Exception#addAdditionalInformation(String, String)} 替换重写 {@link CheckTokenEndpoint}
 *
 * @author xuxiaowei
 * @see CheckTokenEndpoint
 * @see DefaultWebResponseExceptionTranslator
 * @since 0.0.1
 */
@Configuration
public class WebResponseExceptionTranslatorConfiguration implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Autowired
    public void setCheckTokenEndpoint(CheckTokenEndpoint checkTokenEndpoint) {
        checkTokenEndpoint.setExceptionTranslator(this);
    }

    /**
     * @see CodeEnums#T10001
     */
    public final String CODE_ENUMS_T10001_MESSAGE = "Token was not recognised";

    /**
     * @see CodeEnums#T10001
     */
    public final String CODE_ENUMS_T10002_MESSAGE = "Token has expired";

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        // 尝试从堆栈跟踪中提取 SpringSecurityException
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
        Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

        if (ase != null) {
            @SuppressWarnings("all")
            OAuth2Exception oauth2Exception = (OAuth2Exception) ase;

            String message = oauth2Exception.getMessage();
            if (CODE_ENUMS_T10001_MESSAGE.equals(message)) {
                oauth2Exception.addAdditionalInformation(Response.CODE, CodeEnums.T10001.code);
                oauth2Exception.addAdditionalInformation(Response.MSG, CodeEnums.T10001.msg);
            } else if (CODE_ENUMS_T10002_MESSAGE.equals(message)) {
                oauth2Exception.addAdditionalInformation(Response.CODE, CodeEnums.T10002.code);
                oauth2Exception.addAdditionalInformation(Response.MSG, CodeEnums.T10002.msg);
            } else {
                oauth2Exception.addAdditionalInformation(Response.CODE, CodeEnums.T10000.code);
                oauth2Exception.addAdditionalInformation(Response.MSG, CodeEnums.T10000.msg);
            }

            oauth2Exception.addAdditionalInformation(Response.DATA, null);
            oauth2Exception.addAdditionalInformation(Response.FIELD, null);
            oauth2Exception.addAdditionalInformation(Response.EXPLAIN, null);
            oauth2Exception.addAdditionalInformation(Response.REQUEST_ID, null);

            return new ResponseEntity<>(oauth2Exception, HttpStatus.OK);
        }

        ///////////////////////////////以下异常待开发/////////////////////////////

        ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
                causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new WebResponseExceptionTranslatorConfiguration.UnauthorizedException(e.getMessage(), e));
        }

        ase = (AccessDeniedException) throwableAnalyzer
                .getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new WebResponseExceptionTranslatorConfiguration.ForbiddenException(ase.getMessage(), ase));
        }

        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType(
                HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new WebResponseExceptionTranslatorConfiguration.MethodNotAllowed(ase.getMessage(), ase));
        }

        return handleOAuth2Exception(new WebResponseExceptionTranslatorConfiguration.ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));
    }

    private static class UnauthorizedException extends OAuth2Exception {

        public UnauthorizedException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
        public String getOAuth2ErrorCode() {
            return "unauthorized";
        }

        @Override
        public int getHttpErrorCode() {
            return 401;
        }

    }

    private static class ForbiddenException extends OAuth2Exception {

        public ForbiddenException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
        public String getOAuth2ErrorCode() {
            return "access_denied";
        }

        @Override
        public int getHttpErrorCode() {
            return 403;
        }

    }

    private static class MethodNotAllowed extends OAuth2Exception {

        public MethodNotAllowed(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
        public String getOAuth2ErrorCode() {
            return "method_not_allowed";
        }

        @Override
        public int getHttpErrorCode() {
            return 405;
        }

    }

    private static class ServerErrorException extends OAuth2Exception {

        public ServerErrorException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
        public String getOAuth2ErrorCode() {
            return "server_error";
        }

        @Override
        public int getHttpErrorCode() {
            return 500;
        }

    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) throws IOException {

        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }

        return new ResponseEntity<>(e, headers, HttpStatus.valueOf(status));
    }

}
