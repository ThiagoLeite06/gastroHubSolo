package com.thiagoleite.GastroHubSolo.infrastructure.security;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.application.services.AuthenticationUseCaseImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthenticationUseCaseImpl authenticationUseCaseImpl;

    public UserDetailsServiceImpl(AuthenticationUseCaseImpl authenticationUseCaseImpl) {
        this.authenticationUseCaseImpl = authenticationUseCaseImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // Aqui passamos uma senha vazia pois a verificação real será feita pelo AuthenticationUseCase
            User user = authenticationUseCaseImpl.authenticate("teste@teste.com", "").getUser();
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
