package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void whenDeleteUser_thenRepositoryCalledWithCorrectId() {
        // Given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        // When
        deleteUserUseCase.execute(userId);

        // Then
        verify(userRepository).findById(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void whenDeleteNonExistingUser_thenThrowException() {
        // Given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            deleteUserUseCase.execute(userId);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findById(userId);
        verify(userRepository, never()).deleteById(anyLong());
    }
}
