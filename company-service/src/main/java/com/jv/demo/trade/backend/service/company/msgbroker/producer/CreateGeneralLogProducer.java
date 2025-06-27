package com.jv.demo.trade.backend.service.company.msgbroker.producer;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.random.RandomGenerator;

@Service
@EnableAsync
@CommonsLog
public class CreateGeneralLogProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    public CreateGeneralLogProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void sendMessage(String topic, String message) {

        CompletableFuture<SendResult<String, String>> compFut = kafkaTemplate.send(topic, "Key:" + message.hashCode() + RandomGenerator.getDefault().nextInt(), message);
        do {
            try {
                if(compFut.isDone()) {
                    SendResult<String, String> result = compFut.get();
                    log.info("Send Message Completed");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }while(!compFut.isDone() && !compFut.isCancelled());
    }
}
