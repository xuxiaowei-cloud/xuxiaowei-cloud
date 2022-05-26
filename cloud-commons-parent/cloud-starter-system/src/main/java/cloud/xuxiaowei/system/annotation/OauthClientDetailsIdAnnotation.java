package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.system.validation.OauthClientDetailsIdValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 客户主键 验证 注解
 * <p>
 * 客户主键不存在时异常（为 null 时跳过验证）
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Constraint(validatedBy = {OauthClientDetailsIdValidation.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OauthClientDetailsIdAnnotation {

    String message() default "客户主键 不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
