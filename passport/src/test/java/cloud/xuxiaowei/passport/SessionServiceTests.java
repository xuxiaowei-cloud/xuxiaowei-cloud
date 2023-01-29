package cloud.xuxiaowei.passport;

import cloud.xuxiaowei.system.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.http.HttpSession;

/**
 * {@link HttpSession} 服务接口 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@SpringBootTest
class SessionServiceTests {

	@Autowired
	private SessionService sessionService;

	@Test
	@WithMockUser
	void setAttribute() {
		sessionService.setAttribute("name", "徐晓伟");
	}

}
