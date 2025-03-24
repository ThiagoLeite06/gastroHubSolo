package com.thiagoleite.GastroHubSolo.domain.repositories;

import com.thiagoleite.GastroHubSolo.domain.entities.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository {
    MenuItem save(MenuItem menuItem);
    Optional<MenuItem> findById(Long id);
    List<MenuItem> findAll();
    List<MenuItem> findByRestaurantId(Long restaurantId);
    void deleteById(Long id);
    boolean existsByNameAndRestaurantId(String name, Long restaurantId);
}