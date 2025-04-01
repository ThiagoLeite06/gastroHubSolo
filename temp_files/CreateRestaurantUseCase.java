package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateRestaurantInput;
import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;

public interface CreateRestaurantUseCase {
    RestaurantOutput execute(CreateRestaurantInput input);
}