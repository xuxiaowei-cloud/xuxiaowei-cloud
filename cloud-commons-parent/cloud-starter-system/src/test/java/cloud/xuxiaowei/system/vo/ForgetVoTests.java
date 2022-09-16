package cloud.xuxiaowei.system.vo;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 忘记密码 响应 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class ForgetVoTests {

	@Test
	void ma() throws JsonProcessingException {
		ForgetVo forgetVo = new ForgetVo();
		forgetVo.setEmail("xuxiaowei@xuxiaowei.com.cn");
		forgetVo.setPhone("18812345678");
		log.info(String.valueOf(forgetVo));

		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = objectMapper.writeValueAsString(forgetVo);
		log.info(writeValueAsString);

		String toJSONString = JSON.toJSONString(forgetVo);
		log.info(toJSONString);

		String toJsonStr = JSONUtil.toJsonStr(forgetVo);
		log.info(toJsonStr);
	}

}
