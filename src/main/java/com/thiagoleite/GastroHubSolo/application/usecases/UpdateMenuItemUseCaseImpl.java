//package com.thiagoleite.GastroHubSolo.application.usecases;
//
//import com.thiagoleite.GastroHubSolo.application.dtos.UpdateMenuItemInput;
//import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;
//import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
//import com.thiagoleite.GastroHubSolo.domain.entities.MenuItem;
//import com.thiagoleite.GastroHubSolo.domain.repositories.MenuItemRepository;
//import com.thiagoleite.GastroHubSolo.domain.usecases.UpdateMenuItemUseCase;
//
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class UpdateMenuItemUseCaseImpl implements UpdateMenuItemUseCase {
//    private final MenuItemRepository menuItemRepository;
//    private final MenuItemMapper menuItemMapper;
//
//    public UpdateMenuItemUseCaseImpl(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
//        this.menuItemRepository = menuItemRepository;
//        this.menuItemMapper = menuItemMapper;
//    }
//
//    @Override
//    public MenuItemOutput execute(Long id, UpdateMenuItemInput input) {
//        MenuItem existingMenuItem = menuItemRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Item de cardápio não encontrado"));
//
//        // Atualizar campos
//        existingMenuItem.setName(input.getName());
//        existingMenuItem.setDescription(input.getDescription());
//        existingMenuItem.setPrice(input.getPrice());
//        existingMenuItem.setAvailableOnlyInRestaurant(input.isAvailableOnlyInRestaurant());
//
//        if (input.getPhotoPath() != null && !input.getPhotoPath().isEmpty()) {
//            existingMenuItem.setPhotoPath(input.getPhotoPath());
//        }
//
//        existingMenuItem.setLastUpdatedAt(LocalDateTime.now());
//
//        MenuItem updatedMenuItem = menuItemRepository.save(existingMenuItem);
//        return menuItemMapper.toOutput(updatedMenuItem);
//    }
//}