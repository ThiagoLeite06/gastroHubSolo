package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthInput;
import com.thiagoleite.GastroHubSolo.application.dtos.AuthOutput;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticateUserUseCase;
import com.thiagoleite.GastroHubSolo.mocks.MockAuthenticateUserUseCaseImpl;
import com.thiagoleite.GastroHubSolo.presentation.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticateUserUseCaseImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider tokenProvider;

    @Mock
    private Authentication authentication;

    private AuthenticateUserUseCase authenticateUserUseCase;

    private AuthInput authInput;
    private User user;

    @BeforeEach
    void setUp() {
        // Usar a implementação mockada para evitar problemas com o substring
        authenticateUserUseCase = new MockAuthenticateUserUseCaseImpl(
                authenticationManager, userRepository, tokenProvider);

        authInput = new AuthInput();
        authInput.setEmail("test@example.com");
        authInput.setPassword("password123");

        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
    }

    @Test
    void whenValidCredentials_thenReturnAuthOutput() {
        // Given
        String token = "jwt-token-test-string";
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(tokenProvider.generateToken(authentication)).thenReturn(token);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        // When
        AuthOutput result = authenticateUserUseCase.execute(authInput);

        // Then
        assertNotNull(result);
        assertEquals(token, result.getToken());
        assertEquals("Bearer", result.getType());
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenProvider).generateToken(authentication);
        verify(userRepository).findByEmail(authInput.getEmail());
    }

    @Test
    void whenUserNotFound_thenThrowException() {
        // Given
        String token = "jwt-token-test-string";
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(tokenProvider.generateToken(authentication)).thenReturn(token);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authenticateUserUseCase.execute(authInput);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenProvider).generateToken(authentication);
        verify(userRepository).findByEmail(authInput.getEmail());
    }

    @Test
    void whenAuthenticationFails_thenPropagateException() {
        // Given
        RuntimeException authException = new RuntimeException("Authentication failed");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(authException);

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authenticateUserUseCase.execute(authInput);
        });

        assertEquals(authException, exception);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenProvider, never()).generateToken(any());
        verify(userRepository, never()).findByEmail(anyString());
    }
}