package com.thiagoleite.GastroHubSolo.domain.repositories;

import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    Optional<Restaurant> findById(Long id);
    List<Restaurant> findAll();
    List<Restaurant> findByOwnerId(Long ownerId);
    void deleteById(Long id);
    boolean existsByNameAndOwnerId(String name, Long ownerId);
}