//package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;
//
//import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
//import com.thiagoleite.GastroHubSolo.domain.repositories.RestaurantRepository;
//
//import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.RestaurantEntity;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class RestaurantRepositoryImpl implements RestaurantRepository {
//    private final JpaRestaurantRepository jpaRestaurantRepository;
//    private final RestaurantMapper restaurantMapper;
//
//    public RestaurantRepositoryImpl(
//            JpaRestaurantRepository jpaRestaurantRepository,
//            RestaurantMapper restaurantMapper) {
//        this.jpaRestaurantRepository = jpaRestaurantRepository;
//        this.restaurantMapper = restaurantMapper;
//    }
//
//    @Override
//    public Restaurant save(Restaurant restaurant) {
//        RestaurantEntity entity = restaurantMapper.toEntity(restaurant);
//        RestaurantEntity savedEntity = jpaRestaurantRepository.save(entity);
//        return restaurantMapper.toDomain(savedEntity);
//    }
//
//    @Override
//    public Optional<Restaurant> findById(Long id) {
//        return jpaRestaurantRepository.findById(id)
//                .map(restaurantMapper::toDomain);
//    }
//
//    @Override
//    public List<Restaurant> findAll() {
//        return jpaRestaurantRepository.findAll().stream()
//                .map(restaurantMapper::toDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Restaurant> findByOwnerId(Long ownerId) {
//        return jpaRestaurantRepository.findByOwnerId(ownerId).stream()
//                .map(restaurantMapper::toDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        jpaRestaurantRepository.deleteById(id);
//    }
//
//    @Override
//    public boolean existsByNameAndOwnerId(String name, Long ownerId) {
//        return jpaRestaurantRepository.existsByNameAndOwnerId(name, ownerId);
//    }
//}