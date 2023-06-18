package cloud.xuxiaowei.system.validation;

import cloud.xuxiaowei.system.annotation.NumberAnnotation;
import cloud.xuxiaowei.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForCharSequence;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 是否包含数字 验证
 *
 * @author xuxiaowei
 * @see SizeValidatorForCharSequence
 * @since 0.0.1
 */
@Slf4j
public class NumberValidation implements ConstraintValidator<NumberAnnotation, String> {

	@Override
	public void initialize(NumberAnnotation constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {

			String[] split = value.split("");

			for (String s : split) {
				if (Constants.DIGIT_LIST.contains(s)) {
					return true;
				}
			}

			return false;
		}
		return true;
	}

}
