package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(User user) {
        return userRepository.save(user);
    }
}
