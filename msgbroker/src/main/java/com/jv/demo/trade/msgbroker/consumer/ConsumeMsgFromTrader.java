package com.jv.demo.trade.msgbroker.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumeMsgFromTrader {
    @KafkaListener(topics = "topicMsgFromTrader", groupId = "msgFromTrader-group") // Replace "my-topic" with your topic name
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
