package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);

    }
}
