package com.thiagoleite.GastroHubSolo.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    private String email;
    private String password;
}
