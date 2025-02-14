package com.thiagoleite.GastroHubSolo.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    private String address;
}
