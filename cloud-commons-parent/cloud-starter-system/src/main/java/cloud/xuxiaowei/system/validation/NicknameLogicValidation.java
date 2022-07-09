package cloud.xuxiaowei.system.validation;

import cloud.xuxiaowei.system.annotation.NicknameLogicAnnotation;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForCharSequence;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 用户名 验证
 * <p>
 * 用户名存在时异常（为 null 时跳过验证）
 *
 * @author xuxiaowei
 * @see SizeValidatorForCharSequence
 * @since 0.0.1
 */
@Slf4j
public class NicknameLogicValidation implements ConstraintValidator<NicknameLogicAnnotation, String> {

	private IUsersService usersService;

	@Autowired
	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}

	@Override
	public void initialize(NicknameLogicAnnotation constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			Users users = usersService.getLogicByNickname(value);
			if (users == null) {
				return true;
			}
			else {
				Boolean deleted = users.getDeleted();
				return deleted != null && !deleted;
			}
		}
		return true;
	}

}
