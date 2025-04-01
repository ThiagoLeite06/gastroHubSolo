package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    private User user;
    private UserOutput userOutput;
    private List<User> userList;
    private List<UserOutput> userOutputList;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");

        userOutput = new UserOutput();
        userOutput.setId(1L);
        userOutput.setName("Test User");
        userOutput.setEmail("test@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Another User");
        user2.setEmail("another@example.com");

        UserOutput userOutput2 = new UserOutput();
        userOutput2.setId(2L);
        userOutput2.setName("Another User");
        userOutput2.setEmail("another@example.com");

        userList = Arrays.asList(user, user2);
        userOutputList = Arrays.asList(userOutput, userOutput2);
    }

    @Test
    void whenGetUserById_thenReturnUserOutput() {
        // Given
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.toOutput(any(User.class))).thenReturn(userOutput);

        // When
        UserOutput result = getUserUseCase.getById(1L);

        // Then
        assertNotNull(result);
        // Verificar atributos individualmente em vez de usar equals
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
        verify(userRepository).findById(1L);
        verify(userMapper).toOutput(user);
    }

    @Test
    void whenGetNonExistingUserById_thenThrowException() {
        // Given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            getUserUseCase.getById(1L);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(userRepository).findById(1L);
        verify(userMapper, never()).toOutput(any(User.class));
    }

    @Test
    void whenGetAllUsers_thenReturnAllUserOutputs() {
        // Given
        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.toOutput(userList.get(0))).thenReturn(userOutputList.get(0));
        when(userMapper.toOutput(userList.get(1))).thenReturn(userOutputList.get(1));

        // When
        List<UserOutput> result = getUserUseCase.getAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(userList.get(0).getId(), result.get(0).getId());
        assertEquals(userList.get(1).getId(), result.get(1).getId());
        verify(userRepository).findAll();
        verify(userMapper, times(2)).toOutput(any(User.class));
    }
}