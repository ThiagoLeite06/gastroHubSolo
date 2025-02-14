package com.thiagoleite.GastroHubSolo.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String role;
}
