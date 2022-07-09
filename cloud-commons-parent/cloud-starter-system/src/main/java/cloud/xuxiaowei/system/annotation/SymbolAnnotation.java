package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.SymbolValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 是否包含特殊符号 验证
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Constraint(validatedBy = { SymbolValidation.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SymbolAnnotation {

	String message() default "内容必须包含特殊符号";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
