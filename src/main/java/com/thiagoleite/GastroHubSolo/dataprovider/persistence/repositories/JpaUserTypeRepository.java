package com.thiagoleite.GastroHubSolo.dataprovider.persistence.repositories;

import com.thiagoleite.GastroHubSolo.dataprovider.persistence.entities.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserTypeRepository extends JpaRepository<UserTypeEntity, Long> {
    boolean existsByName(String name);
}
