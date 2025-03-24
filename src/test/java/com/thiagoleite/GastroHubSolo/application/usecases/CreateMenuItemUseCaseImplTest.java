package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateMenuItemInput;
import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UpdateMenuItemInput;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.entities.MenuItem;
import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
import com.thiagoleite.GastroHubSolo.domain.repositories.MenuItemRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.RestaurantRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.MenuItemMapper;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateMenuItemUseCaseImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemMapper menuItemMapper;

    @InjectMocks
    private CreateMenuItemUseCaseImpl createMenuItemUseCase;

    private CreateMenuItemInput menuItemInput;
    private MenuItem menuItem;
    private Restaurant restaurant;
    private MenuItemOutput menuItemOutput;

    @BeforeEach
    void setUp() {
        menuItemInput = new CreateMenuItemInput();
        menuItemInput.setName("Test Item");
        menuItemInput.setDescription("Test Description");
        menuItemInput.setPrice(new BigDecimal("15.99"));
        menuItemInput.setAvailableOnlyInRestaurant(false);
        menuItemInput.setPhotoPath("/photos/test.jpg");
        menuItemInput.setRestaurantId(1L);

        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");

        menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName(menuItemInput.getName());
        menuItem.setDescription(menuItemInput.getDescription());
        menuItem.setPrice(menuItemInput.getPrice());
        menuItem.setAvailableOnlyInRestaurant(menuItemInput.isAvailableOnlyInRestaurant());
        menuItem.setPhotoPath(menuItemInput.getPhotoPath());
        menuItem.setRestaurant(restaurant);
        menuItem.setCreatedAt(LocalDateTime.now());
        menuItem.setLastUpdatedAt(LocalDateTime.now());

        menuItemOutput = new MenuItemOutput();
        menuItemOutput.setId(1L);
        menuItemOutput.setName(menuItemInput.getName());
        menuItemOutput.setDescription(menuItemInput.getDescription());
        menuItemOutput.setPrice(menuItemInput.getPrice());
    }

    @Test
    void whenCreateMenuItem_thenReturnMenuItemOutput() {
        // Given
        when(menuItemRepository.existsByNameAndRestaurantId(anyString(), anyLong())).thenReturn(false);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);
        when(menuItemMapper.toOutput(any(MenuItem.class))).thenReturn(menuItemOutput);

        // When
        MenuItemOutput result = createMenuItemUseCase.execute(menuItemInput);

        // Then
        assertNotNull(result);
        assertEquals(menuItemInput.getName(), result.getName());
        assertEquals(menuItemInput.getDescription(), result.getDescription());
        assertEquals(menuItemInput.getPrice(), result.getPrice());
        verify(menuItemRepository).existsByNameAndRestaurantId(menuItemInput.getName(), menuItemInput.getRestaurantId());
        verify(restaurantRepository).findById(menuItemInput.getRestaurantId());
        verify(menuItemRepository).save(any(MenuItem.class));
        verify(menuItemMapper).toOutput(any(MenuItem.class));
    }

    @Test
    void whenCreateMenuItemWithExistingNameInRestaurant_thenThrowException() {
        // Given
        when(menuItemRepository.existsByNameAndRestaurantId(anyString(), anyLong())).thenReturn(true);

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            createMenuItemUseCase.execute(menuItemInput);
        });

        assertEquals("Já existe um item com este nome neste restaurante", exception.getMessage());
        verify(menuItemRepository).existsByNameAndRestaurantId(menuItemInput.getName(), menuItemInput.getRestaurantId());
        verify(restaurantRepository, never()).findById(anyLong());
        verify(menuItemRepository, never()).save(any(MenuItem.class));
    }

    @Test
    void whenCreateMenuItemWithNonExistingRestaurant_thenThrowException() {
        // Given
        when(menuItemRepository.existsByNameAndRestaurantId(anyString(), anyLong())).thenReturn(false);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            createMenuItemUseCase.execute(menuItemInput);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
        verify(menuItemRepository).existsByNameAndRestaurantId(menuItemInput.getName(), menuItemInput.getRestaurantId());
        verify(restaurantRepository).findById(menuItemInput.getRestaurantId());
        verify(menuItemRepository, never()).save(any(MenuItem.class));
    }
}

