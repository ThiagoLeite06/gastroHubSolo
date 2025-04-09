//package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;
//
//import com.thiagoleite.GastroHubSolo.domain.entities.MenuItem;
//import com.thiagoleite.GastroHubSolo.domain.repositories.MenuItemRepository;
//
//import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.MenuItemEntity;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class MenuItemRepositoryImpl implements MenuItemRepository {
//    private final JpaMenuItemRepository jpaMenuItemRepository;
//    private final MenuItemMapper menuItemMapper;
//
//    public MenuItemRepositoryImpl(
//            JpaMenuItemRepository jpaMenuItemRepository,
//            MenuItemMapper menuItemMapper) {
//        this.jpaMenuItemRepository = jpaMenuItemRepository;
//        this.menuItemMapper = menuItemMapper;
//    }
//
//    @Override
//    public MenuItem save(MenuItem menuItem) {
//        MenuItemEntity entity = menuItemMapper.toEntity(menuItem);
//        MenuItemEntity savedEntity = jpaMenuItemRepository.save(entity);
//        return menuItemMapper.toDomain(savedEntity);
//    }
//
//    @Override
//    public Optional<MenuItem> findById(Long id) {
//        return jpaMenuItemRepository.findById(id)
//                .map(menuItemMapper::toDomain);
//    }
//
//    @Override
//    public List<MenuItem> findAll() {
//        return jpaMenuItemRepository.findAll().stream()
//                .map(menuItemMapper::toDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<MenuItem> findByRestaurantId(Long restaurantId) {
//        return jpaMenuItemRepository.findByRestaurantId(restaurantId).stream()
//                .map(menuItemMapper::toDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        jpaMenuItemRepository.deleteById(id);
//    }
//
//    @Override
//    public boolean existsByNameAndRestaurantId(String name, Long restaurantId) {
//        return jpaMenuItemRepository.existsByNameAndRestaurantId(name, restaurantId);
//    }
//}