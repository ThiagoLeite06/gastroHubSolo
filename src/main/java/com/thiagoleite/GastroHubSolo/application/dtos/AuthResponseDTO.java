package com.thiagoleite.GastroHubSolo.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthResponseDTO {
    private String token;
    private String type = "Bearer";
    private String email;

    public AuthResponseDTO(String token, String email) {
        this.token = token;
        this.email = email;
    }
}
