package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.User;

public interface CreateUserUseCase {
    User execute(User user);
}
