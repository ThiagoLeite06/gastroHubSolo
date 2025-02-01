package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.User;

public interface DeleteUserUseCase {
    void execute(Long id);
}
