package com.formacion.bosonit.block15kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("listener")
public class KafkaMessageListener {

    @KafkaListener(topics = "${message.topic.name:alumno}", groupId = "${message.group.name:grupoAlumno}")
    public void listenTopic1(String message) {
        System.out.println("Recibido mensaje de MyTopic1 en el consumidor: " + message);
    }

    @KafkaListener(topics = "${message.topic.name2:alumno}", groupId = "${message.group.name:grupoAlumno}")
    public void listenTopic2(String message) {
        System.out.println("Recibido mensaje de MyTopic2 en el consumidor: " + message);
    }
}