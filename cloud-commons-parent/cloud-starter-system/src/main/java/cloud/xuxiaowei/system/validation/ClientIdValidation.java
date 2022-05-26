package cloud.xuxiaowei.system.validation;

import cloud.xuxiaowei.system.annotation.ClientIdAnnotation;
import cloud.xuxiaowei.system.entity.OauthClientDetails;
import cloud.xuxiaowei.system.service.IOauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForCharSequence;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 客户ID 验证
 * <p>
 * 客户ID不存在时异常（为 null 时跳过验证）
 *
 * @author xuxiaowei
 * @see SizeValidatorForCharSequence
 * @since 0.0.1
 */
@Slf4j
public class ClientIdValidation implements ConstraintValidator<ClientIdAnnotation, String> {

    private IOauthClientDetailsService oauthClientDetailsService;

    @Autowired
    public void setOauthClientDetailsService(IOauthClientDetailsService oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
    }

    @Override
    public void initialize(ClientIdAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            OauthClientDetails oauthClientDetails = oauthClientDetailsService.getByClientId(value);
            return oauthClientDetails != null;
        }
        return true;
    }

}
