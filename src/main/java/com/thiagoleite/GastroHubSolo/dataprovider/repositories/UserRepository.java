package com.thiagoleite.GastroHubSolo.dataprovider.repositories;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserDomain save(UserDomain userDomain);
    Optional<UserDomain> findById(Long id);
    List<UserDomain> findAll();
    void deleteById(Long id);
    Optional<UserDomain> findByEmail(String email);
    boolean existsByEmail(String email);
}
