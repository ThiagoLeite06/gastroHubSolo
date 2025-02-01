package com.thiagoleite.GastroHubSolo.domain.repositories;

import com.thiagoleite.GastroHubSolo.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
}
