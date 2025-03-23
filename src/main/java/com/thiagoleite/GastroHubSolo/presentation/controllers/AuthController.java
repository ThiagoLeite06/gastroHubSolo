package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.CreateUserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.LoginRequestDTO;
import com.thiagoleite.GastroHubSolo.application.usecases.RegisterUserUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(AuthenticateUserUseCase authenticateUserUseCase,
                          RegisterUserUseCase registerUserUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDto) {
        return ResponseEntity.ok(authenticateUserUseCase.execute(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody CreateUserInput createUserInput) {
        return ResponseEntity.ok(registerUserUseCase.execute(createUserInput));
    }
}