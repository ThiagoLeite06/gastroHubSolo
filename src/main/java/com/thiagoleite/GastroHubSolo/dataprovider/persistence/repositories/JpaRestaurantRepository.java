package com.thiagoleite.GastroHubSolo.dataprovider.persistence.repositories;

import com.thiagoleite.GastroHubSolo.dataprovider.persistence.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByName(String name);
}
