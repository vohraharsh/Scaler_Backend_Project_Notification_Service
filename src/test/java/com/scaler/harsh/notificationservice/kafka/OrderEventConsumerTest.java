package com.scaler.harsh.notificationservice.kafka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderEventConsumerTest {

    private final OrderEventConsumer orderEventConsumer = new OrderEventConsumer();

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
    void consume_normalMessage_printsEmailSentBannerAndMessage() {
        orderEventConsumer.consume("Order #123 placed by user 5");

        String output = capturedOut.toString();
        assertTrue(output.contains("EMAIL SENT (mock)"));
        assertTrue(output.contains("Order #123 placed by user 5"));
        assertTrue(output.contains("Timestamp:"));
    }

    @Test
    void consume_nullMessage_doesNotThrow() {
        assertDoesNotThrow(() -> orderEventConsumer.consume(null));

        String output = capturedOut.toString();
        assertTrue(output.contains("EMAIL SENT (mock)"));
        assertTrue(output.contains("null"));
    }

    @Test
    void consume_emptyMessage_doesNotThrowAndStillPrintsBanner() {
        assertDoesNotThrow(() -> orderEventConsumer.consume(""));

        String output = capturedOut.toString();
        assertTrue(output.contains("EMAIL SENT (mock)"));
    }
}