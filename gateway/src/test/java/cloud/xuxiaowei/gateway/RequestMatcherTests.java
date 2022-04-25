package cloud.xuxiaowei.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.web.util.matcher.*;
import org.springframework.web.reactive.result.condition.RequestMethodsRequestCondition;
import org.springframework.web.reactive.result.method.RequestMappingInfo;

/**
 * 路径、IP匹配 测试类
 *
 * @author xuxiaowei
 * @see RequestMatcher
 * @see ELRequestMatcher
 * @see RequestHeaderRequestMatcher
 * @see MediaTypeRequestMatcher
 * @see RegexRequestMatcher
 * @see RequestMethodsRequestCondition
 * @see RequestMappingInfo
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
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher("192.168.5.4/30");
        for (int i = 0; i < 256; i++) {
            String ip = "192.168.5." + i;
            boolean matches = ipAddressMatcher.matches(ip);
            System.out.println(ip + "\t" + matches);
        }
    }

}
