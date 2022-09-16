package cloud.xuxiaowei.user.service;

import cloud.xuxiaowei.system.bo.ForgetBo;
import cloud.xuxiaowei.system.service.IUsersService;
import cloud.xuxiaowei.system.vo.ForgetVo;
import cloud.xuxiaowei.system.vo.UsersVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户表 服务类 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@SpringBootTest
class IUsersServiceTests {

	@Autowired
	private IUsersService usersService;

	/**
	 * 根据 用户名/手机号码/绑定邮箱 查询绑定的 手机号码/绑定邮箱（已脱敏的，使用 Getter 进行脱敏）
	 */
	@Test
	void getForgetVoByForgetBo() {
		ForgetBo forgetBo = new ForgetBo();
		// forgetBo.setUsername("xuxiaowei");
		// forgetBo.setUsername("18812345678");
		forgetBo.setUsername("xuxiaowei@xuxiaowei.com.cn");
		ForgetVo forgetVo = usersService.getForgetVoByForgetBo(forgetBo);
		log.info(String.valueOf(forgetVo));
	}

	/**
	 * 根据 用户名 查询用户信息、性别、区域地址及权限
	 */
	@Test
	void getUsersVoByUsername() throws JsonProcessingException {
		UsersVo usersVo = usersService.getUsersVoByUsername("xuxiaowei");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
		String value = objectWriter.writeValueAsString(usersVo);
		log.info(value);
	}

}
