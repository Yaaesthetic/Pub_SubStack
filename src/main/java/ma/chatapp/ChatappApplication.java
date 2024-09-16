package ma.chatapp;

import ma.chatapp.service.KafkaProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ChatappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatappApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String,String> kafkaProducer) {
        return args -> kafkaProducer.send("chat-room","Hello Word");
    }

}
