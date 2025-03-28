package com.thiagoleite.GastroHubSolo.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateUserInput {
    @NotBlank(message = "O nome é obrigatório")
    @Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    private String password;

    @NotBlank(message = "O endereço é obrigatório")
    @Length(max = 200, message = "O endereço não pode ter mais que 200 caracteres")
    private String address;

    private Long userTypeId;
}
