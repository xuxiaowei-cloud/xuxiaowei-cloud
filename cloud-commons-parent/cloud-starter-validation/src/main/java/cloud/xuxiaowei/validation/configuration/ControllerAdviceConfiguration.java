package cloud.xuxiaowei.validation.configuration;

import cloud.xuxiaowei.utils.Response;
import cloud.xuxiaowei.utils.exception.TokenException;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link Controller}、{@link RestController} 异常处理
 * <p>
 * 由于 {@link ControllerAdvice} 不支持通配符，故本配置直接扫描所有 {@link Controller}、{@link RestController} 父包
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice(basePackages = {
        "cloud.xuxiaowei",
        "org.springframework.security.oauth2.provider.endpoint"
})
public class ControllerAdviceConfiguration {

    /**
     * Token 异常父类
     *
     * @param exception Token 异常父类
     * @param request   请求
     * @return 返回 验证结果
     */
    @ResponseBody
    @ExceptionHandler(TokenException.class)
    public Response<?> tokenException(TokenException exception, WebRequest request) {

        log.error(String.format("%s：%s", exception.code, exception.msg), exception);

        return Response.error(exception.code, exception.msg);
    }

    /**
     * 请求时数据转换失败处理
     *
     * @param exception 异常
     * @param request   请求
     * @return 返回 验证结果
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> methodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {

        Response<?> error = Response.error("方法参数无效异常");

        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

        fieldErrorList(error, fieldErrorList);

        log.error("方法参数无效异常：{}，异常：{}", error, exception);

        return error;
    }

    /**
     * 字段转换异常统一处理
     *
     * @param error          异常
     * @param fieldErrorList 异常字段列表
     */
    private void fieldErrorList(Response<?> error, List<FieldError> fieldErrorList) {

        List<String> fieldList = new ArrayList<>();
        List<String> messageList = new ArrayList<>();

        for (FieldError fieldError : fieldErrorList) {
            String field = fieldError.getField();
            fieldList.add(field);

            String defaultMessage = fieldError.getDefaultMessage();
            messageList.add(defaultMessage);
        }

        error.setField(Joiner.on(",").join(fieldList));
        error.setMsg(Joiner.on(",").join(messageList));
    }

}
