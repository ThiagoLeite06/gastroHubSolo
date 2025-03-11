package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.LoginRequestDTO;

public interface AuthenticateUserUseCase {
    AuthResponseDTO execute(LoginRequestDTO loginRequestDTO);
}

