package com.springboot.everefing.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @Test
    void handlerProductAPIException_ShouldReturnErrorResponse() {
        // Arrange
        ProductAPIException productAPIException = new ProductAPIException(HttpStatus.BAD_REQUEST, "Test error message");
        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("Test web request description");

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        // Act
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.handlerProductAPIException(productAPIException, webRequest);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Test error message", responseEntity.getBody().getMessage());
        assertEquals("Test web request description", responseEntity.getBody().getDetails());
        assertNotNull(responseEntity.getBody().getTimeStamp());
    }
}
