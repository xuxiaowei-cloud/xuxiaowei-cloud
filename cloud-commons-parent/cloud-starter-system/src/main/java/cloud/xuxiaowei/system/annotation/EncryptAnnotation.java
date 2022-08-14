package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.utils.Encrypt;

import java.lang.annotation.*;

/**
 * AES 加密注解
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptAnnotation {

	/**
	 * 加密方式（版本）
	 */
	Encrypt.AesVersion value();

	/**
	 * 客户 AES 加密注解
	 */
	ClientIdEncryptAnnotation[] client() default {};

	/**
	 * 客户 AES 加密注解
	 *
	 * @author xuxiaowei
	 * @since 0.0.1
	 */
	@interface ClientIdEncryptAnnotation {

		/**
		 * 客户ID
		 */
		String cloudId();

		/**
		 * 加密方式（版本）
		 */
		Encrypt.AesVersion value();

	}

}
