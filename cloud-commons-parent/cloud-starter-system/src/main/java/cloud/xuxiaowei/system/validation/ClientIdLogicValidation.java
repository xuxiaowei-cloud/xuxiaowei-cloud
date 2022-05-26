package cloud.xuxiaowei.system.validation;

import cloud.xuxiaowei.system.annotation.ClientIdLogicAnnotation;
import cloud.xuxiaowei.system.entity.OauthClientDetails;
import cloud.xuxiaowei.system.entity.Users;
import cloud.xuxiaowei.system.service.IOauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForCharSequence;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 客户ID 验证
 * <p>
 * 客户ID存在时异常（为 null 时跳过验证）
 *
 * @author xuxiaowei
 * @see SizeValidatorForCharSequence
 * @since 0.0.1
 */
@Slf4j
public class ClientIdLogicValidation implements ConstraintValidator<ClientIdLogicAnnotation, String> {

    private IOauthClientDetailsService oauthClientDetailsService;

    @Autowired
    public void setOauthClientDetailsService(IOauthClientDetailsService oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
    }

    @Override
    public void initialize(ClientIdLogicAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            OauthClientDetails oauthClientDetails = oauthClientDetailsService.getLogicByClientId(value);
            if (oauthClientDetails == null) {
                return true;
            } else {
                Boolean deleted = oauthClientDetails.getDeleted();
                return deleted != null && !deleted;
            }
        }
        return true;
    }

}
