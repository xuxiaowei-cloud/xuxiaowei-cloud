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
     * /24：254：256
     * /25：126：128
     * /26：62：64
     * /27：30：32
     * /28：14：16
     * /29：6：8
     * /30：2：4
     */
    @Test
    void ipAddressMatcher() {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher("192.168.5.1/24");
        boolean matches = ipAddressMatcher.matches("192.168.5.2");
        System.out.println(matches);
    }

}
