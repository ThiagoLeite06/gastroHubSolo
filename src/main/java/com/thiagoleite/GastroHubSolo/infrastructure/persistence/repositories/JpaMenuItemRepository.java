//package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;
//
//import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.MenuItemEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface JpaMenuItemRepository extends JpaRepository<MenuItemEntity, Long> {
//    List<MenuItemEntity> findByRestaurantId(Long restaurantId);
//    boolean existsByNameAndRestaurantId(String name, Long restaurantId);
//}