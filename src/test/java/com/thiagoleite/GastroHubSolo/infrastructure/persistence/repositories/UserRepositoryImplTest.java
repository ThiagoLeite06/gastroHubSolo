package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;

import com.thiagoleite.GastroHubSolo.domain.entities.MenuItem;
import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.MenuItemMapper;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.RestaurantMapper;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserMapper;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserTypeMapper;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.MenuItemEntity;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.RestaurantEntity;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserEntity;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
public class UserRepositoryImplTest {

    @Mock
    private JpaUserRepository jpaUserRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    private User user;
    private UserEntity userEntity;
    private List<User> userList;
    private List<UserEntity> userEntityList;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Test User");
        userEntity.setEmail("test@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Another User");
        user2.setEmail("another@example.com");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2L);
        userEntity2.setName("Another User");
        userEntity2.setEmail("another@example.com");

        userList = Arrays.asList(user, user2);
        userEntityList = Arrays.asList(userEntity, userEntity2);
    }

    @Test
    void whenSaveUser_thenReturnSavedUser() {
        // Given
        when(userMapper.toEntity(any(User.class))).thenReturn(userEntity);
        when(jpaUserRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.toUser(any(UserEntity.class))).thenReturn(user);

        // When
        User result = userRepository.save(user);

        // Then
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
        verify(userMapper).toEntity(user);
        verify(jpaUserRepository).save(userEntity);
        verify(userMapper).toUser(userEntity);
    }

    @Test
    void whenFindUserById_thenReturnUser() {
        // Given
        when(jpaUserRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userMapper.toUser(any(UserEntity.class))).thenReturn(user);

        // When
        Optional<User> result = userRepository.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getName(), result.get().getName());
        assertEquals(user.getEmail(), result.get().getEmail());
        verify(jpaUserRepository).findById(1L);
        verify(userMapper).toUser(userEntity);
    }

    @Test
    void whenFindUserByNonExistingId_thenReturnEmpty() {
        // Given
        when(jpaUserRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Optional<User> result = userRepository.findById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(jpaUserRepository).findById(1L);
        verify(userMapper, never()).toUser(any(UserEntity.class));
    }

    @Test
    void whenFindAllUsers_thenReturnAllUsers() {
        // Given
        when(jpaUserRepository.findAll()).thenReturn(userEntityList);
        when(userMapper.toUser(userEntityList.get(0))).thenReturn(userList.get(0));
        when(userMapper.toUser(userEntityList.get(1))).thenReturn(userList.get(1));

        // When
        List<User> result = userRepository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(userList.get(0).getId(), result.get(0).getId());
        assertEquals(userList.get(1).getId(), result.get(1).getId());
        verify(jpaUserRepository).findAll();
        verify(userMapper, times(2)).toUser(any(UserEntity.class));
    }

    @Test
    void whenDeleteUserById_thenRepositoryCalledWithCorrectId() {
        // Given
        Long userId = 1L;

        // When
        userRepository.deleteById(userId);

        // Then
        verify(jpaUserRepository).deleteById(userId);
    }

    @Test
    void whenFindUserByEmail_thenReturnUser() {
        // Given
        String email = "test@example.com";
        when(jpaUserRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(userMapper.toUser(any(UserEntity.class))).thenReturn(user);

        // When
        Optional<User> result = userRepository.findByEmail(email);

        // Then
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getName(), result.get().getName());
        assertEquals(user.getEmail(), result.get().getEmail());
        verify(jpaUserRepository).findByEmail(email);
        verify(userMapper).toUser(userEntity);
    }

    @Test
    void whenExistsByEmail_thenReturnTrue() {
        // Given
        String email = "test@example.com";
        when(jpaUserRepository.existsByEmail(anyString())).thenReturn(true);

        // When
        boolean result = userRepository.existsByEmail(email);

        // Then
        assertTrue(result);
        verify(jpaUserRepository).existsByEmail(email);
    }
}

@ExtendWith(MockitoExtension.class)
class RestaurantRepositoryImplTest {

    @Mock
    private JpaRestaurantRepository jpaRestaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantRepositoryImpl restaurantRepository;

    private Restaurant restaurant;
    private RestaurantEntity restaurantEntity;
    private List<Restaurant> restaurantList;
    private List<RestaurantEntity> restaurantEntityList;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("Test Address");
        restaurant.setCuisineType("Italian");

        restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(1L);
        restaurantEntity.setName("Test Restaurant");
        restaurantEntity.setAddress("Test Address");
        restaurantEntity.setCuisineType("Italian");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Another Restaurant");
        restaurant2.setAddress("Another Address");
        restaurant2.setCuisineType("French");

