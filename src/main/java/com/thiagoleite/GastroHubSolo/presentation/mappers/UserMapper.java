package com.thiagoleite.GastroHubSolo.presentation.mappers;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.presentation.dtos.RegisterRequest;
import com.thiagoleite.GastroHubSolo.presentation.dtos.RegisterResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toUser(RegisterRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .address(request.getAddress())
                .userType(request.getUserType())
                .lastUpdatedAt(LocalDateTime.now())
                .build();
    }

    public RegisterResponse toRegisterResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .lastUpdatedAt(user.getLastUpdatedAt())
                .address(user.getAddress())
                .userType(user.getUserType())
                .build();
    }
}
