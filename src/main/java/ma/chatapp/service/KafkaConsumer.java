package ma.chatapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "chat-room", groupId = "chat-group")
    public void listen(String message) {
        logger.info(String.format("#### -> Received message -> %s", message));
        System.out.println("Received message: " + message);
        // Here you can broadcast the message via WebSocket
    }
}
