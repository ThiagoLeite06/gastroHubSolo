package com.thiagoleite.GastroHubSolo.dataprovider.repositories;

import com.thiagoleite.GastroHubSolo.core.domain.RestaurantDomain;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    RestaurantDomain save (RestaurantDomain restaurantDomain);
    Optional<RestaurantDomain> findById(Long id);
    List<RestaurantDomain> findAll();
    void deleteById(Long id);
    Optional<RestaurantDomain> findByName(String name);
}