        RestaurantEntity restaurantEntity2 = new RestaurantEntity();
        restaurantEntity2.setId(2L);
        restaurantEntity2.setName("Another Restaurant");
        restaurantEntity2.setAddress("Another Address");
        restaurantEntity2.setCuisineType("French");

        restaurantList = Arrays.asList(restaurant, restaurant2);
        restaurantEntityList = Arrays.asList(restaurantEntity, restaurantEntity2);
    }

    @Test
    void whenSaveRestaurant_thenReturnSavedRestaurant() {
        // Given
        when(restaurantMapper.toEntity(any(Restaurant.class))).thenReturn(restaurantEntity);
        when(jpaRestaurantRepository.save(any(RestaurantEntity.class))).thenReturn(restaurantEntity);
        when(restaurantMapper.toDomain(any(RestaurantEntity.class))).thenReturn(restaurant);

        // When
        Restaurant result = restaurantRepository.save(restaurant);

        // Then
        assertNotNull(result);
        assertEquals(restaurant.getId(), result.getId());
        assertEquals(restaurant.getName(), result.getName());
        assertEquals(restaurant.getAddress(), result.getAddress());
        verify(restaurantMapper).toEntity(restaurant);
        verify(jpaRestaurantRepository).save(restaurantEntity);
        verify(restaurantMapper).toDomain(restaurantEntity);
    }

    @Test
    void whenFindRestaurantById_thenReturnRestaurant() {
        // Given
        when(jpaRestaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurantEntity));
        when(restaurantMapper.toDomain(any(RestaurantEntity.class))).thenReturn(restaurant);

        // When
        Optional<Restaurant> result = restaurantRepository.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(restaurant.getId(), result.get().getId());
        assertEquals(restaurant.getName(), result.get().getName());
        assertEquals(restaurant.getAddress(), result.get().getAddress());
        verify(jpaRestaurantRepository).findById(1L);
        verify(restaurantMapper).toDomain(restaurantEntity);
    }

    @Test
    void whenFindAllRestaurants_thenReturnAllRestaurants() {
        // Given
        when(jpaRestaurantRepository.findAll()).thenReturn(restaurantEntityList);
        when(restaurantMapper.toDomain(restaurantEntityList.get(0))).thenReturn(restaurantList.get(0));
        when(restaurantMapper.toDomain(restaurantEntityList.get(1))).thenReturn(restaurantList.get(1));

        // When
        List<Restaurant> result = restaurantRepository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(restaurantList.get(0).getId(), result.get(0).getId());
        assertEquals(restaurantList.get(1).getId(), result.get(1).getId());
        verify(jpaRestaurantRepository).findAll();
        verify(restaurantMapper, times(2)).toDomain(any(RestaurantEntity.class));
    }

    @Test
    void whenFindRestaurantsByOwnerId_thenReturnOwnerRestaurants() {
        // Given
        Long ownerId = 1L;
        when(jpaRestaurantRepository.findByOwnerId(anyLong())).thenReturn(restaurantEntityList);
        when(restaurantMapper.toDomain(restaurantEntityList.get(0))).thenReturn(restaurantList.get(0));
        when(restaurantMapper.toDomain(restaurantEntityList.get(1))).thenReturn(restaurantList.get(1));

        // When
        List<Restaurant> result = restaurantRepository.findByOwnerId(ownerId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(restaurantList.get(0).getId(), result.get(0).getId());
        assertEquals(restaurantList.get(1).getId(), result.get(1).getId());
        verify(jpaRestaurantRepository).findByOwnerId(ownerId);
        verify(restaurantMapper, times(2)).toDomain(any(RestaurantEntity.class));
    }

    @Test
    void whenExistsByNameAndOwnerId_thenReturnTrue() {
        // Given
        String name = "Test Restaurant";
        Long ownerId = 1L;
        when(jpaRestaurantRepository.existsByNameAndOwnerId(anyString(), anyLong())).thenReturn(true);

        // When
        boolean result = restaurantRepository.existsByNameAndOwnerId(name, ownerId);

        // Then
        assertTrue(result);
        verify(jpaRestaurantRepository).existsByNameAndOwnerId(name, ownerId);
    }
}

@ExtendWith(MockitoExtension.class)
class MenuItemRepositoryImplTest {

    @Mock
    private JpaMenuItemRepository jpaMenuItemRepository;

    @Mock
    private MenuItemMapper menuItemMapper;

    @InjectMocks
    private MenuItemRepositoryImpl menuItemRepository;

    private MenuItem menuItem;
    private MenuItemEntity menuItemEntity;
    private List<MenuItem> menuItemList;
    private List<MenuItemEntity> menuItemEntityList;

