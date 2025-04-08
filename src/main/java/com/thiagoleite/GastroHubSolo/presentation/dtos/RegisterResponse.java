package com.thiagoleite.GastroHubSolo.presentation.dtos;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime lastUpdatedAt;
    private String address;
    private UserType userType;
}