package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateRestaurantInput;
import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UpdateRestaurantInput;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.RestaurantRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.RestaurantMapper;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRestaurantUseCaseImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private CreateRestaurantUseCaseImpl createRestaurantUseCase;

    private CreateRestaurantInput restaurantInput;
    private Restaurant restaurant;
    private User owner;
    private RestaurantOutput restaurantOutput;

    @BeforeEach
    void setUp() {
        restaurantInput = new CreateRestaurantInput();
        restaurantInput.setName("Test Restaurant");
        restaurantInput.setAddress("Test Address");
        restaurantInput.setCuisineType("Italian");
        restaurantInput.setOperatingHours("9AM-10PM");
        restaurantInput.setOwnerId(1L);

        owner = new User();
        owner.setId(1L);
        owner.setName("Owner Name");
        owner.setEmail("owner@example.com");

        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName(restaurantInput.getName());
        restaurant.setAddress(restaurantInput.getAddress());
        restaurant.setCuisineType(restaurantInput.getCuisineType());
        restaurant.setOperatingHours(restaurantInput.getOperatingHours());
        restaurant.setOwner(owner);
        restaurant.setCreatedAt(LocalDateTime.now());
        restaurant.setLastUpdatedAt(LocalDateTime.now());

        restaurantOutput = new RestaurantOutput();
        restaurantOutput.setId(1L);
        restaurantOutput.setName(restaurantInput.getName());
        restaurantOutput.setAddress(restaurantInput.getAddress());
        restaurantOutput.setCuisineType(restaurantInput.getCuisineType());
        restaurantOutput.setOperatingHours(restaurantInput.getOperatingHours());
    }

    @Test
    void whenCreateRestaurant_thenReturnRestaurantOutput() {
        // Given
        when(restaurantRepository.existsByNameAndOwnerId(anyString(), anyLong())).thenReturn(false);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
        when(restaurantMapper.toOutput(any(Restaurant.class))).thenReturn(restaurantOutput);

        // When
        RestaurantOutput result = createRestaurantUseCase.execute(restaurantInput);

        // Then
        assertNotNull(result);
        assertEquals(restaurantInput.getName(), result.getName());
        assertEquals(restaurantInput.getAddress(), result.getAddress());
        verify(restaurantRepository).existsByNameAndOwnerId(restaurantInput.getName(), restaurantInput.getOwnerId());
        verify(userRepository).findById(restaurantInput.getOwnerId());
        verify(restaurantRepository).save(any(Restaurant.class));
        verify(restaurantMapper).toOutput(any(Restaurant.class));
    }

    @Test
    void whenCreateRestaurantWithExistingNameAndOwner_thenThrowException() {
        // Given
        when(restaurantRepository.existsByNameAndOwnerId(anyString(), anyLong())).thenReturn(true);

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            createRestaurantUseCase.execute(restaurantInput);
        });

        assertEquals("Já existe um restaurante com este nome para este proprietário", exception.getMessage());
        verify(restaurantRepository).existsByNameAndOwnerId(restaurantInput.getName(), restaurantInput.getOwnerId());
        verify(userRepository, never()).findById(anyLong());
        verify(restaurantRepository, never()).save(any(Restaurant.class));
    }

    @Test
    void whenCreateRestaurantWithNonExistingOwner_thenThrowException() {
        // Given
        when(restaurantRepository.existsByNameAndOwnerId(anyString(), anyLong())).thenReturn(false);
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            createRestaurantUseCase.execute(restaurantInput);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(restaurantRepository).existsByNameAndOwnerId(restaurantInput.getName(), restaurantInput.getOwnerId());
        verify(userRepository).findById(restaurantInput.getOwnerId());
        verify(restaurantRepository, never()).save(any(Restaurant.class));
    }
}

