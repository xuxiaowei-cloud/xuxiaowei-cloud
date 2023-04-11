package cloud.xuxiaowei.user.service;

import cloud.xuxiaowei.system.service.IUsersService;
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
	 * 根据 用户ID 查询用户信息、性别、区域地址及权限
	 */
	@Test
	void getUsersVoByUsersId() throws JsonProcessingException {
		UsersVo usersVo = usersService.getUsersVoByUsersId(1L);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
		String value = objectWriter.writeValueAsString(usersVo);
		log.info(value);
	}

}
