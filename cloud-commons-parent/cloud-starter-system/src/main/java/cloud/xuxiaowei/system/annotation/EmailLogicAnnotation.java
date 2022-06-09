package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.EmailLogicValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 邮箱 验证 注解
 * <p>
 * 邮箱存在时异常（为 null 时跳过验证）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Constraint(validatedBy = {EmailLogicValidation.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailLogicAnnotation {

    String message() default "邮箱 已被逻辑删除";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
