package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.UpdateUserInput;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    private UpdateUserInput updateUserInput;
    private User existingUser;
    private UserType userType;
    private UserOutput userOutput;

    @BeforeEach
    void setUp() {
        updateUserInput = new UpdateUserInput();
        updateUserInput.setName("Updated User");
        updateUserInput.setEmail("updated@example.com");
        updateUserInput.setPassword("newpassword123");
        updateUserInput.setAddress("Updated Address");
        updateUserInput.setUserTypeId(2L);

        userType = new UserType();
        userType.setId(2L);
        userType.setName("Dono de Restaurante");

        existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Test User");
        existingUser.setEmail("test@example.com");
        existingUser.setPassword("encodedPassword");
        existingUser.setAddress("Test Address");
        existingUser.setRole("USER");

        userOutput = new UserOutput();
        userOutput.setId(1L);
        userOutput.setName(updateUserInput.getName());
        userOutput.setEmail(updateUserInput.getEmail());
        userOutput.setAddress(updateUserInput.getAddress());
    }

    @Test
    void whenUpdateUser_thenReturnUpdatedUserOutput() {
        // Given
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.of(userType));
        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(existingUser);
        when(userMapper.toOutput(any(User.class))).thenReturn(userOutput);

        // When
        UserOutput result = updateUserUseCase.execute(1L, updateUserInput);

        // Then
        assertNotNull(result);
        assertEquals(updateUserInput.getName(), result.getName());
        assertEquals(updateUserInput.getEmail(), result.getEmail());
        verify(userRepository).findById(1L);
        verify(userTypeRepository).findById(updateUserInput.getUserTypeId());
        verify(passwordEncoder).encode(updateUserInput.getPassword());
        verify(userRepository).save(any(User.class));
        verify(userMapper).toOutput(any(User.class));
    }

    @Test
    void whenUpdateUserWithNonExistingId_thenThrowException() {
        // Given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            updateUserUseCase.execute(1L, updateUserInput);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findById(1L);
        verify(userTypeRepository, never()).findById(anyLong());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void whenUpdateUserWithNonExistingUserType_thenThrowException() {
        // Given
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));

        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            updateUserUseCase.execute(1L, updateUserInput);
        });

        assertEquals("Tipo de usuário não encontrado", exception.getMessage());
        verify(userRepository).findById(1L);
        verify(userTypeRepository).findById(updateUserInput.getUserTypeId());

        verify(userRepository, never()).save(any(User.class));
    }
}