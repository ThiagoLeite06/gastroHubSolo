//package com.thiagoleite.GastroHubSolo.application.usecases;
//
//import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;
//import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
//import com.thiagoleite.GastroHubSolo.domain.entities.MenuItem;
//import com.thiagoleite.GastroHubSolo.domain.repositories.MenuItemRepository;
//import com.thiagoleite.GastroHubSolo.domain.usecases.GetMenuItemUseCase;
//
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class GetMenuItemUseCaseImpl implements GetMenuItemUseCase {
//    private final MenuItemRepository menuItemRepository;
//    private final MenuItemMapper menuItemMapper;
//
//    public GetMenuItemUseCaseImpl(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
//        this.menuItemRepository = menuItemRepository;
//        this.menuItemMapper = menuItemMapper;
//    }
//
//    @Override
//    public MenuItemOutput getById(Long id) {
//        MenuItem menuItem = menuItemRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Item de cardápio não encontrado"));
//        return menuItemMapper.toOutput(menuItem);
//    }
//
//    @Override
//    public List<MenuItemOutput> getAll() {
//        List<MenuItem> menuItems = menuItemRepository.findAll();
//        return menuItems.stream()
//                .map(menuItemMapper::toOutput)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<MenuItemOutput> getByRestaurantId(Long restaurantId) {
//        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(restaurantId);
//        return menuItems.stream()
//                .map(menuItemMapper::toOutput)
//                .collect(Collectors.toList());
//    }
//}