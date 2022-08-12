package cloud.xuxiaowei.system.annotation;

import cloud.xuxiaowei.utils.ResponseEncrypt;

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
	ResponseEncrypt.AesVersion value();

}
