package com.thiagoleite.GastroHubSolo.presentation.dtos;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String role;

    private String address;

    @NotNull
    private UserType userType;
}
