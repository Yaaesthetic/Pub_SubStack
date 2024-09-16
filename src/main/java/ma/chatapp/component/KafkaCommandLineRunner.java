package ma.chatapp.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class KafkaCommandLineRunner implements CommandLineRunner {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaCommandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String topic = "chat-room"; // Update with your Kafka topic
        String message = "Hello Kafka!"; // Message to be sent

        kafkaTemplate.send(topic, message);
        System.out.println("Message sent to Kafka topic: " + topic);
    }
}
