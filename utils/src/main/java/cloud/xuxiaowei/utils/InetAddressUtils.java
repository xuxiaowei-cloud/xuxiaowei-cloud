package cloud.xuxiaowei.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 地址工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class InetAddressUtils {

	/**
	 * 获取主机名
	 * @return 返回主机名，如果无法获取主机的IP，将返回 null
	 */
	public static String getHostName() {
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
		}
		catch (UnknownHostException e) {
			return null;
		}
		return inetAddress.getHostName();
	}

}
