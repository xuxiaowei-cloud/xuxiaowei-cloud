package cloud.xuxiaowei.passport;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * RSA 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class RsaTests {

	@Test
	void rea() {
		RSA generate = new RSA();

		// 获取私钥
		String privateKeyBase64 = generate.getPrivateKeyBase64();

		// 获取公钥
		String publicKeyBase64 = generate.getPublicKeyBase64();
		log.info("PrivateKey：{}", privateKeyBase64);
		log.info("PublicKey：{}", publicKeyBase64);

		// 设置公钥、私钥
		RSA rsa = new RSA(privateKeyBase64, publicKeyBase64);

		// 使用公钥加密
		String encryptBase64 = rsa.encryptBase64("你好，世界！", KeyType.PublicKey);
		log.info(encryptBase64);

		// 使用私钥解密
		String decryptStr = rsa.decryptStr(encryptBase64, KeyType.PrivateKey);
		log.info(decryptStr);
	}

}
