package com.scaler.harsh.notificationservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserEventConsumer {

    // Mock only — no real SES/SMTP integration. This just renders what would be sent,
    // so the message flow (Kafka -> Notification Service) is real even though delivery isn't.
    @KafkaListener(topics = "user-registered", groupId = "notification-service-group")
    public void consume(String message) {
        System.out.println("================ WELCOME EMAIL SENT (mock) ================");
        System.out.println("Timestamp: " + LocalDateTime.now());
        System.out.println(message);
        System.out.println("=============================================================");
    }
}