package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.usecases.LoginUserUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.RegisterUserUseCase;
import com.thiagoleite.GastroHubSolo.presentation.dtos.LoginRequest;
import com.thiagoleite.GastroHubSolo.presentation.dtos.LoginResponse;
import com.thiagoleite.GastroHubSolo.presentation.mappers.UserMapper;
import com.thiagoleite.GastroHubSolo.presentation.dtos.RegisterRequest;
import com.thiagoleite.GastroHubSolo.presentation.dtos.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Qualifier("registerUserService")
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = userMapper.toUser(request);
        User registeredUser = registerUserUseCase.execute(user);
        RegisterResponse response = userMapper.toRegisterResponse(registeredUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = loginUserUseCase.execute(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}