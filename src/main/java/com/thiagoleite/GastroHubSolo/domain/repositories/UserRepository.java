package com.thiagoleite.GastroHubSolo.domain.repositories;

import org.springframework.stereotype.Repository;
import com.thiagoleite.GastroHubSolo.domain.entities.User;

import java.util.Optional;


@Repository
public interface UserRepository {
    boolean existsByEmail(String email);
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
