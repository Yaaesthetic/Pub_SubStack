package ma.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Enable a simple memory-based message broker with a prefix for sending messages
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register a WebSocket endpoint for the client to connect to
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
//
// /ws: This is the WebSocket endpoint that the client will connect to.
// /topic: This is a simple in-memory broker to which the back-end will broadcast messages.
// /app: This is the prefix for client-sent messages that will be routed to message-handling methods on the server.
