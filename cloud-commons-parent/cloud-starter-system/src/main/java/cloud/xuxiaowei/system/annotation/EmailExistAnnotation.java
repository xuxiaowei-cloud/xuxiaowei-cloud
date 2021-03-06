package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.EmailExistValidation;

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
@Constraint(validatedBy = { EmailExistValidation.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailExistAnnotation {

	String message() default "邮箱 已存在";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
