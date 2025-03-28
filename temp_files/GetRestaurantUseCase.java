package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;

import java.util.List;

public interface GetRestaurantUseCase {
    RestaurantOutput getById(Long id);
    List<RestaurantOutput> getAll();
    List<RestaurantOutput> getByOwnerId(Long ownerId);
}
