package cloud.xuxiaowei.passport.entity;

import cloud.xuxiaowei.core.properties.CloudClientProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 客户表 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
class Oauth2RegisteredClientTests {

	@Test
	void loadAsConfig() throws JsonProcessingException {

		Oauth2RegisteredClient oauth2RegisteredClient = new Oauth2RegisteredClient();
		// @formatter:off
		oauth2RegisteredClient.setConfig("" +
				"# 客户端 配置\n"
				+ "cloud:\n"
				+ "  security:\n"
				+ "    # 启动RSA密码加密\n"
				+ "    enabled-rsa: false\n"
				+ "    passport-domain: http://passport.example.xuxiaowei.cloud:1411\n"
				+ "  client:\n"
				+ "    consent-page: http://passport.example.xuxiaowei.cloud:1101/passport/oauth2.1/authorize\n"
				+ "    list:\n"
				+ "      - client-id: xuxiaowei_client_id\n"
				+ "        client-secret: xuxiaowei_client_secret\n"
				+ "        authorize-uri: http://gateway.example.xuxiaowei.cloud:1101/passport/oauth2/authorize\n"
				+ "        check-token-uri: http://gateway.example.xuxiaowei.cloud:1101/passport/oauth2/check_token?token=\n"
				+ "        redirect-uri-prefix: http://gateway.example.xuxiaowei.cloud:1101/passport/code\n"
				+ "        access-token-uri: http://passport/oauth2/token\n"
				+ "        scope: snsapi_base\n"
				+ "        state-name: STATE_NAME\n"
				+ "        home-page: http://example.xuxiaowei.cloud:1001/#/\n"
				+ "      - client-id: tenant_id_1_client_id\n"
				+ "        client-secret: tenant_id_1_client_secret\n"
				+ "        authorize-uri: http://gateway.example.xuxiaowei.cloud:1101/passport/oauth2/authorize\n"
				+ "        check-token-uri: http://gateway.example.xuxiaowei.cloud:1101/passport/oauth2/check_token?token=\n"
				+ "        redirect-uri-prefix: http://gateway.example.xuxiaowei.cloud:1101/passport/code\n"
				+ "        access-token-uri: http://passport/oauth2/token\n"
				+ "        scope: snsapi_base\n"
				+ "        state-name: STATE_NAME\n"
				+ "        home-page: http://example.xuxiaowei.cloud:1001/#/\n"
				+ "      - client-id: tenant_id_2_client_id\n"
				+ "        client-secret: tenant_id_2_client_secret\n"
				+ "        authorize-uri: http://gateway.example.xuxiaowei.cloud:1101/passport/oauth2/authorize\n"
				+ "        check-token-uri: http://gateway.example.xuxiaowei.cloud:1101/passport/oauth2/check_token?token=\n"
				+ "        redirect-uri-prefix: http://gateway.example.xuxiaowei.cloud:1101/passport/code\n"
				+ "        access-token-uri: http://passport/oauth2/token\n"
				+ "        scope: snsapi_base\n"
				+ "        state-name: STATE_NAME\n"
				+ "        home-page: http://example.xuxiaowei.cloud:1001/#/\n");
		// @formatter:off

		CloudClientProperties.Client client = oauth2RegisteredClient.loadAsConfig();

		ObjectMapper objectMapper = new ObjectMapper();
		String value = objectMapper.writeValueAsString(client);
		log.info(value);
	}

}
