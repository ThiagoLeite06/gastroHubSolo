package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.User;

public interface UpdateUserUseCase {
    User execute(Long id, User user);
}
