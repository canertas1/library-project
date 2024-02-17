package library.demo.library.kafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic bookTopic1(){

        return TopicBuilder.name("bookStockDecrease")
                .build();
    }

    @Bean
    public NewTopic bookTopic(){

        return TopicBuilder.name("bookStockIncrease")
                .build();
    }




}
