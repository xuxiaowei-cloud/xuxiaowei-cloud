package cloud.xuxiaowei.passport;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;

import java.util.List;

/**
 * 路径匹配 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class RequestMatcherTests {

    @Test
    void ipAddressMatcher() {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher("192.168.5.1/24");
        boolean matches = ipAddressMatcher.matches("192.168.5.2");
        System.out.println(matches);
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
