package cloud.xuxiaowei.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;

import java.util.List;

/**
 * 路径、IP匹配 测试类
 *
 * @author xuxiaowei
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

    @Test
    void patternsRequestCondition() {
        PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition("/login/**", "/login2/**");

        List<String> login = patternsRequestCondition.getMatchingPatterns("/login");
        log.info(String.valueOf(login.size()));

        List<String> loginCode = patternsRequestCondition.getMatchingPatterns("/login/code");
        log.info(String.valueOf(loginCode.size()));

        List<String> loginCodeAbc = patternsRequestCondition.getMatchingPatterns("/login/code/abc");
        log.info(String.valueOf(loginCodeAbc.size()));

        List<String> login2CodeAbc = patternsRequestCondition.getMatchingPatterns("/login2/code/abc");
        log.info(String.valueOf(login2CodeAbc.size()));

        List<String> login3CodeAbc = patternsRequestCondition.getMatchingPatterns("/login3/code/abc");
        log.info(String.valueOf(login3CodeAbc.size()));
    }

}
