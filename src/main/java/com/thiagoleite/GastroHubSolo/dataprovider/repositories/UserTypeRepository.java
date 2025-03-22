package com.thiagoleite.GastroHubSolo.dataprovider.repositories;

import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;

import java.util.List;
import java.util.Optional;

public interface UserTypeRepository {
    UserTypeDomain save(UserTypeDomain userTypeDomain);
    Optional<UserTypeDomain> findById(Long id);
    List<UserTypeDomain> findAll();
    void deleteById(Long id);
    boolean existsByName(String name);
}
