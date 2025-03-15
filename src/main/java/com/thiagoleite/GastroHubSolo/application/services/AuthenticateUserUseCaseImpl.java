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
        try {
            System.out.println("Tentando autenticar usuário: " + loginRequestDto.getEmail());

            // Autenticar usuário com Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getEmail(),
                            loginRequestDto.getPassword()
                    )
            );

            System.out.println("Autenticação bem-sucedida para: " + loginRequestDto.getEmail());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gerar token
            String token = tokenProvider.generateToken(authentication);
            System.out.println("Token gerado com sucesso: " + token.substring(0, 10) + "...");

            // Buscar usuário
            User user = userRepository.findByEmail(loginRequestDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            System.out.println("Usuário encontrado com ID: " + user.getId());

            // Retornar resposta
            return AuthResponseDTO.builder()
                    .token(token)
                    .type("Bearer")
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        } catch (Exception e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
