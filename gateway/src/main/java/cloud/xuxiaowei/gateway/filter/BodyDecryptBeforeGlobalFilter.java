package cloud.xuxiaowei.gateway.filter;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 请求体 Body 解密之前 过滤器
 * <p>
 * 该过滤器的目的是为了
 *
 * @author xuxiaowei
 * @see ServerHttpRequestDecorator
 * @since 0.0.1
 */
@Slf4j
@Component
public class BodyDecryptBeforeGlobalFilter implements GlobalFilter, Ordered {

	public static final String BODY_DECRYPT_BYTES = "bodyDecryptBytes";

	/**
	 * 最低优先级（最大值）：0
	 * <p>
	 * 大于 0 无效
	 */
	public static final int ORDERED = Ordered.HIGHEST_PRECEDENCE + 1020000;

	@Setter
	private int order = ORDERED;

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		MediaType contentType = exchange.getRequest().getHeaders().getContentType();

		if (MediaType.APPLICATION_JSON.includes(contentType)) {
			// 请求数据为JSON，可以解密
			// @formatter:off
			return DataBufferUtils.join(exchange.getRequest().getBody())
					.map(dataBuffer -> {
						byte[] bytes = new byte[dataBuffer.readableByteCount()];
						dataBuffer.read(bytes);
						DataBufferUtils.release(dataBuffer);
						return bytes;
					}).defaultIfEmpty(new byte[0])
					.doOnNext(bytes -> {
						// 暂存请求体，方便后面使用
						exchange.getAttributes().put(BODY_DECRYPT_BYTES, bytes);
					})
					.then(chain.filter(exchange));
			// @formatter:on
		}
		else {
			return chain.filter(exchange);
		}

	}

}
