package ma.chatapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {


// Create a KafkaAdmin bean responsible for managing topics
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9093"); // Kafka address
        return new KafkaAdmin(configs);
    }


    // Define a NewTopic bean for creating a topic programmatically
    @Bean
    public NewTopic chatRoomTopic() {
        return TopicBuilder.name("chat-room")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
