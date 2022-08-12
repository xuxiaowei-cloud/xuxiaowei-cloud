package cloud.xuxiaowei.gateway.filter;

import cloud.xuxiaowei.core.properties.CloudAesProperties;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.ResponseEncrypt;
import cn.hutool.crypto.symmetric.AES;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

/**
 * 请求体 Body 解密 过滤器
 *
 * @author xuxiaowei
 * @see ServerHttpRequestDecorator
 * @see <a href=
 * "https://stackoverflow.com/questions/66822340/spring-webflux-security-and-request-body">spring-webflux-security-and-request-body</a>
 * @since 0.0.1
 */
@Slf4j
@Component
public class BodyDecryptGlobalFilter implements GlobalFilter, Ordered {

	/**
	 * 最低优先级（最大值）：0
	 * <p>
	 * 大于 0 无效
	 */
	public static final int ORDERED = Ordered.HIGHEST_PRECEDENCE + 1020000;

	private CloudAesProperties cloudAesProperties;

	@Autowired
	public void setCloudAesProperties(CloudAesProperties cloudAesProperties) {
		this.cloudAesProperties = cloudAesProperties;
	}

	@Setter
	private int order = ORDERED;

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {

			@NonNull
			@Override
			public Flux<DataBuffer> getBody() {
				return super.getBody().buffer().map(body -> {

					DataBuffer join = exchange.getResponse().bufferFactory().join(body);
					byte[] bytes = new byte[join.readableByteCount()];
					join.read(bytes);
					DataBufferUtils.release(join);

					HttpHeaders headers = exchange.getRequest().getHeaders();
					MediaType contentType = headers.getContentType();

					if (MediaType.APPLICATION_JSON.includes(contentType)) {
						// 请求数据为JSON，可以解密

						// 接口请求中的加密方式（版本）
						String encrypt = headers.getFirst(Constant.ENCRYPT);
						if (StringUtils.hasText(encrypt)) {
							// 存在：请求中的加密方式（版本）

							// 默认秘钥
							byte[] keyBytes = cloudAesProperties.getDefaultKey().getBytes();
							// 默认偏移量
							byte[] ivBytes = cloudAesProperties.getDefaultIv().getBytes();

							// 响应中的客户ID
							String clientId = headers.getFirst(OAuth2TokenIntrospectionClaimNames.CLIENT_ID);
							if (StringUtils.hasText(clientId)) {
								// 客户ID存在

								List<CloudAesProperties.Aes> aesList = cloudAesProperties.getList();
								// 遍历客户AES配置
								for (CloudAesProperties.Aes aesProperties : aesList) {
									if (clientId.equals(aesProperties.getClientId())) {
										// 匹配到客户的秘钥配置
										// 使用客户的秘钥配置
										keyBytes = aesProperties.getKey().getBytes();
										ivBytes = aesProperties.getIv().getBytes();
									}
								}
							}

							// 匹配枚举
							ResponseEncrypt.AesVersion version = ResponseEncrypt.AesVersion.version(encrypt);

							if (version == null) {
								// 未匹配到枚举，请求体不处理
								return exchange.getResponse().bufferFactory().wrap(bytes);
							}
							else {
								switch (version) {
								case V1:
									return v1(exchange, bytes, keyBytes, ivBytes);
								case V0:
									// 加密方式（版本）为 V0 时，使用 V0，与未匹配时，采用相同的方式
									// 故：此处使用 switch case 的穿透效果
								default:
									return exchange.getResponse().bufferFactory().wrap(bytes);
								}
							}

						}
						else {
							// 不存在：请求中的加密方式（版本），请求体不处理
							return exchange.getResponse().bufferFactory().wrap(bytes);
						}
					}
					else {
						// 请求数据不是JSON，不进行解密，直接返回数据
						return exchange.getResponse().bufferFactory().wrap(bytes);
					}
				});
			}

			@NonNull
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.addAll(super.getHeaders());
				// 移除请求体长度，防止流读取时长度不匹配
				headers.remove(HttpHeaders.CONTENT_LENGTH);
				return headers;
			}
		};

		return chain.filter(exchange.mutate().request(decorator).build());
	}

	/**
	 * 解密方式（版本）V1
	 * @param exchange 服务器网络交换
	 * @param bytes 请求
	 * @param keyBytes 秘钥
	 * @param ivBytes 偏移量
	 * @return 返回解密后的数据
	 */
	private DataBuffer v1(ServerWebExchange exchange, byte[] bytes, byte[] keyBytes, byte[] ivBytes) {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		ResponseEncrypt.AesVersion aesVersion = ResponseEncrypt.AesVersion.V1;

		AES aes = new AES(aesVersion.mode, aesVersion.padding, keyBytes, ivBytes);

		log.debug("解密前 body：{}", new String(bytes));

		ResponseEncrypt responseEncrypt;
		try {
			responseEncrypt = objectMapper.readValue(bytes, ResponseEncrypt.class);
		}
		catch (IOException e) {
			log.error("body 转对象 ResponseEncrypt 失败", e);
			throw new RuntimeException(e);
		}

		String ciphertext = responseEncrypt.getCiphertext();

		byte[] decrypt = aes.decryptStr(ciphertext).getBytes();

		log.debug("解密后 body：{}", new String(decrypt));

		return exchange.getResponse().bufferFactory().wrap(decrypt);
	}

}
