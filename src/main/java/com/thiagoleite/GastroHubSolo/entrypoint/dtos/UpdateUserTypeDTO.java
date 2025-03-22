package com.thiagoleite.GastroHubSolo.entrypoint.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateUserTypeDTO {
    @NotBlank(message = "O nome do tipo é obrigatório")
    @Length(min = 3, max = 50, message = "O nome do tipo deve ter entre 3 e 50 caracteres")
    private String name;
}
