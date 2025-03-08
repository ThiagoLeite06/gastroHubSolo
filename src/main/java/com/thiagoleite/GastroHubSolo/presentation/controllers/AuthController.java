package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.dtos.LoginRequestDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.LoginResponseDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticateUserUseCase;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final UserMapper userMapper;

    public AuthController(AuthenticateUserUseCase authenticateUserUseCase, UserMapper userMapper) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        User authenticatedUser = authenticateUserUseCase.execute(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        LoginResponseDTO response = new LoginResponseDTO();
        response.setUserId(authenticatedUser.getId());
        response.setName(authenticatedUser.getName());
        response.setEmail(authenticatedUser.getEmail());
        response.setRole(authenticatedUser.getRole());
        // response.setToken("jwt-token-here"); // Para implementação futura

        return ResponseEntity.ok(response);
    }
}