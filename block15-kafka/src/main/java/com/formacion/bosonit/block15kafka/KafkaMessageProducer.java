package com.formacion.bosonit.block15kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Profile("provider")
public class KafkaMessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic,String message) {

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.whenComplete((result, ex) -> {
            if(ex == null) {
                System.out.println("Mensaje enviado: " + message + " con offset: " + result.getRecordMetadata().offset() + ".");
            }
            else {
                System.out.println("No ha sido posible enviar el mensaje : " + message + " debido a : " + ex.getMessage() + ".");
            }
        });
    }
}
