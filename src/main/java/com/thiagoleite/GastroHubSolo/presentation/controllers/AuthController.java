package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthRequestDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.AuthenticationResult;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationUseCase authenticationUseCase;

    public AuthController(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO request) {
        AuthenticationResult result = authenticationUseCase.authenticate(
                request.getEmail(),
                request.getPassword()
        );

        AuthResponseDTO response = new AuthResponseDTO(
                result.getToken(),
                result.getUser().getEmail()
        );

        return ResponseEntity.ok(response);
    }
}
