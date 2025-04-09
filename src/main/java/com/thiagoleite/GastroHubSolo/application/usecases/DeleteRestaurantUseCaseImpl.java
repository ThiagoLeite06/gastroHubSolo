//package com.thiagoleite.GastroHubSolo.application.usecases;
//
//import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
//import com.thiagoleite.GastroHubSolo.domain.repositories.RestaurantRepository;
//import com.thiagoleite.GastroHubSolo.domain.usecases.DeleteRestaurantUseCase;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DeleteRestaurantUseCaseImpl implements DeleteRestaurantUseCase {
//    private final RestaurantRepository restaurantRepository;
//
//    public DeleteRestaurantUseCaseImpl(RestaurantRepository restaurantRepository) {
//        this.restaurantRepository = restaurantRepository;
//    }
//
//    @Override
//    public void execute(Long id) {
//        if (!restaurantRepository.findById(id).isPresent()) {
//            throw new ResourceNotFoundException("Restaurante não encontrado");
//        }
//        restaurantRepository.deleteById(id);
//    }
//}