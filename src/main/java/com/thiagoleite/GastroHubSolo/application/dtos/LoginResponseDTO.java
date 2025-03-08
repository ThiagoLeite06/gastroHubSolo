package com.thiagoleite.GastroHubSolo.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private Long userId;
    private String name;
    private String email;
    private String role;
    private String token;
}
