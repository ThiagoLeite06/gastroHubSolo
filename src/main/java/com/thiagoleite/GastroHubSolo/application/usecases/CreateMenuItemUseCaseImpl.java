//package com.thiagoleite.GastroHubSolo.application.usecases;
//
//import com.thiagoleite.GastroHubSolo.application.dtos.CreateMenuItemInput;
//import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;
//import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
//import com.thiagoleite.GastroHubSolo.domain.entities.MenuItem;
//import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
//import com.thiagoleite.GastroHubSolo.domain.repositories.MenuItemRepository;
//import com.thiagoleite.GastroHubSolo.domain.repositories.RestaurantRepository;
//import com.thiagoleite.GastroHubSolo.domain.usecases.CreateMenuItemUseCase;
//
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//
//@Service
//public class CreateMenuItemUseCaseImpl implements CreateMenuItemUseCase {
//    private final MenuItemRepository menuItemRepository;
//    private final RestaurantRepository restaurantRepository;
//    private final MenuItemMapper menuItemMapper;
//
//    public CreateMenuItemUseCaseImpl(
//            MenuItemRepository menuItemRepository,
//            RestaurantRepository restaurantRepository,
//            MenuItemMapper menuItemMapper) {
//        this.menuItemRepository = menuItemRepository;
//        this.restaurantRepository = restaurantRepository;
//        this.menuItemMapper = menuItemMapper;
//    }
//
//    @Override
//    public MenuItemOutput execute(CreateMenuItemInput input) {
//
//        if (menuItemRepository.existsByNameAndRestaurantId(input.getName(), input.getRestaurantId())) {
//            throw new RuntimeException("Já existe um item com este nome neste restaurante");
//        }
//
//
//        Restaurant restaurant = restaurantRepository.findById(input.getRestaurantId())
//                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));
//
//        MenuItem menuItem = new MenuItem();
//        menuItem.setName(input.getName());
//        menuItem.setDescription(input.getDescription());
//        menuItem.setPrice(input.getPrice());
//        menuItem.setAvailableOnlyInRestaurant(input.isAvailableOnlyInRestaurant());
//        menuItem.setPhotoPath(input.getPhotoPath());
//        menuItem.setRestaurant(restaurant);
//        menuItem.setCreatedAt(LocalDateTime.now());
//        menuItem.setLastUpdatedAt(LocalDateTime.now());
//
//        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
//        return menuItemMapper.toOutput(savedMenuItem);
//    }
//}