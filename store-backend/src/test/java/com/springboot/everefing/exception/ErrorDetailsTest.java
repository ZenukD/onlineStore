package com.springboot.everefing.exception;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDetailsTest {

    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        String message = "Test error message";
        String details = "Test error details";

        // Act
        ErrorDetails errorDetails = new ErrorDetails(now, message, details);

        // Assert
        assertEquals(now, errorDetails.getTimeStamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

    @Test
    void gettersAndSetters_ShouldWorkAsExpected() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        String message = "Test error message";
        String details = "Test error details";

        // Act
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimeStamp(now);
        errorDetails.setMessage(message);
        errorDetails.setDetails(details);

        // Assert
        assertEquals(now, errorDetails.getTimeStamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }
}
