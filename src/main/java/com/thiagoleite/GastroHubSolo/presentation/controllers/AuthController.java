package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.AuthInput;
import com.thiagoleite.GastroHubSolo.application.usecases.RegisterUserUseCaseImpl;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Auth Controller")
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUserUseCaseImpl registerUserUseCaseImpl;

    public AuthController(AuthenticateUserUseCase authenticateUserUseCase,
                          RegisterUserUseCaseImpl registerUserUseCaseImpl) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUserUseCaseImpl = registerUserUseCaseImpl;
    }

    @PostMapping("/login")
    @Operation(description = "Endpoint para logar", summary = "Este é um resumo para logar")
    public ResponseEntity<AuthOutput> login(@Valid @RequestBody AuthInput authInput) {
        return ResponseEntity.ok(authenticateUserUseCase.execute(authInput));
    }

    @PostMapping("/register")
    @Operation(description = "Endpoint para registrar", summary = "Este é um resumo para registrar")
    public ResponseEntity<AuthOutput> register(@Valid @RequestBody UserInput userInput) {
        return ResponseEntity.ok(registerUserUseCaseImpl.execute(userInput));
    }
}