package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.User;

import java.util.List;

public interface GetUserUseCase {
    User getById(Long id);
    List<User> getAll();
}
