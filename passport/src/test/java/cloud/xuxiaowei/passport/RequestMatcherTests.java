package cloud.xuxiaowei.passport;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

/**
 * 路径匹配 测试类
 *
 * @author xuxiaowei
 * @see org.springframework.security.web.util.matcher.RequestMatcher
 * @see org.springframework.security.web.util.matcher.IpAddressMatcher
 * @see org.springframework.security.web.util.matcher.ELRequestMatcher
 * @see org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher
 * @see org.springframework.security.web.util.matcher.MediaTypeRequestMatcher
 * @see org.springframework.security.web.util.matcher.RegexRequestMatcher
 * @since 0.0.1
 */
@Slf4j
class RequestMatcherTests {

	/**
	 * 常用内网IP地址：
	 * <p>
	 * 10.0.0.0 到 10.255.255.255（CIDR：10.0.0.0/8）
	 * <p>
	 * 172.16.0.0 到 172.31.255.255（CIDR：172.16.0.0/12）
	 * <p>
	 * 192.168.0.0 到 192.168.255.255（CIDR：192.168.0.0/16）
	 * <p>
	 * <p>
	 * /8：16777214个用作主机地址，16777216个可用的IP地址
	 * <p>
	 * /9：8388606个用作主机地址，8388608个可用的IP地址
	 * <p>
	 * /10：4194302个用作主机地址，4194304个可用的IP地址
	 * <p>
	 * /11：2097150个用作主机地址，2097152个可用的IP地址
	 * <p>
	 * /12：1048574个用作主机地址，1048576个可用的IP地址
	 * <p>
	 * /13：524286个用作主机地址，524288个可用的IP地址
	 * <p>
	 * /14：262142个用作主机地址，262144个可用的IP地址
	 * <p>
	 * /15：131070个用作主机地址，131072个可用的IP地址
	 * <p>
	 * /16：65534个用作主机地址，65536个可用的IP地址
	 * <p>
	 * /17：32766个用作主机地址，32768个可用的IP地址
	 * <p>
	 * /18：16382个用作主机地址，16384个可用的IP地址
	 * <p>
	 * /19：8190个用作主机地址，8192个可用的IP地址
	 * <p>
	 * /20：4094个用作主机地址，4096个可用的IP地址
	 * <p>
	 * /21：2046个用作主机地址，2048个可用的IP地址
	 * <p>
	 * /22：1022个用作主机地址，1024个可用的IP地址
	 * <p>
	 * /23：510个用作主机地址，512个可用的IP地址
	 * <p>
	 * /24：254个用作主机地址，256个可用的IP地址
	 * <p>
	 * /25：126个用作主机地址，128个可用的IP地址
	 * <p>
	 * /26：62个用作主机地址，64个可用的IP地址
	 * <p>
	 * /27：30个用作主机地址，32个可用的IP地址
	 * <p>
	 * /28：14个用作主机地址，16个可用的IP地址
	 * <p>
	 * /29：6个用作主机地址，8个可用的IP地址
	 * <p>
	 * /30：2个用作主机地址，4个可用的IP地址
	 * <p>
	 * /31：0个用作主机地址，2个可用的IP地址
	 * <p>
	 * /32：IP地址作为单个主机使用，即该IP地址没有可用的子网
	 * <p>
	 */
	@Test
	void ipAddressMatcher() {
		IpAddressMatcher ipAddressMatcher = new IpAddressMatcher("192.168.5.1/24");
		boolean matches = ipAddressMatcher.matches("192.168.5.2");
		System.out.println(matches);
	}

}
