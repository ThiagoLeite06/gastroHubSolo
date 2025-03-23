package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.AuthInput;
import com.thiagoleite.GastroHubSolo.application.usecases.RegisterUserUseCaseImpl;
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
    private final RegisterUserUseCaseImpl registerUserUseCaseImpl;

    public AuthController(AuthenticateUserUseCase authenticateUserUseCase,
                          RegisterUserUseCaseImpl registerUserUseCaseImpl) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUserUseCaseImpl = registerUserUseCaseImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthOutput> login(@Valid @RequestBody AuthInput authInput) {
        return ResponseEntity.ok(authenticateUserUseCase.execute(authInput));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthOutput> register(@Valid @RequestBody UserInput userInput) {
        return ResponseEntity.ok(registerUserUseCaseImpl.execute(userInput));
    }
}