package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.NicknameLogicValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 昵称 验证 注解
 * <p>
 * 昵称存在时异常（为 null 时跳过验证）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Constraint(validatedBy = { NicknameLogicValidation.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NicknameLogicAnnotation {

	String message() default "昵称 已被逻辑删除";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
