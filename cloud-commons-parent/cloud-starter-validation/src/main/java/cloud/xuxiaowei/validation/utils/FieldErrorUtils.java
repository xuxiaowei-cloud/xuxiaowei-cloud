package cloud.xuxiaowei.validation.utils;

import cloud.xuxiaowei.utils.Response;
import com.google.common.base.Joiner;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class FieldErrorUtils {

    /**
     * 字段转换异常统一处理
     *
     * @param error          异常
     * @param fieldErrorList 异常字段列表
     */
    public static void list(Response<?> error, List<FieldError> fieldErrorList) {

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
