package cloud.xuxiaowei.websocket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * {@link WebSocketHandler} 工厂
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class WebSocketHandlerDecoratorFactoryConfiguration implements WebSocketHandlerDecoratorFactory {

    @Override
    @NonNull
    public WebSocketHandler decorate(@NonNull WebSocketHandler handler) {
        return new WebSocketHandlerDecoratorConfiguration(handler);
    }

}
