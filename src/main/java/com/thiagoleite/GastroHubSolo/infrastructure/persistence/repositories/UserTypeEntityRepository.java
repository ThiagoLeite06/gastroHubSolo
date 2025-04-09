package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserTypeEntity;

@Repository
public interface UserTypeEntityRepository extends JpaRepository<UserTypeEntity, Long> {
} 