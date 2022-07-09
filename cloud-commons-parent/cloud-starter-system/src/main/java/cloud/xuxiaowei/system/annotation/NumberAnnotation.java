package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.NumberValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 是否包含数字 验证
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Constraint(validatedBy = { NumberValidation.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberAnnotation {

	String message() default "内容必须包含数字";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
