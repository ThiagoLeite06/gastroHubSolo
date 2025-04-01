package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
import com.thiagoleite.GastroHubSolo.domain.repositories.RestaurantRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.GetRestaurantUseCase;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.RestaurantMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetRestaurantUseCaseImpl implements GetRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public GetRestaurantUseCaseImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public RestaurantOutput getById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));
        return restaurantMapper.toOutput(restaurant);
    }

    @Override
    public List<RestaurantOutput> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurantMapper::toOutput)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantOutput> getByOwnerId(Long ownerId) {
        List<Restaurant> restaurants = restaurantRepository.findByOwnerId(ownerId);
        return restaurants.stream()
                .map(restaurantMapper::toOutput)
                .collect(Collectors.toList());
    }
}