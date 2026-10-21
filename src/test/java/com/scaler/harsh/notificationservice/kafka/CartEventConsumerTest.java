package com.scaler.harsh.notificationservice.kafka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartEventConsumerTest {

    private final CartEventConsumer cartEventConsumer = new CartEventConsumer();

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
    void consume_normalMessage_printsCartActivityBannerAndMessage() {
        cartEventConsumer.consume("Item added to cart 22 by user 3");

        String output = capturedOut.toString();
        assertTrue(output.contains("CART ACTIVITY (mock)"));
        assertTrue(output.contains("Item added to cart 22 by user 3"));
        assertTrue(output.contains("Timestamp:"));
    }

    @Test
    void consume_nullMessage_doesNotThrow() {
        assertDoesNotThrow(() -> cartEventConsumer.consume(null));

        String output = capturedOut.toString();
        assertTrue(output.contains("CART ACTIVITY (mock)"));
        assertTrue(output.contains("null"));
    }

    @Test
    void consume_emptyMessage_doesNotThrowAndStillPrintsBanner() {
        assertDoesNotThrow(() -> cartEventConsumer.consume(""));

        String output = capturedOut.toString();
        assertTrue(output.contains("CART ACTIVITY (mock)"));
    }
}