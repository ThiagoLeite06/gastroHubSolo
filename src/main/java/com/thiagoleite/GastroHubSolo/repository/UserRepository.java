package com.thiagoleite.GastroHubSolo.repository;

import com.thiagoleite.GastroHubSolo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
