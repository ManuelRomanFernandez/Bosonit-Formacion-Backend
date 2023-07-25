package com.formacion.bosonit.block15kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("provider")
public class KafkaController {
    @Autowired
    KafkaMessageProducer kafkaMessageProducer;

    @PostMapping("/add/{topic}")
    public String addTopic( @PathVariable String topic, @RequestBody  String body) throws InterruptedException {
        kafkaMessageProducer.sendMessage(topic,body);
        return "Mensaje enviado con exito";
    }
}
