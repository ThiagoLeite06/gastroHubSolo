package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateRestaurantInput;
import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.RestaurantRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.CreateRestaurantUseCase;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.RestaurantMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantMapper restaurantMapper;

    public CreateRestaurantUseCaseImpl(
            RestaurantRepository restaurantRepository,
            UserRepository userRepository,
            RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public RestaurantOutput execute(CreateRestaurantInput input) {

        if (restaurantRepository.existsByNameAndOwnerId(input.getName(), input.getOwnerId())) {
            throw new RuntimeException("Já existe um restaurante com este nome para este proprietário");
        }

        // Buscar o usuário proprietário
        User owner = userRepository.findById(input.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        // Criar o restaurante
        Restaurant restaurant = new Restaurant();
        restaurant.setName(input.getName());
        restaurant.setAddress(input.getAddress());
        restaurant.setCuisineType(input.getCuisineType());
        restaurant.setOperatingHours(input.getOperatingHours());
        restaurant.setOwner(owner);
        restaurant.setCreatedAt(LocalDateTime.now());
        restaurant.setLastUpdatedAt(LocalDateTime.now());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toOutput(savedRestaurant);
    }
}