package com.thiagoleite.GastroHubSolo.application.services;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.LoginRequestDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.User;

import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticateUserUseCase;

import com.thiagoleite.GastroHubSolo.infrastructure.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    public AuthenticateUserUseCaseImpl(AuthenticationManager authenticationManager,
                                       UserRepository userRepository,
                                       JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public AuthResponseDTO execute(LoginRequestDTO loginRequestDto) {
        // Autenticar usuário com Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Gerar token
        String token = tokenProvider.generateToken(authentication);

        // Buscar usuário
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Retornar resposta
        return AuthResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