@ExtendWith(MockitoExtension.class)
class UpdateMenuItemUseCaseImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemMapper menuItemMapper;

    @InjectMocks
    private UpdateMenuItemUseCaseImpl updateMenuItemUseCase;

    private UpdateMenuItemInput updateMenuItemInput;
    private MenuItem existingMenuItem;
    private MenuItemOutput menuItemOutput;

    @BeforeEach
    void setUp() {
        updateMenuItemInput = new UpdateMenuItemInput();
        updateMenuItemInput.setName("Updated Item");
        updateMenuItemInput.setDescription("Updated Description");
        updateMenuItemInput.setPrice(new BigDecimal("19.99"));
        updateMenuItemInput.setAvailableOnlyInRestaurant(true);
        updateMenuItemInput.setPhotoPath("/photos/updated.jpg");

        existingMenuItem = new MenuItem();
        existingMenuItem.setId(1L);
        existingMenuItem.setName("Test Item");
        existingMenuItem.setDescription("Test Description");
        existingMenuItem.setPrice(new BigDecimal("15.99"));
        existingMenuItem.setAvailableOnlyInRestaurant(false);
        existingMenuItem.setPhotoPath("/photos/test.jpg");
        existingMenuItem.setCreatedAt(LocalDateTime.now());
        existingMenuItem.setLastUpdatedAt(LocalDateTime.now());

        menuItemOutput = new MenuItemOutput();
        menuItemOutput.setId(1L);
        menuItemOutput.setName(updateMenuItemInput.getName());
        menuItemOutput.setDescription(updateMenuItemInput.getDescription());
        menuItemOutput.setPrice(updateMenuItemInput.getPrice());
        menuItemOutput.setAvailableOnlyInRestaurant(updateMenuItemInput.isAvailableOnlyInRestaurant());
        menuItemOutput.setPhotoPath(updateMenuItemInput.getPhotoPath());
    }

    @Test
    void whenUpdateMenuItem_thenReturnUpdatedMenuItemOutput() {
        // Given
        when(menuItemRepository.findById(anyLong())).thenReturn(Optional.of(existingMenuItem));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(existingMenuItem);
        when(menuItemMapper.toOutput(any(MenuItem.class))).thenReturn(menuItemOutput);

        // When
        MenuItemOutput result = updateMenuItemUseCase.execute(1L, updateMenuItemInput);

        // Then
        assertNotNull(result);
        assertEquals(updateMenuItemInput.getName(), result.getName());
        assertEquals(updateMenuItemInput.getDescription(), result.getDescription());
        assertEquals(updateMenuItemInput.getPrice(), result.getPrice());
        verify(menuItemRepository).findById(1L);
        verify(menuItemRepository).save(any(MenuItem.class));
        verify(menuItemMapper).toOutput(any(MenuItem.class));
    }

    @Test
    void whenUpdateNonExistingMenuItem_thenThrowException() {
        // Given
        when(menuItemRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            updateMenuItemUseCase.execute(1L, updateMenuItemInput);
        });

        assertEquals("Item de cardápio não encontrado", exception.getMessage());
        verify(menuItemRepository).findById(1L);
        verify(menuItemRepository, never()).save(any(MenuItem.class));
    }
}