    @BeforeEach
    void setUp() {
        menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName("Test Item");
        menuItem.setDescription("Test Description");
        menuItem.setPrice(new BigDecimal("15.99"));

        menuItemEntity = new MenuItemEntity();
        menuItemEntity.setId(1L);
        menuItemEntity.setName("Test Item");
        menuItemEntity.setDescription("Test Description");
        menuItemEntity.setPrice(new BigDecimal("15.99"));

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(2L);
        menuItem2.setName("Another Item");
        menuItem2.setDescription("Another Description");
        menuItem2.setPrice(new BigDecimal("12.99"));

        MenuItemEntity menuItemEntity2 = new MenuItemEntity();
        menuItemEntity2.setId(2L);
        menuItemEntity2.setName("Another Item");
        menuItemEntity2.setDescription("Another Description");
        menuItemEntity2.setPrice(new BigDecimal("12.99"));

        menuItemList = Arrays.asList(menuItem, menuItem2);
        menuItemEntityList = Arrays.asList(menuItemEntity, menuItemEntity2);
    }

    @Test
    void whenSaveMenuItem_thenReturnSavedMenuItem() {
        // Given
        when(menuItemMapper.toEntity(any(MenuItem.class))).thenReturn(menuItemEntity);
        when(jpaMenuItemRepository.save(any(MenuItemEntity.class))).thenReturn(menuItemEntity);
        when(menuItemMapper.toDomain(any(MenuItemEntity.class))).thenReturn(menuItem);

        // When
        MenuItem result = menuItemRepository.save(menuItem);

        // Then
        assertNotNull(result);
        assertEquals(menuItem.getId(), result.getId());
        assertEquals(menuItem.getName(), result.getName());
        assertEquals(menuItem.getDescription(), result.getDescription());
        verify(menuItemMapper).toEntity(menuItem);
        verify(jpaMenuItemRepository).save(menuItemEntity);
        verify(menuItemMapper).toDomain(menuItemEntity);
    }

    @Test
    void whenFindMenuItemById_thenReturnMenuItem() {
        // Given
        when(jpaMenuItemRepository.findById(anyLong())).thenReturn(Optional.of(menuItemEntity));
        when(menuItemMapper.toDomain(any(MenuItemEntity.class))).thenReturn(menuItem);

        // When
        Optional<MenuItem> result = menuItemRepository.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(menuItem.getId(), result.get().getId());
        assertEquals(menuItem.getName(), result.get().getName());
        assertEquals(menuItem.getDescription(), result.get().getDescription());
        verify(jpaMenuItemRepository).findById(1L);
        verify(menuItemMapper).toDomain(menuItemEntity);
    }

    @Test
    void whenFindAllMenuItems_thenReturnAllMenuItems() {
        // Given
        when(jpaMenuItemRepository.findAll()).thenReturn(menuItemEntityList);
        when(menuItemMapper.toDomain(menuItemEntityList.get(0))).thenReturn(menuItemList.get(0));
        when(menuItemMapper.toDomain(menuItemEntityList.get(1))).thenReturn(menuItemList.get(1));

        // When
        List<MenuItem> result = menuItemRepository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(menuItemList.get(0).getId(), result.get(0).getId());
        assertEquals(menuItemList.get(1).getId(), result.get(1).getId());
        verify(jpaMenuItemRepository).findAll();
        verify(menuItemMapper, times(2)).toDomain(any(MenuItemEntity.class));
    }

    @Test
    void whenFindMenuItemsByRestaurantId_thenReturnRestaurantMenuItems() {
        // Given
        Long restaurantId = 1L;
        when(jpaMenuItemRepository.findByRestaurantId(anyLong())).thenReturn(menuItemEntityList);
        when(menuItemMapper.toDomain(menuItemEntityList.get(0))).thenReturn(menuItemList.get(0));
        when(menuItemMapper.toDomain(menuItemEntityList.get(1))).thenReturn(menuItemList.get(1));

        // When
        List<MenuItem> result = menuItemRepository.findByRestaurantId(restaurantId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(menuItemList.get(0).getId(), result.get(0).getId());
        assertEquals(menuItemList.get(1).getId(), result.get(1).getId());
        verify(jpaMenuItemRepository).findByRestaurantId(restaurantId);
        verify(menuItemMapper, times(2)).toDomain(any(MenuItemEntity.class));
    }

    @Test
    void whenExistsByNameAndRestaurantId_thenReturnTrue() {
        // Given
        String name = "Test Item";
        Long restaurantId = 1L;
        when(jpaMenuItemRepository.existsByNameAndRestaurantId(anyString(), anyLong())).thenReturn(true);

        // When
        boolean result = menuItemRepository.existsByNameAndRestaurantId(name, restaurantId);

        // Then
        assertTrue(result);
        verify(jpaMenuItemRepository).existsByNameAndRestaurantId(name, restaurantId);
    }
}

@ExtendWith(MockitoExtension.class)
class UserTypeRepositoryImplTest {

