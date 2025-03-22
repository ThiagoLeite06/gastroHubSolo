package com.thiagoleite.GastroHubSolo.entrypoint.service;

import com.thiagoleite.GastroHubSolo.entrypoint.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.LoginRequestDTO;

public interface AuthUserControllerImpl {
    AuthResponseDTO execute(LoginRequestDTO loginRequestDTO);
}
