package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.UpperCaseValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 是否包含大写字母 验证
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Constraint(validatedBy = {UpperCaseValidation.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UpperCaseAnnotation {

    String message() default "内容必须包含大写字母";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
