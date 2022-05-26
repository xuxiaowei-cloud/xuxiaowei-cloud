package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.ClientIdExistValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 客户ID 验证 注解
 * <p>
 * 客户ID存在时异常（为 null 时跳过验证）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Constraint(validatedBy = {ClientIdExistValidation.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientIdExistAnnotation {

    String message() default "客户ID 已存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
