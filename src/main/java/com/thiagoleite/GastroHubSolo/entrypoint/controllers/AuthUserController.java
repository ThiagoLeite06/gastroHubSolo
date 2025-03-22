package com.thiagoleite.GastroHubSolo.entrypoint.controllers;

import com.thiagoleite.GastroHubSolo.entrypoint.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.LoginRequestDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.service.AuthUserControllerImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;

@Controller
public class AuthUserController implements AuthUserControllerImpl {
    @Override
    public AuthResponseDTO execute(LoginRequestDTO loginRequestDTO) {
        private final AuthenticationManager authenticationManager;
    }
}
