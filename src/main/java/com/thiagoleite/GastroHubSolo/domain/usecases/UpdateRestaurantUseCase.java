package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UpdateRestaurantInput;

public interface UpdateRestaurantUseCase {
    RestaurantOutput execute(Long id, UpdateRestaurantInput input);
}
