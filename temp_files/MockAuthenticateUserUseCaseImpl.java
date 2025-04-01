package com.thiagoleite.GastroHubSolo.mocks;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthInput;
import com.thiagoleite.GastroHubSolo.application.dtos.AuthOutput;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticateUserUseCase;
import com.thiagoleite.GastroHubSolo.presentation.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Esta é uma versão modificada da classe AuthenticateUserUseCaseImpl para testes
 * Ela não faz o substring do token que pode causar erros no teste
 */
public class MockAuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    public MockAuthenticateUserUseCaseImpl(AuthenticationManager authenticationManager,
                                           UserRepository userRepository,
                                           JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public AuthOutput execute(AuthInput authInput) {
        try {
            System.out.println("Tentando autenticar usuário: " + authInput.getEmail());

            // Autenticar usuário com Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authInput.getEmail(),
                            authInput.getPassword()
                    )
            );

            System.out.println("Autenticação bem-sucedida para: " + authInput.getEmail());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gerar token
            String token = tokenProvider.generateToken(authentication);
            System.out.println("Token gerado com sucesso");

            // Buscar usuário
            User user = userRepository.findByEmail(authInput.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            System.out.println("Usuário encontrado com ID: " + user.getId());

            // Retornar resposta
            return AuthOutput.builder()
                    .token(token)
                    .type("Bearer")
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        } catch (Exception e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
            throw e;
        }
    }
}