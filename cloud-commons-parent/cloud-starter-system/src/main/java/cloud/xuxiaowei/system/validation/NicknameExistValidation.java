package cloud.xuxiaowei.system.validation;

import cloud.xuxiaowei.system.annotation.NicknameExistAnnotation;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForCharSequence;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 昵称 验证
 * <p>
 * 昵称存在时异常（为 null 时跳过验证）
 *
 * @author xuxiaowei
 * @see SizeValidatorForCharSequence
 * @since 0.0.1
 */
@Slf4j
public class NicknameExistValidation implements ConstraintValidator<NicknameExistAnnotation, String> {

	private IUsersService usersService;

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	@Override
	public void initialize(NicknameExistAnnotation constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			Users users = usersService.getByNickname(value);
			return users == null;
		}
		return true;
	}

}
