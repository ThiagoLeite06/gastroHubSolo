package com.thiagoleite.GastroHubSolo.domain.entities;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private LocalDateTime lastUpdatedAt;
    private String address;
    private UserType userType;
}