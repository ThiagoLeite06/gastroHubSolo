package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;

import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserTypeRepository extends JpaRepository<UserTypeEntity, Long> {
    boolean existsByName(String name);
}
