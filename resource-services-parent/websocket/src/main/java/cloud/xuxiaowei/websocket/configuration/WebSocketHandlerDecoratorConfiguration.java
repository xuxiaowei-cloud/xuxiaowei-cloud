package cloud.xuxiaowei.websocket.configuration;

import cloud.xuxiaowei.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

/**
 * WebSocket 消息和生命周期事件的处理程序。
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
public class WebSocketHandlerDecoratorConfiguration extends WebSocketHandlerDecorator {

    public WebSocketHandlerDecoratorConfiguration(WebSocketHandler delegate) {
        super(delegate);
    }

    /**
     * 在WebSocket协商成功并且WebSocket连接打开并准备好使用后调用。
     *
     * @param session 发送WebSocket消息：{@link TextMessage}或{@link BinaryMessage}。
     *                <p><strong>注意：</strong>底层标准WebSocket会话（JSR-356）不允许并发发送。
     *                因此必须同步发送。
     *                为了确保这一点，一个选项是使用{@link ConcurrentWebSocketSessionDecorator}包装{@link WebSocketSession}。
     * @throws Exception 此方法可以处理或传播异常; 有关详细信息，请参阅类级Javadoc。
     */
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        // 客户端与服务器端建立连接后，此处记录谁上线了
        // 需要解析授权Token
        String accessToken = UrlUtils.getAccessToken(session.getUri());

//        log.info("上线: " + name);

    }

    /**
     * 在新的WebSocket消息到达时调用。
     *
     * @throws Exception 此方法可以处理或传播异常; 有关详细信息，请参阅类级Javadoc。
     */
    @Override
    public void handleMessage(@NonNull WebSocketSession session, @NonNull WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);

        // 需要解析授权Token
        String accessToken = UrlUtils.getAccessToken(session.getUri());

//        log.info("接收到用户: " + name + " 的消息");
        log.info("消息内容：\n" + message.getPayload());

    }

    /**
     * 处理底层WebSocket消息传输中的错误。
     *
     * @throws Exception 此方法可以处理或传播异常; 有关详细信息，请参阅类级Javadoc。
     */
    @Override
    public void handleTransportError(@NonNull WebSocketSession session, @NonNull Throwable exception) throws Exception {
        super.handleTransportError(session, exception);

        // 需要解析授权Token
        String accessToken = UrlUtils.getAccessToken(session.getUri());

//        log.info("接收到用户: " + name + " 的异常");
        log.info("异常信息：" + exception.getMessage());

    }

    /**
     * WebSocket连接被任何一方关闭后，或者在发生传输错误后调用。
     * 虽然会话在技术上可能仍然是开放的，但取决于底层实现，此时不鼓励发送消息，并且很可能不会成功。
     *
     * @throws Exception 此方法可以处理或传播异常; 有关详细信息，请参阅类级Javadoc。
     */
    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus closeStatus) throws Exception {
        super.afterConnectionClosed(session, closeStatus);

        // 客户端与服务器端断开连接后，此处记录谁下线了
        // 需要解析授权Token
        String accessToken = UrlUtils.getAccessToken(session.getUri());

//        log.info("离线: " + name);

    }

}
