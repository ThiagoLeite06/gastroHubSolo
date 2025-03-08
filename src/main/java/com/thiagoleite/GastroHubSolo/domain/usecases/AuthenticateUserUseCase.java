package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.exceptions.AuthenticationException;


public interface AuthenticateUserUseCase {
    User execute(String email, String password);
}
