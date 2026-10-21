package com.scaler.harsh.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CartEventConsumer {

    @KafkaListener(topics = "cart-item-added", groupId = "notification-service-group")
    public void consume(String message) {
        System.out.println("================ CART ACTIVITY (mock) ================");
        System.out.println("Timestamp: " + LocalDateTime.now());
        System.out.println(message);
        System.out.println("========================================================");
    }
}