@ExtendWith(MockitoExtension.class)
class GetMenuItemUseCaseImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemMapper menuItemMapper;

    @InjectMocks
    private GetMenuItemUseCaseImpl getMenuItemUseCase;

    private MenuItem menuItem;
    private MenuItemOutput menuItemOutput;
    private List<MenuItem> menuItemList;
    private List<MenuItemOutput> menuItemOutputList;

    @BeforeEach
    void setUp() {
        menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setName("Test Item");
        menuItem.setDescription("Test Description");
        menuItem.setPrice(new BigDecimal("15.99"));

        menuItemOutput = new MenuItemOutput();
        menuItemOutput.setId(1L);
        menuItemOutput.setName("Test Item");
        menuItemOutput.setDescription("Test Description");
        menuItemOutput.setPrice(new BigDecimal("15.99"));

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(2L);
        menuItem2.setName("Another Item");
        menuItem2.setDescription("Another Description");
        menuItem2.setPrice(new BigDecimal("12.99"));

        MenuItemOutput menuItemOutput2 = new MenuItemOutput();
        menuItemOutput2.setId(2L);
        menuItemOutput2.setName("Another Item");
        menuItemOutput2.setDescription("Another Description");
        menuItemOutput2.setPrice(new BigDecimal("12.99"));

        menuItemList = Arrays.asList(menuItem, menuItem2);
        menuItemOutputList = Arrays.asList(menuItemOutput, menuItemOutput2);
    }

    @Test
    void whenGetMenuItemById_thenReturnMenuItemOutput() {
        // Given
        when(menuItemRepository.findById(anyLong())).thenReturn(Optional.of(menuItem));
        when(menuItemMapper.toOutput(any(MenuItem.class))).thenReturn(menuItemOutput);

        // When
        MenuItemOutput result = getMenuItemUseCase.getById(1L);

        // Then
        assertNotNull(result);
        assertEquals(menuItem.getId(), result.getId());
        assertEquals(menuItem.getName(), result.getName());
        verify(menuItemRepository).findById(1L);
        verify(menuItemMapper).toOutput(menuItem);
    }

    @Test
    void whenGetNonExistingMenuItemById_thenThrowException() {
        // Given
        when(menuItemRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            getMenuItemUseCase.getById(1L);
        });

        assertEquals("Item de cardápio não encontrado", exception.getMessage());
        verify(menuItemRepository).findById(1L);
        verify(menuItemMapper, never()).toOutput(any(MenuItem.class));
    }

    @Test
    void whenGetAllMenuItems_thenReturnAllMenuItemOutputs() {
        // Given
        when(menuItemRepository.findAll()).thenReturn(menuItemList);
        when(menuItemMapper.toOutput(menuItem)).thenReturn(menuItemOutput);
        when(menuItemMapper.toOutput(menuItemList.get(1))).thenReturn(menuItemOutputList.get(1));

        // When
        List<MenuItemOutput> result = getMenuItemUseCase.getAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(menuItemList.get(0).getId(), result.get(0).getId());
        assertEquals(menuItemList.get(1).getId(), result.get(1).getId());
        verify(menuItemRepository).findAll();
        verify(menuItemMapper, times(2)).toOutput(any(MenuItem.class));
    }

    @Test
    void whenGetMenuItemsByRestaurantId_thenReturnRestaurantMenuItems() {
        // Given
        Long restaurantId = 1L;
        when(menuItemRepository.findByRestaurantId(restaurantId)).thenReturn(menuItemList);
        when(menuItemMapper.toOutput(menuItemList.get(0))).thenReturn(menuItemOutputList.get(0));
        when(menuItemMapper.toOutput(menuItemList.get(1))).thenReturn(menuItemOutputList.get(1));

        // When
        List<MenuItemOutput> result = getMenuItemUseCase.getByRestaurantId(restaurantId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(menuItemOutputList.get(0).getId(), result.get(0).getId());
        assertEquals(menuItemOutputList.get(1).getId(), result.get(1).getId());
        verify(menuItemRepository).findByRestaurantId(restaurantId);
        verify(menuItemMapper, times(2)).toOutput(any(MenuItem.class));
    }
}

@ExtendWith(MockitoExtension.class)
class DeleteMenuItemUseCaseImplTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private DeleteMenuItemUseCaseImpl deleteMenuItemUseCase;

    @Test
    void whenDeleteMenuItem_thenRepositoryCalledWithCorrectId() {
        // Given
        Long menuItemId = 1L;
        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(new MenuItem()));

        // When
        deleteMenuItemUseCase.execute(menuItemId);

        // Then
        verify(menuItemRepository).findById(menuItemId);
        verify(menuItemRepository).deleteById(menuItemId);
    }

    @Test
    void whenDeleteNonExistingMenuItem_thenThrowException() {
        // Given
        Long menuItemId = 1L;
        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            deleteMenuItemUseCase.execute(menuItemId);
        });

        assertEquals("Item de cardápio não encontrado", exception.getMessage());
        verify(menuItemRepository).findById(menuItemId);
        verify(menuItemRepository, never()).deleteById(anyLong());
    }
}