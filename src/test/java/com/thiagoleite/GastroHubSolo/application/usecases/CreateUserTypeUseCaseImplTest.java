package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserTypeUseCaseImplTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private CreateUserTypeUseCaseImpl createUserTypeUseCase;

    private UserType userType;

    @BeforeEach
    void setUp() {
        userType = new UserType();
        userType.setName("Test Type");
    }

    @Test
    void whenCreateUserType_thenReturnCreatedUserType() {
        // Given
        when(userTypeRepository.existsByName(anyString())).thenReturn(false);
        when(userTypeRepository.save(any(UserType.class))).thenReturn(userType);

        // When
        UserType result = createUserTypeUseCase.execute(userType);

        // Then
        assertNotNull(result);
        assertEquals(userType.getName(), result.getName());
        verify(userTypeRepository).existsByName(userType.getName());
        verify(userTypeRepository).save(any(UserType.class));
    }

    @Test
    void whenCreateUserTypeWithExistingName_thenThrowException() {
        // Given
        when(userTypeRepository.existsByName(anyString())).thenReturn(true);

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            createUserTypeUseCase.execute(userType);
        });

        assertEquals("Tipo de usuário já existe", exception.getMessage());
        verify(userTypeRepository).existsByName(userType.getName());
        verify(userTypeRepository, never()).save(any(UserType.class));
    }
}

@ExtendWith(MockitoExtension.class)
class UpdateUserTypeUseCaseImplTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private UpdateUserTypeUseCaseImpl updateUserTypeUseCase;

    private UserType userType;
    private UserType existingUserType;
    private Long userTypeId;

    @BeforeEach
    void setUp() {
        userTypeId = 1L;

        userType = new UserType();
        userType.setName("Updated Type");

        existingUserType = new UserType();
        existingUserType.setId(userTypeId);
        existingUserType.setName("Test Type");
        existingUserType.setCreatedAt(LocalDateTime.now());
        existingUserType.setLastUpdatedAt(LocalDateTime.now());
    }

    @Test
    void whenUpdateUserType_thenReturnUpdatedUserType() {
        // Given
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.of(existingUserType));
        when(userTypeRepository.existsByName(anyString())).thenReturn(false);
        when(userTypeRepository.save(any(UserType.class))).thenReturn(existingUserType);

        // When
        UserType result = updateUserTypeUseCase.execute(userTypeId, userType);

        // Then
        assertNotNull(result);
        assertEquals(userType.getName(), result.getName());
        verify(userTypeRepository).findById(userTypeId);
        verify(userTypeRepository).existsByName(userType.getName());
        verify(userTypeRepository).save(any(UserType.class));
    }

    @Test
    void whenUpdateUserTypeWithSameName_thenDoNotCheckExistsByName() {
        // Given
        existingUserType.setName(userType.getName());
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.of(existingUserType));
        when(userTypeRepository.save(any(UserType.class))).thenReturn(existingUserType);

        // When
        UserType result = updateUserTypeUseCase.execute(userTypeId, userType);

        // Then
        assertNotNull(result);
        assertEquals(userType.getName(), result.getName());
        verify(userTypeRepository).findById(userTypeId);
        verify(userTypeRepository, never()).existsByName(anyString());
        verify(userTypeRepository).save(any(UserType.class));
    }

    @Test
    void whenUpdateUserTypeWithExistingName_thenThrowException() {
        // Given
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.of(existingUserType));
        when(userTypeRepository.existsByName(anyString())).thenReturn(true);

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            updateUserTypeUseCase.execute(userTypeId, userType);
        });

        assertEquals("Já existe um tipo de usuário com este nome", exception.getMessage());
        verify(userTypeRepository).findById(userTypeId);
        verify(userTypeRepository).existsByName(userType.getName());
        verify(userTypeRepository, never()).save(any(UserType.class));
    }

    @Test
    void whenUpdateNonExistingUserType_thenThrowException() {
        // Given
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            updateUserTypeUseCase.execute(userTypeId, userType);
        });

        assertEquals("Tipo de usuário não encontrado", exception.getMessage());
        verify(userTypeRepository).findById(userTypeId);
        verify(userTypeRepository, never()).existsByName(anyString());
        verify(userTypeRepository, never()).save(any(UserType.class));
    }
}

@ExtendWith(MockitoExtension.class)
class GetUserTypeUseCaseImplTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private GetUserTypeUseCaseImpl getUserTypeUseCase;

    private UserType userType;
    private List<UserType> userTypeList;
    private Long userTypeId;

    @BeforeEach
    void setUp() {
        userTypeId = 1L;

        userType = new UserType();
        userType.setId(userTypeId);
        userType.setName("Test Type");

        UserType userType2 = new UserType();
        userType2.setId(2L);
        userType2.setName("Another Type");

        userTypeList = Arrays.asList(userType, userType2);
    }

    @Test
    void whenGetUserTypeById_thenReturnUserType() {
        // Given
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.of(userType));

        // When
        UserType result = getUserTypeUseCase.getById(userTypeId);

        // Then
        assertNotNull(result);
        assertEquals(userType.getId(), result.getId());
        assertEquals(userType.getName(), result.getName());
        verify(userTypeRepository).findById(userTypeId);
    }

    @Test
    void whenGetNonExistingUserTypeById_thenThrowException() {
        // Given
        when(userTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            getUserTypeUseCase.getById(userTypeId);
        });

        assertEquals("Tipo de usuário não encontrado", exception.getMessage());
        verify(userTypeRepository).findById(userTypeId);
    }

    @Test
    void whenGetAllUserTypes_thenReturnAllUserTypes() {
        // Given
        when(userTypeRepository.findAll()).thenReturn(userTypeList);

        // When
        List<UserType> result = getUserTypeUseCase.getAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(userTypeList.get(0).getId(), result.get(0).getId());
        assertEquals(userTypeList.get(1).getId(), result.get(1).getId());
        verify(userTypeRepository).findAll();
    }
}

@ExtendWith(MockitoExtension.class)
class DeleteUserTypeUseCaseImplTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @InjectMocks
    private DeleteUserTypeUseCaseImpl deleteUserTypeUseCase;

    private Long userTypeId;

    @BeforeEach
    void setUp() {
        userTypeId = 1L;
    }

    @Test
    void whenDeleteUserType_thenRepositoryCalledWithCorrectId() {
        // Given
        when(userTypeRepository.findById(userTypeId)).thenReturn(Optional.of(new UserType()));

        // When
        deleteUserTypeUseCase.execute(userTypeId);

        // Then
        verify(userTypeRepository).findById(userTypeId);
        verify(userTypeRepository).deleteById(userTypeId);
    }

    @Test
    void whenDeleteNonExistingUserType_thenThrowException() {
        // Given
        when(userTypeRepository.findById(userTypeId)).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            deleteUserTypeUseCase.execute(userTypeId);
        });

        assertEquals("Tipo de usuário não encontrado", exception.getMessage());
        verify(userTypeRepository).findById(userTypeId);
        verify(userTypeRepository, never()).deleteById(anyLong());
    }
}