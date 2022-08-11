package cloud.xuxiaowei.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import lombok.Data;

/**
 * 响应密文
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class ResponseEncrypt {

	/**
	 * 密文
	 */
	private String ciphertext;

	/**
	 * AES 加密版本
	 *
	 * @author xuxiaowei
	 * @since 0.0.1
	 */
	public enum AesVersion {

		/**
		 * AES 加密版本 v1
		 */
		V1("v1", Mode.CTS, Padding.PKCS5Padding);

		/**
		 * AES 加密版本
		 */
		public final String version;

		/**
		 * AES 加密模式
		 */
		public final Mode mode;

		/**
		 * AES 加密填充方式
		 */
		public final Padding padding;

		AesVersion(String version, Mode mode, Padding padding) {
			this.version = version;
			this.mode = mode;
			this.padding = padding;
		}

	}

}
