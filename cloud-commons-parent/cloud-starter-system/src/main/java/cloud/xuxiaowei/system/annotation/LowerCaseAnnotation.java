package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.LowerCaseValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 是否包含小写字母 验证
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Constraint(validatedBy = { LowerCaseValidation.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LowerCaseAnnotation {

	String message() default "内容必须包含小写字母";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
