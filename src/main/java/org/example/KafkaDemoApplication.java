package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.PrimitiveIterator;

@SpringBootApplication
public class KafkaDemoApplication implements ApplicationRunner {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String msg){
        kafkaTemplate.send("tutorial-point",msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class,args);
    }

    @KafkaListener(topics="tutorial-point",groupId = "group-id")
    public void listen(String message){
        System.out.println("Received Message in group - group-id: "+message);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendMessage("Hi Welcome to Spring For Apache Kafka.");
    }

}