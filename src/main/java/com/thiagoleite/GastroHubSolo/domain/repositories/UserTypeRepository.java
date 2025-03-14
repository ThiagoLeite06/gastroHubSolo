package com.thiagoleite.GastroHubSolo.domain.repositories;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;

import java.util.List;
import java.util.Optional;

public interface UserTypeRepository {
    UserType save(UserType userType);
    Optional<UserType> findById(Long id);
    List<UserType> findAll();
    void deleteById(Long id);
    boolean existsByName(String name);
}
