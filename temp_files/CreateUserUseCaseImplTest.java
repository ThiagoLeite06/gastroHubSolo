package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.UserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    private UserInput userInput;
    private User user;
    private UserType userType;
    private UserOutput userOutput;

    @BeforeEach
    void setUp() {
        userInput = new UserInput();
        userInput.setName("Test User");
        userInput.setEmail("test@example.com");
        userInput.setPassword("password123");
        userInput.setAddress("Test Address");
        userInput.setUserTypeId(1L);

        userType = new UserType();
        userType.setId(1L);
        userType.setName("Cliente");

        user = new User();
        user.setName(userInput.getName());
        user.setEmail(userInput.getEmail());
        user.setPassword("encodedPassword");
        user.setAddress(userInput.getAddress());
        user.setUserType(userType);
        user.setRole("USER");

        userOutput = new UserOutput();
        userOutput.setId(1L);
        userOutput.setName(userInput.getName());
        userOutput.setEmail(userInput.getEmail());
        userOutput.setAddress(userInput.getAddress());
    }

    @Test
    void whenCreateUser_thenReturnUserOutput() {
        // Given
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.of(userType));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toOutput(any(User.class))).thenReturn(userOutput);

        // When
        UserOutput result = createUserUseCase.execute(userInput);

        // Then
        assertNotNull(result);
        assertEquals(userInput.getName(), result.getName());
        assertEquals(userInput.getEmail(), result.getEmail());
        verify(userRepository).existsByEmail(userInput.getEmail());
        verify(userTypeRepository).findById(userInput.getUserTypeId());
        verify(passwordEncoder).encode(userInput.getPassword());
        verify(userRepository).save(any(User.class));
        verify(userMapper).toOutput(any(User.class));
    }

    @Test
    void whenCreateUserWithExistingEmail_thenThrowException() {
        // Given
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            createUserUseCase.execute(userInput);
        });

        assertEquals("Email já está em uso", exception.getMessage());
        verify(userRepository).existsByEmail(userInput.getEmail());
        verify(userTypeRepository, never()).findById(anyLong());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void whenCreateUserWithNonExistingUserType_thenThrowException() {
        // Given
        when(userRepository.existsByEmail(anyString())).thenReturn(false);

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            createUserUseCase.execute(userInput);
        });

        assertEquals("Tipo de usuário não encontrado", exception.getMessage());
        verify(userRepository).existsByEmail(userInput.getEmail());
        verify(userTypeRepository).findById(userInput.getUserTypeId());

        verify(userRepository, never()).save(any(User.class));
    }
}

