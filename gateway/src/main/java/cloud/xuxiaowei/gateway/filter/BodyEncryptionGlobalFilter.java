package cloud.xuxiaowei.gateway.filter;

import cloud.xuxiaowei.core.properties.CloudAesProperties;
import cloud.xuxiaowei.utils.Constant;
import cloud.xuxiaowei.utils.ResponseEncrypt;
import cn.hutool.crypto.symmetric.AES;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

/**
 * 响应 Body 加密 过滤器
 *
 * @author xuxiaowei
 * @see ServerHttpResponseDecorator
 * @since 0.0.1
 */
@Slf4j
@Component
public class BodyEncryptionGlobalFilter implements GlobalFilter, Ordered {

	/**
	 * 最低优先级（最大值）：0
	 * <p>
	 * 大于 0 无效
	 */
	public static final int ORDERED = Ordered.HIGHEST_PRECEDENCE + 1010000;

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

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		ServerHttpResponseDecorator decorator = new ServerHttpResponseDecorator(exchange.getResponse()) {

			@NonNull
			@Override
			public Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {

				HttpHeaders headers = exchange.getResponse().getHeaders();
				MediaType contentType = headers.getContentType();

				if (MediaType.APPLICATION_JSON.includes(contentType)) {
					// 响应数据为JSON，可以加密

					ResponseEncrypt.AesVersion aesVersion = ResponseEncrypt.AesVersion.V1;

					// 设置加密版本
					headers.add(Constant.ENCRYPT, aesVersion.version);
					// 暴露响应头（否则 axios 将无法获取）
					headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, Constant.ENCRYPT);

					@SuppressWarnings("unchecked")
					Flux<? extends DataBuffer> fluxDataBuffer = (Flux<? extends DataBuffer>) body;

					return super.writeWith(fluxDataBuffer.buffer().map(dataBuffer -> {

						DataBuffer join = exchange.getResponse().bufferFactory().join(dataBuffer);

						byte[] bytes = new byte[join.readableByteCount()];
						join.read(bytes);
						DataBufferUtils.release(join);

						String originalText = new String(bytes);

						log.debug("加密前 body：{}", originalText);

						AES aes = new AES(aesVersion.mode, aesVersion.padding,
								cloudAesProperties.getDefaultKey().getBytes(),
								cloudAesProperties.getDefaultIv().getBytes());

						String encryptBase64 = aes.encryptBase64(originalText);

						log.debug("加密后 body：{}", encryptBase64);

						ResponseEncrypt responseEncrypt = new ResponseEncrypt();
						responseEncrypt.setCiphertext(encryptBase64);

						byte[] responseBytes;
						try {
							String value = objectMapper.writeValueAsString(responseEncrypt);
							responseBytes = value.getBytes();
						}
						catch (JsonProcessingException e) {
							throw new RuntimeException(e);
						}

						return exchange.getResponse().bufferFactory().wrap(responseBytes);
					}));
				}

				// 响应数据不是JSON，不进行加密，直接返回数据
				return exchange.getResponse().writeWith(body);
			}

		};

		return chain.filter(exchange.mutate().response(decorator).build());
	}

}
