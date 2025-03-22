package com.thiagoleite.GastroHubSolo.dataprovider.persistence.repositories;

import com.thiagoleite.GastroHubSolo.core.domain.RestaurantDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.RestaurantRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final JpaRestaurantRepository jpaRestaurantRepository;

    public RestaurantRepositoryImpl(JpaRestaurantRepository jpaRestaurantRepository) {
        this.jpaRestaurantRepository = jpaRestaurantRepository;
    }


    @Override
    public RestaurantDomain save(RestaurantDomain restaurantDomain) {
        return null;
    }

    @Override
    public Optional<RestaurantDomain> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<RestaurantDomain> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<RestaurantDomain> findByName(String name) {
        return Optional.empty();
    }
}
