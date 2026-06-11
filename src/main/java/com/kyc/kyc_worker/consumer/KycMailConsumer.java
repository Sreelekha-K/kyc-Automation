package com.kyc.kyc_worker.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KycMailConsumer {

    @KafkaListener(
            topics = "kyc-mail-notification",
            groupId = "kyc-worker-group"
    )
    public void consume(String message) {

        System.out.println("=================================");
        System.out.println("KAFKA MESSAGE RECEIVED");
        System.out.println(message);
        System.out.println("=================================");
    }
}