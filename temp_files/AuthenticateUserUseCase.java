package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.AuthInput;

public interface AuthenticateUserUseCase {
    AuthOutput execute(AuthInput authInput);
}

