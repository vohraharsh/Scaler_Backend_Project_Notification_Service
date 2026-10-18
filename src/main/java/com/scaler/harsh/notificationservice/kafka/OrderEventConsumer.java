package com.scaler.harsh.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    @KafkaListener(topics = "order-placed", groupId = "notification-service-group")
    public void consume(String message) {
        System.out.println("Notification Service received event: " + message);
        System.out.println("Confirmation email sent: " + message);
    }
}