@ExtendWith(MockitoExtension.class)
class UpdateRestaurantUseCaseImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private UpdateRestaurantUseCaseImpl updateRestaurantUseCase;

    private UpdateRestaurantInput updateRestaurantInput;
    private Restaurant existingRestaurant;
    private User owner;
    private RestaurantOutput restaurantOutput;

    @BeforeEach
    void setUp() {
        updateRestaurantInput = new UpdateRestaurantInput();
        updateRestaurantInput.setName("Updated Restaurant");
        updateRestaurantInput.setAddress("Updated Address");
        updateRestaurantInput.setCuisineType("French");
        updateRestaurantInput.setOperatingHours("10AM-11PM");
        updateRestaurantInput.setOwnerId(2L);

        owner = new User();
        owner.setId(1L);
        owner.setName("Original Owner");
        owner.setEmail("original@example.com");

        User newOwner = new User();
        newOwner.setId(2L);
        newOwner.setName("New Owner");
        newOwner.setEmail("new@example.com");

        existingRestaurant = new Restaurant();
        existingRestaurant.setId(1L);
        existingRestaurant.setName("Test Restaurant");
        existingRestaurant.setAddress("Test Address");
        existingRestaurant.setCuisineType("Italian");
        existingRestaurant.setOperatingHours("9AM-10PM");
        existingRestaurant.setOwner(owner);
        existingRestaurant.setCreatedAt(LocalDateTime.now());
        existingRestaurant.setLastUpdatedAt(LocalDateTime.now());

        restaurantOutput = new RestaurantOutput();
        restaurantOutput.setId(1L);
        restaurantOutput.setName(updateRestaurantInput.getName());
        restaurantOutput.setAddress(updateRestaurantInput.getAddress());
        restaurantOutput.setCuisineType(updateRestaurantInput.getCuisineType());
        restaurantOutput.setOperatingHours(updateRestaurantInput.getOperatingHours());
    }

    @Test
    void whenUpdateRestaurant_thenReturnUpdatedRestaurantOutput() {
        // Given
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(existingRestaurant));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(existingRestaurant);
        when(restaurantMapper.toOutput(any(Restaurant.class))).thenReturn(restaurantOutput);

        // When
        RestaurantOutput result = updateRestaurantUseCase.execute(1L, updateRestaurantInput);

        // Then
        assertNotNull(result);
        assertEquals(updateRestaurantInput.getName(), result.getName());
        assertEquals(updateRestaurantInput.getAddress(), result.getAddress());
        verify(restaurantRepository).findById(1L);
        verify(userRepository).findById(updateRestaurantInput.getOwnerId());
        verify(restaurantRepository).save(any(Restaurant.class));
        verify(restaurantMapper).toOutput(any(Restaurant.class));
    }

    @Test
    void whenUpdateNonExistingRestaurant_thenThrowException() {
        // Given
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            updateRestaurantUseCase.execute(1L, updateRestaurantInput);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(restaurantRepository).findById(1L);
        verify(userRepository, never()).findById(anyLong());
        verify(restaurantRepository, never()).save(any(Restaurant.class));
    }

    @Test
    void whenUpdateRestaurantWithNonExistingOwner_thenThrowException() {
        // Given
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(existingRestaurant));
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            updateRestaurantUseCase.execute(1L, updateRestaurantInput);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(restaurantRepository).findById(1L);
        verify(userRepository).findById(updateRestaurantInput.getOwnerId());
        verify(restaurantRepository, never()).save(any(Restaurant.class));
    }

    @Test
    void whenUpdateRestaurantWithSameOwner_thenDoNotCallFindOwner() {
        // Given
        updateRestaurantInput.setOwnerId(1L); // Same owner as existing
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(existingRestaurant));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(existingRestaurant);
        when(restaurantMapper.toOutput(any(Restaurant.class))).thenReturn(restaurantOutput);

        // When
        RestaurantOutput result = updateRestaurantUseCase.execute(1L, updateRestaurantInput);

        // Then
        assertNotNull(result);
        verify(restaurantRepository).findById(1L);
        verify(userRepository, never()).findById(anyLong()); // Owner is the same, no need to find
        verify(restaurantRepository).save(any(Restaurant.class));
    }
}

