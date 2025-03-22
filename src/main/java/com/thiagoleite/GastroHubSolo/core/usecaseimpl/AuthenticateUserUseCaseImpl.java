package com.thiagoleite.GastroHubSolo.core.usecaseimpl;

import com.thiagoleite.GastroHubSolo.core.domain.AuthResponseDomain;
import com.thiagoleite.GastroHubSolo.core.domain.LoginRequestDomain;
import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;

import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.core.usecase.AuthenticateUserUseCase;

import com.thiagoleite.GastroHubSolo.dataprovider.security.JwtTokenProvider;
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

    public AuthResponseDomain execute(LoginRequestDomain loginRequestDomain) {
        try {
            System.out.println("Tentando autenticar usuário: " + loginRequestDomain.getEmail());

            // Autenticar usuário com Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDomain.getEmail(),
                            loginRequestDomain.getPassword()
                    )
            );

            System.out.println("Autenticação bem-sucedida para: " + loginRequestDomain.getEmail());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gerar token
            String token = tokenProvider.generateToken(authentication);
            System.out.println("Token gerado com sucesso: " + token.substring(0, 10) + "...");

            // Buscar usuário
            UserDomain userDomain = userRepository.findByEmail(loginRequestDomain.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            System.out.println("Usuário encontrado com ID: " + userDomain.getId());

            // Retornar resposta
            return AuthResponseDomain.builder()
                    .token(token)
                    .type("Bearer")
                    .id(userDomain.getId())
                    .name(userDomain.getName())
                    .email(userDomain.getEmail())
                    .build();
        } catch (Exception e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