    @Mock
    private JpaUserTypeRepository jpaUserTypeRepository;

    @Mock
    private UserTypeMapper userTypeMapper;

    @InjectMocks
    private UserTypeRepositoryImpl userTypeRepository;

    private UserType userType;
    private UserTypeEntity userTypeEntity;
    private List<UserType> userTypeList;
    private List<UserTypeEntity> userTypeEntityList;

    @BeforeEach
    void setUp() {
        userType = new UserType();
        userType.setId(1L);
        userType.setName("Cliente");
        userType.setCreatedAt(LocalDateTime.now());
        userType.setLastUpdatedAt(LocalDateTime.now());

        userTypeEntity = new UserTypeEntity();
        userTypeEntity.setId(1L);
        userTypeEntity.setName("Cliente");

        UserType userType2 = new UserType();
        userType2.setId(2L);
        userType2.setName("Dono de Restaurante");
        userType2.setCreatedAt(LocalDateTime.now());
        userType2.setLastUpdatedAt(LocalDateTime.now());

        UserTypeEntity userTypeEntity2 = new UserTypeEntity();
        userTypeEntity2.setId(2L);
        userTypeEntity2.setName("Dono de Restaurante");

        userTypeList = Arrays.asList(userType, userType2);
        userTypeEntityList = Arrays.asList(userTypeEntity, userTypeEntity2);
    }

    @Test
    void whenSaveUserType_thenReturnSavedUserType() {
        // Given
        when(userTypeMapper.toEntity(any(UserType.class))).thenReturn(userTypeEntity);
        when(jpaUserTypeRepository.save(any(UserTypeEntity.class))).thenReturn(userTypeEntity);
        when(userTypeMapper.toUserType(any(UserTypeEntity.class))).thenReturn(userType);

        // When
        UserType result = userTypeRepository.save(userType);

        // Then
        assertNotNull(result);
        assertEquals(userType.getId(), result.getId());
        assertEquals(userType.getName(), result.getName());
        verify(userTypeMapper).toEntity(userType);
        verify(jpaUserTypeRepository).save(userTypeEntity);
        verify(userTypeMapper).toUserType(userTypeEntity);
    }

    @Test
    void whenFindUserTypeById_thenReturnUserType() {
        // Given
        when(jpaUserTypeRepository.findById(anyLong())).thenReturn(Optional.of(userTypeEntity));
        when(userTypeMapper.toUserType(any(UserTypeEntity.class))).thenReturn(userType);

        // When
        Optional<UserType> result = userTypeRepository.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(userType.getId(), result.get().getId());
        assertEquals(userType.getName(), result.get().getName());
        verify(jpaUserTypeRepository).findById(1L);
        verify(userTypeMapper).toUserType(userTypeEntity);
    }

    @Test
    void whenFindUserTypeByNonExistingId_thenReturnEmpty() {
        // Given
        when(jpaUserTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Optional<UserType> result = userTypeRepository.findById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(jpaUserTypeRepository).findById(1L);
        verify(userTypeMapper, never()).toUserType(any(UserTypeEntity.class));
    }

    @Test
    void whenFindAllUserTypes_thenReturnAllUserTypes() {
        // Given
        when(jpaUserTypeRepository.findAll()).thenReturn(userTypeEntityList);
        when(userTypeMapper.toUserType(userTypeEntityList.get(0))).thenReturn(userTypeList.get(0));
        when(userTypeMapper.toUserType(userTypeEntityList.get(1))).thenReturn(userTypeList.get(1));

        // When
        List<UserType> result = userTypeRepository.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(userTypeList.get(0).getId(), result.get(0).getId());
        assertEquals(userTypeList.get(1).getId(), result.get(1).getId());
        verify(jpaUserTypeRepository).findAll();
        verify(userTypeMapper, times(2)).toUserType(any(UserTypeEntity.class));
    }

    @Test
    void whenDeleteUserTypeById_thenRepositoryCalledWithCorrectId() {
        // Given
        Long userTypeId = 1L;

        // When
        userTypeRepository.deleteById(userTypeId);

        // Then
        verify(jpaUserTypeRepository).deleteById(userTypeId);
    }

    @Test
    void whenExistsByName_thenReturnTrue() {
        // Given
        String name = "Cliente";
        when(jpaUserTypeRepository.existsByName(anyString())).thenReturn(true);

        // When
        boolean result = userTypeRepository.existsByName(name);

        // Then
        assertTrue(result);
        verify(jpaUserTypeRepository).existsByName(name);
    }
}