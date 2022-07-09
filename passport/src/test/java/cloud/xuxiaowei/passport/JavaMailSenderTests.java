package cloud.xuxiaowei.passport;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * 邮件发送测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@SpringBootTest
class JavaMailSenderTests {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MailProperties mailProperties;

	@Test
	void send() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(mailProperties.getUsername());
		simpleMailMessage.setTo("xuxiaowei@xuxiaowei.com.cn");
		simpleMailMessage.setSubject("主题");
		simpleMailMessage.setText("内容");
		javaMailSender.send(simpleMailMessage);
	}

}
