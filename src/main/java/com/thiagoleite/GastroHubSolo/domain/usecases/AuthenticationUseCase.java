package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.AuthenticationResult;

public interface AuthenticationUseCase {
    AuthenticationResult authenticate(String email, String password);
}
