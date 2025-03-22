package com.thiagoleite.GastroHubSolo.entrypoint.controllers;

import com.thiagoleite.GastroHubSolo.entrypoint.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.CreateUserDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.LoginRequestDTO;
import com.thiagoleite.GastroHubSolo.core.usecaseimpl.RegisterUserUseCase;
import com.thiagoleite.GastroHubSolo.core.usecase.AuthenticateUserUseCase;
import com.thiagoleite.GastroHubSolo.entrypoint.service.AuthUserControllerImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserControllerImpl authUserControllerImpl;
    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(AuthUserControllerImpl authUserControllerImpl,
                          RegisterUserUseCase registerUserUseCase) {
        this.authUserControllerImpl = authUserControllerImpl;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDto) {
        return ResponseEntity.ok(authUserControllerImpl.execute(loginRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(registerUserUseCase.execute(createUserDTO));
    }
}