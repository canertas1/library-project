package library.demo.library.kafkaConfig;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {


    @KafkaListener(topics = "caner", groupId = "groupId")
    void listener(String data){
        System.out.println("Data geldi : " +data);
    }



}
