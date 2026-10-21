package com.scaler.harsh.notificationservice.kafka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserEventConsumerTest {

    private final UserEventConsumer userEventConsumer = new UserEventConsumer();

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void setUp() {
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void consume_normalMessage_printsWelcomeEmailBannerAndMessage() {
        userEventConsumer.consume("Welcome message for user 6");

        String output = capturedOut.toString();
        assertTrue(output.contains("WELCOME EMAIL SENT (mock)"));
        assertTrue(output.contains("Welcome message for user 6"));
        assertTrue(output.contains("Timestamp:"));
    }

    @Test
    void consume_nullMessage_doesNotThrow() {
        assertDoesNotThrow(() -> userEventConsumer.consume(null));

        String output = capturedOut.toString();
        assertTrue(output.contains("WELCOME EMAIL SENT (mock)"));
        assertTrue(output.contains("null"));
    }

    @Test
    void consume_emptyMessage_doesNotThrowAndStillPrintsBanner() {
        assertDoesNotThrow(() -> userEventConsumer.consume(""));

        String output = capturedOut.toString();
        assertTrue(output.contains("WELCOME EMAIL SENT (mock)"));
    }
}