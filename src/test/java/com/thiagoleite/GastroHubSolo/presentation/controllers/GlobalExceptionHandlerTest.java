package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.exceptions.AuthenticationException;
import com.thiagoleite.GastroHubSolo.presentation.exceptions.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenHandleAuthenticationException_thenReturnUnauthorized() {
        // Given
        String errorMessage = "Invalid credentials";
        AuthenticationException exception = new AuthenticationException(errorMessage);

        // When
        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleAuthenticationException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Erro de autenticação", responseBody.get("error"));
        assertEquals(errorMessage, responseBody.get("message"));
        assertNotNull(responseBody.get("timestamp"));
    }
}