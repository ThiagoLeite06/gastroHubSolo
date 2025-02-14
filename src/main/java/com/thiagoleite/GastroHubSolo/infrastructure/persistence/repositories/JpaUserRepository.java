package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;

import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}

