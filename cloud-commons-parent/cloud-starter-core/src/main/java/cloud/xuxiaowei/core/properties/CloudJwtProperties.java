package cloud.xuxiaowei.core.properties;

import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import java.security.InvalidKeyException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 微服务 JWT 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("cloud.jwt")
public class CloudJwtProperties {

    /**
     * RSA
     */
    private Rsa rsa;

    /**
     * RSA
     *
     * @author xuxiaowei
     * @since 0.0.1
     */
    @Data
    public static class Rsa {

        /**
         * 公钥
         */
        private String publicKey;

        /**
         * 私钥
         */
        private String privateKey;

        /**
         * 获取公钥
         *
         * @return 返回 公钥
         * @throws InvalidKeyException 秘钥不合法
         */
        public RSAPublicKey publicKey() throws InvalidKeyException {
            return RSAPublicKeyImpl.newKey(Base64.decodeBase64(publicKey));
        }

        /**
         * 获取私钥
         *
         * @return 返回 私钥
         * @throws InvalidKeyException 秘钥不合法
         */
        public RSAPrivateKey privateKey() throws InvalidKeyException {
            return RSAPrivateCrtKeyImpl.newKey(Base64.decodeBase64(privateKey));
        }

    }

}
