package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.UpdateRestaurantInput;
import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.RestaurantRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.UpdateRestaurantUseCase;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.RestaurantMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateRestaurantUseCaseImpl implements UpdateRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantMapper restaurantMapper;

    public UpdateRestaurantUseCaseImpl(
            RestaurantRepository restaurantRepository,
            UserRepository userRepository,
            RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public RestaurantOutput execute(Long id, UpdateRestaurantInput input) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

        // Atualizar campos
        existingRestaurant.setName(input.getName());
        existingRestaurant.setAddress(input.getAddress());
        existingRestaurant.setCuisineType(input.getCuisineType());
        existingRestaurant.setOperatingHours(input.getOperatingHours());

        // Se um novo dono for fornecido
        if (input.getOwnerId() != null &&
                (existingRestaurant.getOwner() == null || !input.getOwnerId().equals(existingRestaurant.getOwner().getId()))) {
            User owner = userRepository.findById(input.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            existingRestaurant.setOwner(owner);
        }

        existingRestaurant.setLastUpdatedAt(LocalDateTime.now());

        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);
        return restaurantMapper.toOutput(updatedRestaurant);
    }
}