@ExtendWith(MockitoExtension.class)
class GetRestaurantUseCaseImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private GetRestaurantUseCaseImpl getRestaurantUseCase;

    private Restaurant restaurant;
    private RestaurantOutput restaurantOutput;
    private List<Restaurant> restaurantList;
    private List<RestaurantOutput> restaurantOutputList;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("Test Address");
        restaurant.setCuisineType("Italian");

        restaurantOutput = new RestaurantOutput();
        restaurantOutput.setId(1L);
        restaurantOutput.setName("Test Restaurant");
        restaurantOutput.setAddress("Test Address");
        restaurantOutput.setCuisineType("Italian");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Another Restaurant");
        restaurant2.setAddress("Another Address");
        restaurant2.setCuisineType("French");

        RestaurantOutput restaurantOutput2 = new RestaurantOutput();
        restaurantOutput2.setId(2L);
        restaurantOutput2.setName("Another Restaurant");
        restaurantOutput2.setAddress("Another Address");
        restaurantOutput2.setCuisineType("French");

        restaurantList = Arrays.asList(restaurant, restaurant2);
        restaurantOutputList = Arrays.asList(restaurantOutput, restaurantOutput2);
    }

    @Test
    void whenGetRestaurantById_thenReturnRestaurantOutput() {
        // Given
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(restaurantMapper.toOutput(any(Restaurant.class))).thenReturn(restaurantOutput);

        // When
        RestaurantOutput result = getRestaurantUseCase.getById(1L);

        // Then
        assertNotNull(result);
        assertEquals(restaurant.getId(), result.getId());
        assertEquals(restaurant.getName(), result.getName());
        verify(restaurantRepository).findById(1L);
        verify(restaurantMapper).toOutput(restaurant);
    }

    @Test
    void whenGetNonExistingRestaurantById_thenThrowException() {
        // Given
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            getRestaurantUseCase.getById(1L);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(restaurantRepository).findById(1L);
        verify(restaurantMapper, never()).toOutput(any(Restaurant.class));
    }

    @Test
    void whenGetAllRestaurants_thenReturnAllRestaurantOutputs() {
        // Given
        when(restaurantRepository.findAll()).thenReturn(restaurantList);
        when(restaurantMapper.toOutput(restaurant)).thenReturn(restaurantOutput);
        when(restaurantMapper.toOutput(restaurantList.get(1))).thenReturn(restaurantOutputList.get(1));

        // When
        List<RestaurantOutput> result = getRestaurantUseCase.getAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(restaurantList.get(0).getId(), result.get(0).getId());
        assertEquals(restaurantList.get(1).getId(), result.get(1).getId());
        verify(restaurantRepository).findAll();
        verify(restaurantMapper, times(2)).toOutput(any(Restaurant.class));
    }

    @Test
    void whenGetRestaurantsByOwnerId_thenReturnOwnerRestaurants() {
        // Given
        Long ownerId = 1L;
        when(restaurantRepository.findByOwnerId(ownerId)).thenReturn(restaurantList);
        when(restaurantMapper.toOutput(restaurantList.get(0))).thenReturn(restaurantOutputList.get(0));
        when(restaurantMapper.toOutput(restaurantList.get(1))).thenReturn(restaurantOutputList.get(1));

        // When
        List<RestaurantOutput> result = getRestaurantUseCase.getByOwnerId(ownerId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(restaurantOutputList.get(0).getId(), result.get(0).getId());
        assertEquals(restaurantOutputList.get(1).getId(), result.get(1).getId());
        verify(restaurantRepository).findByOwnerId(ownerId);
        verify(restaurantMapper, times(2)).toOutput(any(Restaurant.class));
    }
}

@ExtendWith(MockitoExtension.class)
class DeleteRestaurantUseCaseImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private DeleteRestaurantUseCaseImpl deleteRestaurantUseCase;

    @Test
    void whenDeleteRestaurant_thenRepositoryCalledWithCorrectId() {
        // Given
        Long restaurantId = 1L;
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(new Restaurant()));

        // When
        deleteRestaurantUseCase.execute(restaurantId);

        // Then
        verify(restaurantRepository).findById(restaurantId);
        verify(restaurantRepository).deleteById(restaurantId);
    }

    @Test
    void whenDeleteNonExistingRestaurant_thenThrowException() {
        // Given
        Long restaurantId = 1L;
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            deleteRestaurantUseCase.execute(restaurantId);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(restaurantRepository).findById(restaurantId);
        verify(restaurantRepository, never()).deleteById(anyLong());
    }
}