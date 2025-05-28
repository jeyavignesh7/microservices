package com.jv.demo.trade.msgbroker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateMsgFromTrader {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CreateMsgFromTrader(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
